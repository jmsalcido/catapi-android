package org.otfusion.caturday.model.service.images;

import org.otfusion.caturday.common.domain.Cat;

public interface StorageImageService {

    String FOLDER_NAME = "voted_cats";

    void saveImageIntoStorage(Cat cat);

    void deleteImageFromStorage(Cat cat);

}
