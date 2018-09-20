package com.neighborpil.samplelinearlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Button button01 = new Button(this);
        button01.setText("Button 01");
        button01.setLayoutParams(params);
        mainLayout.addView(button01);

        LinearLayout rightSubLayout = new LinearLayout(this);
        rightSubLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightSubLayout.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        rightSubLayout.setGravity(17); // 3 : left, 17 : center, 5 : right


        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        Button button02 = new Button(this);
        button02.setText("bt2n2");
        button02.setLayoutParams(params2);
        Button button03 = new Button(this);
        button03.setText("btn3");
        button03.setLayoutParams(params2);
        rightSubLayout.addView((button02));
        rightSubLayout.addView((button03));
        mainLayout.addView((rightSubLayout));

        setContentView(mainLayout);
    }
}
