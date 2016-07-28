package org.otfusion.caturday.view.common.gestures;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.otfusion.caturday.view.common.events.VoteCatEvent;

public class DoubleTapGesture extends GestureDetector.SimpleOnGestureListener {

    private VoteCatEvent event;

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        boolean isEventReady = event != null;
        if (isEventReady) {
            event.executeEvent("double tap - tapity tap tap");
        }
        return isEventReady;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("EVENT", "double tap - tapity tap tap");
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    public void setEvent(VoteCatEvent event) {
        this.event = event;
    }

}
