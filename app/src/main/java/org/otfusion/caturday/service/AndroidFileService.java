package org.otfusion.caturday.service;

import android.content.Context;
import android.os.Environment;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.service.images.StorageImageService;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class AndroidFileService implements FileService {

    private final Context context;

    @Inject
    public AndroidFileService(Context context) {
        this.context = context;
    }

    @Override
    public File getFile(Cat cat) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return getStorageFile(cat, Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_PICTURES));
            } else {
                return getStorageFile(cat, context.getFilesDir());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean doesFileExist(File file) {
        return file != null && file.exists();
    }

    @Override
    public File getStorageFile(Cat cat, File directory) throws IOException {
        File folder = new File(directory, StorageImageService.FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String externalPath = folder.getPath();
        File file = new File(externalPath, cat.getName() + ".jpg");
        return file;
    }
}
