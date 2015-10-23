package org.otfusion.caturday.service.images.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageAsyncTask extends AsyncTask<Void, Void, Void> {

    private final Context context;
    private final Bitmap bitmap;
    private final File file;
    private final FileOutputStream fileOutputStream;

    public SaveImageAsyncTask(Context context, Bitmap bitmap, File file, FileOutputStream fileOutputStream) {
        this.context = context;
        this.bitmap = bitmap;
        this.file = file;
        this.fileOutputStream = fileOutputStream;
    }


    @Override
    protected Void doInBackground(Void... params) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        MediaScannerConnection.scanFile(context, new String[] {file.getPath()}, null, null);
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
