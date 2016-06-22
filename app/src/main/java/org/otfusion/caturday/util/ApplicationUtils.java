package org.otfusion.caturday.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import org.otfusion.caturday.application.VoteCatsApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationUtils {

    private ApplicationUtils() {
    }

    public static VoteCatsApplication getApplication(Activity activity) {
        return (VoteCatsApplication) activity.getApplication();
    }

    @SuppressWarnings("unchecked")
    public static <T> T castActivityToInterface(Activity activity, Class<T> interfaceType) {
        try {
            return (T) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + interfaceType.getClass().getName() + " interface");
        }
    }

    public static Intent getShareImageIntent(Uri fileUri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        shareIntent.setType("image/jpeg");
        return shareIntent;
    }

    public static String generateRandomCatName(String suffix) {
        List<String> differentNames = Arrays.asList(
                "aegean",
                "bambino",
                "birman",
                "cheetoh",
                "chausie",
                "cyprus",
                "dwelf",
                "egyptian_mau",
                "javanese",
                "korat",
                "minskin",
                "munchkin",
                "persian",
                "siamese",
                "sphynx",
                "toyger",
                "toybob"
        );
        Random random = new Random(System.currentTimeMillis());
        int sizeOfList = differentNames.size();
        int randomInt = random.nextInt((sizeOfList - 1));
        String prefix = differentNames.get(randomInt) + "_";
        return prefix + suffix;
    }

}
