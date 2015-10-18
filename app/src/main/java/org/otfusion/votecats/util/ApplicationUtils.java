package org.otfusion.votecats.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ApplicationUtils {

    public static String generateRandomCatName() {
        List<String> differentNames = Arrays.asList(
                "Aegean",
                "Bambino",
                "Birman",
                "Cheetoh",
                "Chausie",
                "Cyprus",
                "Dwelf",
                "Egyptian Mau",
                "Javanese",
                "Korat",
                "Minskin",
                "Munchkin",
                "Persian",
                "Siamese",
                "Sphynx",
                "Toyger",
                "Toybob"
        );
        Random random = new Random(System.currentTimeMillis());
        int sizeOfList = differentNames.size();
        int randomInt = random.nextInt(sizeOfList - 1) + sizeOfList;
        return "";
    }

}
