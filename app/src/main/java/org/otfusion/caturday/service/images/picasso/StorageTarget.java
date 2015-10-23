package org.otfusion.caturday.service.images.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.util.FileUtils;
import org.otfusion.caturday.util.UIUtils;

import java.io.File;
import java.io.FileOutputStream;

public class StorageTarget implements Target {

    private final Cat cat;
    private final Context context;

    public StorageTarget(Cat cat, Context context) {
        this.cat = cat;
        this.context = context;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        File file = FileUtils.getFile(cat);
        FileOutputStream fileOutputStream = FileUtils.prepareOutputStream(file);
        if (file == null || fileOutputStream == null) {
            UIUtils.showToast("Error loading storage");
            return;
        }

        SaveImageAsyncTask asyncTask = new SaveImageAsyncTask(context, bitmap, file,
                fileOutputStream);
        asyncTask.execute();
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        // Dont save file, easy.
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }
}
