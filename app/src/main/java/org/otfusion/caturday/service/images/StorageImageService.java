package org.otfusion.caturday.service.images;

import org.otfusion.caturday.common.model.Cat;

public interface StorageImageService {

    String FOLDER_NAME = "voted_cats";

    void saveImageIntoStorage(Cat cat);

    void deleteImageFromStorage(Cat cat);

}
