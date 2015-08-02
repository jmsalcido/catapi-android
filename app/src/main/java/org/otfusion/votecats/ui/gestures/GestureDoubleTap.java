package org.otfusion.votecats.ui.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

import org.otfusion.votecats.ui.events.VoteCatEvent;

public class GestureDoubleTap<T extends VoteCatEvent> extends GestureDetector.SimpleOnGestureListener {

    public static final int DOUBLE_TAP_MILLISECONDS = 500;
    private T _event;
    private long _lastPressTime;

    public GestureDoubleTap(T event) {
        _event = event;
    }

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

}
