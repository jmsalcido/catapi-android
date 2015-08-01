package org.otfusion.votecats.ui.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.squareup.otto.Bus;

public class GestureDoubleTap<T> extends GestureDetector.SimpleOnGestureListener {

    public static final int DOUBLE_TAP_MILLISECONDS = 500;
    private Bus _bus;
    private T _object;
    private long _lastPressTime;

    public GestureDoubleTap(Bus bus, T object) {
        _bus = bus;
        _object = object;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        long pressTime = System.currentTimeMillis();
        long elapsedTime = pressTime - _lastPressTime;
        if (elapsedTime <= DOUBLE_TAP_MILLISECONDS) {
            doEvent();
            return true;
        }
        _lastPressTime = pressTime;
        return true;
    }

    private void doEvent() {
        getBus().post(this);
    }

    public Bus getBus() {
        return _bus;
    }

    public T getObject() {
        return _object;
    }
}
