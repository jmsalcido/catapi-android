package org.otfusion.votecats.service.images.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.service.images.StorageImageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageTarget implements Target {

    private final Cat _cat;

    public StorageTarget(Cat cat) {
        _cat = cat;
    }

    private FileOutputStream prepareOutputStream(File file) throws IOException {
        return new FileOutputStream(file);
    }

    private File getFile() throws IOException {
        if (isExternalStorageWritable()) {
            return getStorageFile(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES));
        } else {
            return getStorageFile(VoteCatsApplication.getContext().getFilesDir());
        }
    }

    private File getStorageFile(File directory) throws IOException {
        File folder = new File(directory, StorageImageService.FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String externalPath = folder.getPath();
        File file = new File(externalPath, _cat.getName() + ".jpg");
        file.createNewFile();
        return file;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        try {
            File file = getFile();
            SaveImageAsyncTask asyncTask = new SaveImageAsyncTask(VoteCatsApplication.getContext(),
                    bitmap, file, prepareOutputStream(file));
            asyncTask.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        // Dont save file, easy.
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }
}
