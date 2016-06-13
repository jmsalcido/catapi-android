package org.otfusion.caturday.ui.views;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.otfusion.caturday.events.VoteCatEvent;
import org.otfusion.caturday.ui.gestures.DoubleTapGesture;

public class ImageDoubleTapView extends AppCompatImageView {

    private GestureDetector mDetector;
    private DoubleTapGesture mGestureListener;

    public ImageDoubleTapView(Context context) {
        super(context);
        initView(context);
    }

    public ImageDoubleTapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImageDoubleTapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mGestureListener = new DoubleTapGesture();
        mDetector = new GestureDetector(context,  mGestureListener);
    }

    public void setEvent(VoteCatEvent event) {
        mGestureListener.setEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
}
