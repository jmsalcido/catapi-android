package org.otfusion.caturday.service.images.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.caturday.common.model.Cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class StorageTarget implements Target {

    private final Cat cat;
    private final Context context;
    private final File file;

    StorageTarget(Cat cat, Context context, File file) {
        this.cat = cat;
        this.context = context;
        this.file = file;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        FileOutputStream fileOutputStream = prepareOutputStream(file);
        if (file == null || fileOutputStream == null) {
            if (file != null) {
                Log.w("CATURDAY", "Problems reading file: " + file.getAbsolutePath());
            }
            Log.w("CATURDAY", "fileOutputStream: " + fileOutputStream);
            return;
        }

        SaveImageAsyncTask asyncTask = new SaveImageAsyncTask(context, bitmap, file,
                fileOutputStream);
        asyncTask.execute();
    }

    private FileOutputStream prepareOutputStream(File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
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
