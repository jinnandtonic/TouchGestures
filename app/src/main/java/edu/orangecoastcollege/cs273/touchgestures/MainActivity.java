package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Member variable to detect gestures
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    protected void clearAll(View view) {
        singleTaps = 0; doubleTaps = 0; longPresses = 0; scrolls = 0; flings = 0;
        gesturesLogTextView.setText("");
        singleTapTextView.setText(R.string.zero);
        doubleTapTextView.setText(R.string.zero);
        longPressTextView.setText(R.string.zero);
        scrollTextView.setText(R.string.zero);
        flingTextView.setText(R.string.zero);
    }

    /**
     * Sends the touch event to all the children in the ViewGroup
     * e.g. ScrollView down to the TextView
     * @param ev The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * Handles a single-tap gesture. Not part of a double-tap
     * @param motionEvent The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        // Display the message, increment the counter
        gesturesLogTextView.append("\nonSingleTap gesture occurred");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     * Responds to a double-tap gesture. Double-tap is the succession of
     * two double-taps within a duration
     * @param motionEvent The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap gesture occurred");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double-tap, another event occurs (including a move)
     * @param motionEvent The motion event triggering the touch
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    /**
     * User made contact with device.
     * Every gesture begins with onDown.
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown gesture occurred");
        return true;
    }

    /**
     * Down event where user does not let go, short duration of time
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress gesture occurred");
    }

    /**
     * Similar to onSingleTapConfirmed, but it could be part of a double-tap
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp gesture occurred");
        return true;
    }

    /**
     * Down event followed by a press and a lateral movement without relinquishing contact
     * with device
     * @param motionEvent The event where the scroll originated
     * @param motionEvent1 The event where the scroll stopped
     * @param distanceX The distance in X direction (pixels)
     * @param distanceY The distance in Y direction (pixels)
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1,
                            float distanceX, float distanceY) {
        gesturesLogTextView.append("\nScroll gesture occurred, distanceX ="
                + distanceX + ", distanceY" + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down event, followed by a long hold
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonLongPress gesture occurred");
    }

    /**
     * Similar to a scroll but with faster velocity and use releases contact with device
     * @param motionEvent
     * @param motionEvent1
     * @param v Velocity in x-direction (pixels per second)
     * @param v1 Velocity in y-direction (pixels per second)
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nScroll gesture occurred, velocityX ="
                + v + ", velocityY" + v1);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }
}
