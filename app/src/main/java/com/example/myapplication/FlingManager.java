package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class FlingManager implements View.OnTouchListener {

    private static final int THRESHOLD = 180;
    private final Context context;
    GestureDetector gestureDetector;

    public FlingManager(Context context) {
        this.context = context;
        gestureDetector = new GestureDetector(context, new swipLeftGesture());
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        //Log.e("TAG", "onTouch: ");
        return true;
    }

    class swipLeftGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            Log.e("TAG", "onFling: ");
            if (e1.getX() - e2.getX() > THRESHOLD) swipLeft();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public boolean swipLeft() {
        return true;
    }

}
