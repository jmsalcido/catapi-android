package org.otfusion.caturday.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.service.images.StorageImageService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    private FileUtils() {

    }

    @Nullable
    public static FileOutputStream prepareOutputStream(File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFilePathFromMediaStore(Bitmap bitmap) {
        VoteCatsApplication context = VoteCatsApplication.getContext();
        return MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bitmap, "share_image", "share_image");
    }

    @Nullable
    public static File getFile(Cat cat) {
        try {
            if (isExternalStorageWritable()) {
                return getStorageFile(cat, Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_PICTURES));
            } else {
                return getStorageFile(cat, VoteCatsApplication.getContext().getFilesDir());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static String getFileName(Cat cat, boolean absolute) {
        File file = getFile(cat);
        if (file != null && file.exists()) {
            if (absolute) {
                return file.getAbsolutePath();
            } else {
                return file.getPath();
            }
        }
        return StringUtils.EMPTY;
    }

    private static File getStorageFile(Cat cat, File directory) throws IOException {
        File folder = new File(directory, StorageImageService.FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String externalPath = folder.getPath();
        File file = new File(externalPath, cat.getName() + ".jpg");
        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
