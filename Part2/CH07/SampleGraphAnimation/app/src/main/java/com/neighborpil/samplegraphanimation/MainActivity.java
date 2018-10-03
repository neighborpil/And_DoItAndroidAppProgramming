package com.neighborpil.samplegraphanimation;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    Resources res;
    Animation growAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        growAnim = AnimationUtils.loadAnimation(this, R.anim.grow);
        mainLayout = findViewById(R.id.mainLayout);

        addItem("Apple", 80);
        addItem("Orange", 100);
        addItem("Kiwi", 40);
    }

    private void addItem(String name, int value){

        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // 텍스트 뷰
        TextView textView = new TextView(this);
        textView.setText(name);
        params.width = 240;
        params.setMargins(0, 4, 0, 4);
        itemLayout.addView(textView, params);

        // 프로그래스바 추가
        ProgressBar proBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        proBar.setIndeterminate(false); // 무한반복할지
        proBar.setMax(100);
        proBar.setAnimation(growAnim);
        proBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        //proBar.setProgressTintList(android.content.res.ColorStateList.);
        //proBar.setBackgroundColor(0xffff00ff);
        params2.height = 80;
        params2.width = value * 5;
        params2.gravity = Gravity.LEFT;

        itemLayout.addView(proBar, params2);

        mainLayout.addView(itemLayout, params3);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Toast.makeText(this, "onWindowFocusChange : " + hasFocus, Toast.LENGTH_LONG).show();

        if(hasFocus){
            growAnim.start();
        } else{
            growAnim.reset();
        }
    }
}
