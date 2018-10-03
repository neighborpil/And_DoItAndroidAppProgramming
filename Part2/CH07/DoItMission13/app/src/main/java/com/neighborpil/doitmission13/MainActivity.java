package com.neighborpil.doitmission13;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 500;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    LinearLayout mainLayout;
    GestureDetector detector;
    ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        detector = new GestureDetector(this, new GestureAdapter());

        layouts.add(addItem("감", 19));
        layouts.add(addItem("킴", 20));

        if(!layouts.isEmpty()){
            for(LinearLayout layout : layouts){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                mainLayout.addView(layout, params);
            }
        }
    }

    private LinearLayout addItem(String name, int age) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView txtName = new TextView(this);
        txtName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        txtName.setText(name);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.weight = 1;
        params1.setMargins(0, 4, 0, 4);
        layout.addView(txtName, params1);

        TextView txtAge = new TextView(this);
        txtAge.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        txtAge.setText(String.format("%d", age));
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.weight = 2;
        params2.setMargins(0, 4, 0, 4);
        layout.addView(txtAge, params2);

        detector = new GestureDetector(this, new GestureAdapter());
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        return layout;

    }

    class GestureAdapter implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                double distanceX = Math.abs(e1.getX() - e2.getY());
                double distanceY = Math.abs(e1.getY() - e2.getY());
                if (distanceX > SWIPE_MAX_OFF_PATH || distanceY > SWIPE_MAX_OFF_PATH) {
                    Toast.makeText(getApplicationContext(),
                            Double.toString(distanceX) + "," + Double.toString(distanceY),
                            Toast.LENGTH_SHORT).show();
                    return true;
                }

                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Left swipe", Toast.LENGTH_SHORT).show();
                } // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
                } //down to up swipe
                else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Swipe UP", Toast.LENGTH_SHORT).show();
                } // up to down swipe
                else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getApplicationContext(), "Swipe Down", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
            }

            return true;
        }
    }

}
