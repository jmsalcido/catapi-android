package org.otfusion.votecats.service.images;

import org.otfusion.votecats.common.model.Cat;

public interface StorageImageService {

    String FOLDER_NAME = "voted_cats";

    void saveImageIntoSD(Cat cat);

}
