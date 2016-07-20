package org.otfusion.caturday.util;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import org.otfusion.caturday.application.VoteCatsApplication;

// todo remove this
public class UIUtils {

    private static void showSnackbar(View view, String text, boolean longDuration) {
        int duration = longDuration ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT;
        Snackbar.make(view, text, duration).show();
    }

    public static void showSnackbar(View view, String text) {
        showSnackbar(view, text, false);
    }

    public static void showSnackbar(View view, String text, String actionText,
                                    View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(actionText, listener).show();
    }

    private static void showToast(String text, boolean longToast) {
        int duration = longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(VoteCatsApplication.getContext(), text, duration).show();
    }

    public static void showToast(String text) {
        showToast(text, false);
    }
}
