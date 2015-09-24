package org.otfusion.votecats.service.images.picasso;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.service.images.StorageImageService;

import javax.inject.Inject;

public class StorageImagePicassoServiceImpl implements StorageImageService {

    private final Context _context;

    @Inject
    public StorageImagePicassoServiceImpl(Context context) {
        _context = context;
    }

    @Override
    public void saveImageIntoSD(Cat cat) {
        Target target = new StorageTarget(cat);
        Picasso.with(_context).load(cat.getImageUrl()).into(target);
    }
}
