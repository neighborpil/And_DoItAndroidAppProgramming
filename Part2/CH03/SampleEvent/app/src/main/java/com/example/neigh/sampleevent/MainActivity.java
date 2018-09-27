package com.example.neigh.sampleevent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        View view = findViewById(R.id.view);
        view.setOnTouchListener(new OnMyTouchListener());

        setGestureDetector();

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 시스템 back 버튼을 눌러씅ㄹ 때 토스트 메세지 보여주기
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "시스템이 back 버튼을 눌렀습니다.",  Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public  void setGestureDetector(){
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨");

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 : " + distanceX + ", " + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress 호출됨");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 : " +  velocityX + ", " + velocityY);
                return true;
            }
        });
    }

    public class OnMyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {

            int action = motionEvent.getAction();
            float curX = motionEvent.getX();
            float curY = motionEvent.getY();

            if (action == MotionEvent.ACTION_DOWN) {
                println("손가락 눌림 : " + curX + ", " + curY);
            } else if (action == MotionEvent.ACTION_MOVE) {
                println("손가락 움직임 : " + curX + ", " + curY);
            } else if (action == MotionEvent.ACTION_UP) {
                println("손가락 뗌 : " + curX + ", " + curY);
            }

            return true;
        }

    }


    public void println(String data) {
        textView.setText(data + "\n");
    }
}

