package com.neighborpil.samplelooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    EditText editText, editText2;

    MainHandler mainHandler;
    ProcessThread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new MainHandler();
        thread1 = new ProcessThread();

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        Button processButton = findViewById(R.id.processButton);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inStr = editText.getText().toString();
                Message msgToSend = Message.obtain();
                msgToSend.obj = inStr;

                thread1.handler.sendMessage(msgToSend);
            }
        });
        thread1.start();
    }

    class  ProcessThread extends Thread{
        ProcessHandler handler;

        public ProcessThread(){
            handler = new ProcessHandler();
        }

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }

    class ProcessHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            Message resultMsg = Message.obtain();
            resultMsg.obj = msg.obj + " Hong!";
            mainHandler.sendMessage(resultMsg);
        }
    }

    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            String str = (String)msg.obj;
            editText2.setText(str);
        }
    }
}
