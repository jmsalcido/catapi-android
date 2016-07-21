package org.otfusion.caturday.util;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.service.images.StorageImageService;

import java.io.File;
import java.io.IOException;

// todo remove
public class FileUtils {

    private FileUtils() {

    }

    @Nullable
    // todo remove this CatService maybe?
    public static File getFile(Cat cat) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
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
    // todo remove this CatService maybe?
    public static String getFileName(Cat cat, boolean absolute) {
        File file = getFile(cat);
        if (file != null && file.exists()) {
            if (absolute) {
                return file.getAbsolutePath();
            } else {
                return file.getPath();
            }
        }
        return "";
    }

    // todo remove this
    private static File getStorageFile(Cat cat, File directory) throws IOException {
        File folder = new File(directory, StorageImageService.FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String externalPath = folder.getPath();
        File file = new File(externalPath, cat.getName() + ".jpg");
        return file;
    }

}
