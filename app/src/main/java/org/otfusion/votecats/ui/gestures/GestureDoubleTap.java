package org.otfusion.votecats.ui.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

import org.otfusion.votecats.ui.events.VoteCatEvent;

public class GestureDoubleTap<T extends VoteCatEvent> extends GestureDetector.SimpleOnGestureListener {

    public static final int DOUBLE_TAP_MILLISECONDS = 350;
    private T _event;
    private long _lastPressTime;

    @Override
    public boolean onDown(MotionEvent e) {
        long pressTime = System.currentTimeMillis();
        long elapsedTime = pressTime - _lastPressTime;
        if (elapsedTime <= DOUBLE_TAP_MILLISECONDS) {
            _event.executeEvent("double tap");
            return true;
        }
        _lastPressTime = pressTime;
        return true;
    }

    public void setEvent(T event) {
        _event = event;
    }

}
