package org.otfusion.caturday.util;

import android.widget.Toast;

import org.otfusion.caturday.application.VoteCatsApplication;

public class UIUtils {

    public static void showToast(String text, boolean longToast) {
        int duration = longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(VoteCatsApplication.getContext(), text, duration).show();
    }

    public static void showToast(String text) {
        showToast(text, false);
    }
}
