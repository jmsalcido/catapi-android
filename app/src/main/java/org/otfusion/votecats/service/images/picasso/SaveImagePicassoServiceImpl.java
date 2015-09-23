package org.otfusion.votecats.service.images.picasso;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.service.images.SaveImageService;

public class SaveImagePicassoServiceImpl implements SaveImageService {

    @Override
    public void saveImageIntoSD(Cat cat) {
        Target target = new StorageTarget(cat);
        Picasso.with(VoteCatsApplication.getContext()).load(cat.getImageUrl()).into(target);
    }
}
