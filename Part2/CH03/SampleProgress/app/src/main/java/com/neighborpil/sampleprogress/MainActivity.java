package com.neighborpil.sampleprogress;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    private int brightness = 100;
    TextView seekBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(80);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("데이터를 확인하는 중입니다");
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });

        // seekbar
        seekBarText = (TextView)findViewById(R.id.seekBarText);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout seekBarPanel = (LinearLayout)findViewById(R.id.seekBarPanel);
                seekBarPanel.setVisibility(View.VISIBLE);
            }
        });
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrightness(progress);
                changeSeekBarMessage(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private  void changeSeekBarMessage(int value){
        if(value < 10){
            value = 10;
        } else if(value > 100){
            value = 100;
        }

        String text = value + "%";
        seekBarText.setText(text);
    }

    private void showProgressDialog(String message){
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.show();
    }

    private void setBrightness(int value){
        if(value < 10){
            value = 10;
        } else if(value > 100){
            value = 100;
        }
        brightness = value;

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value /100;
        getWindow().setAttributes(params);
    }
}
