package org.otfusion.votecats.service.images.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageAsyncTask extends AsyncTask<Void, Void, Void> {

    private final Context _context;
    private final Bitmap _bitmap;
    private final File _file;
    private final FileOutputStream _fileOutputStream;

    public SaveImageAsyncTask(Context context, Bitmap bitmap, File file, FileOutputStream fileOutputStream) {
        _context = context;
        _bitmap = bitmap;
        _file = file;
        _fileOutputStream = fileOutputStream;
    }


    @Override
    protected Void doInBackground(Void... params) {
        _bitmap.compress(Bitmap.CompressFormat.JPEG, 100, _fileOutputStream);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        MediaScannerConnection.scanFile(_context, new String[] {_file.getPath()}, null, null);
        try {
            _fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
