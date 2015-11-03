package org.otfusion.caturday.service.images.picasso;

import android.content.Context;
import android.media.MediaScannerConnection;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.service.images.StorageImageService;
import org.otfusion.caturday.util.FileUtils;

import java.io.File;

import javax.inject.Inject;

public class StorageImagePicassoServiceImpl implements StorageImageService {

    private final Context context;

    @Inject
    public StorageImagePicassoServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void saveImageIntoStorage(Cat cat) {
        Target target = new StorageTarget(cat, context);
        Picasso.with(context).load(cat.getImageUrl()).into(target);
    }

    @Override
    public void deleteImageFromStorage(Cat cat) {
        File file = FileUtils.getFile(cat);
        if (file != null) {
            file.delete();
            MediaScannerConnection.scanFile(context, new String[]{file.getPath()}, null, null);
        }
    }
}
