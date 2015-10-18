package org.otfusion.votecats.ui.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

import org.otfusion.votecats.events.VoteCatEvent;

public class GestureDoubleTap<T extends VoteCatEvent> extends GestureDetector.SimpleOnGestureListener {

    public static final int DOUBLE_TAP_MILLISECONDS = 350;
    private T event;
    private long lastPressTime;

    @Override
    public boolean onDown(MotionEvent e) {
        long pressTime = System.currentTimeMillis();
        long elapsedTime = pressTime - lastPressTime;
        if (elapsedTime <= DOUBLE_TAP_MILLISECONDS) {
            event.executeEvent("double tap");
            return true;
        }
        lastPressTime = pressTime;
        return false;
    }

    public void setEvent(T event) {
        this.event = event;
    }

}
