package org.otfusion.votecats.util;

import android.os.Environment;
import android.support.annotation.Nullable;

import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.service.images.StorageImageService;

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

    private static File getStorageFile(Cat cat, File directory) throws IOException {
        File folder = new File(directory, StorageImageService.FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String externalPath = folder.getPath();
        File file = new File(externalPath, cat.getName() + ".jpg");
        file.createNewFile();
        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}