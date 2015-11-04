package org.otfusion.caturday.util;

import android.app.Activity;

import org.otfusion.caturday.ui.fragments.callbacks.FavoriteCallback;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationUtils {

    private ApplicationUtils() {
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
