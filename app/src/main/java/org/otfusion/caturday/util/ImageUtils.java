package org.otfusion.caturday.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageUtils {

    public static Bitmap cropBitmap(Bitmap bitmap) {
        if (bitmap.getWidth() >= bitmap.getHeight()) {
            return Bitmap.createBitmap(bitmap, bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                    0, bitmap.getHeight(), bitmap.getHeight()
            );

        } else {
            return Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                    bitmap.getWidth(), bitmap.getWidth()
            );
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }

}
