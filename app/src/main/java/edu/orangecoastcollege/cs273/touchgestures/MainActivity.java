package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * The controller 
 */
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

    /**
     * Initializes the views so that the user can interact with the device and UI.
     * @param savedInstanceState Loads a saved instance if there is one provided.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubletapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Clears all of the TextViews by setting them to "0". Sets all the variables to 0. Sets the
     * log to empty string.
     * @param view The button event that clears the views.
     */
    public void clearAll(View view){
        gesturesLogTextView.setText("");

        singleTapTextView.setText(getString(R.string.zero));
        doubleTapTextView.setText(getString(R.string.zero));
        longPressTextView.setText(getString(R.string.zero));
        scrollTextView.setText(getString(R.string.zero));
        flingTextView.setText(getString(R.string.zero));

        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
    }

    /**
     * Sends the touch event to all the children in ViewGroup:
     * e.g. ScrollView down to the TextView
     * @param ev The motion event triggering the touch.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev); // Uses custom the detector, instead of built-in super one.
    }

    /**
     * Handles a single-tap gesture. Not part of double-tap.
     * @param motionEvent The motion event triggering the gesture.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        // Display the message and increment the counter.
        gesturesLogTextView.append("\nonSingleTap gesture occurred.");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     * Responds to a double-tap gesture. Double-tap is the succession of two single tap
     * gestures within a certain duration.
     * @param motionEvent The event where two single-taps occur in succession.
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap gesture occurred.");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double tap, another event occurs (including move).
     * @param motionEvent The event where two single-taps occur in succession and far from each other.
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {return false;}

    /**
     * User made initial contact with device.
     * Every gesture begins with onDown.
     * @param motionEvent The event where the touch event goes down.
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown gesture occurred.");
        return true;
    }

    /**
     * Down event where user does not let go, short duration of time.
     * @param motionEvent The event where a single-tap is held for a short period of time.
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nOnShowPress gesture occurred");
    }

    /**
     * Similar to a onSingleTapConfirmed, but it could be part of a double-tap.
     * @param motionEvent The event where the single-tap is released.
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp gesture occurred.");
        return true;
    }

    /**
     * Down event, followed by a press and a lateral movement, without
     * relinquishing contact with the device.
     * @param motionEvent The event where scroll started.
     * @param motionEvent1 The event where the scroll stopped.
     * @param distanceX The distance in X direction (pixels)
     * @param distanceY The distance in Y direction (pixels)
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nonScroll gesture occurred, distanceX = " + distanceX + ", distanceY = " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down event, followed by a long hold.
     * @param motionEvent The event where the single-tap is held and then released after a certain period of time.
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonLongPress gesture occurred.");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    /**
     * Similar to a scroll, with faster velocity and user releases contact with the device.
     * @param motionEvent The event where fling started.
     * @param motionEvent1 The event where the fling stopped.
     * @param v Velocity in x-direction(pixels/second).
     * @param v1 Velocity in y-direction(pixels/second).
     * @return True if the event is handled, false otherwise.
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nonFling gesture occurred, velocityX =" + v + ", velocityY = " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }
}
