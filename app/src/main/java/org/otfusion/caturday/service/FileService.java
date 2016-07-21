package org.otfusion.caturday.service;

import org.otfusion.caturday.common.model.Cat;

import java.io.File;
import java.io.IOException;

public interface FileService {

    File getFile(Cat cat);

    boolean doesFileExist(File file);

    File getStorageFile(Cat cat, File directory) throws IOException;

}
