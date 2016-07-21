package org.otfusion.caturday.util;

import android.content.Intent;
import android.net.Uri;

// todo remove this class
public class ApplicationUtils {

    private ApplicationUtils() {
    }

    // todo move from here
    public static Intent getShareImageIntent(Uri fileUri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        shareIntent.setType("image/jpeg");
        return shareIntent;
    }

}
