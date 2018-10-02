package com.neighborpil.sampledelayed;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.textView);

        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void request(){
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
        AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        dialog.show();

        textView.setText("원격 요청중");
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message,
                                          CharSequence titleButtonYes, CharSequence titleButtonNo){
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                RequestHandler handler = new RequestHandler();
                handler.sendEmptyMessageDelayed(0, 20);
            }
        });
        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return  requestDialog.show();
    }

    class RequestHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            for(int i = 0; i < 10; i++){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    Log.e("RequestHandler", ex.getMessage(), ex);
                }
            }
            textView.setText("원격 데이터 요청 완료");
        }
    }
}
