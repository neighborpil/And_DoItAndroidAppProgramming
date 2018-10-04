package com.neighborpil.tcpsockettest02;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText etMessage;
    EditText input01;
    Handler handler;
    String message;
    boolean isRunning = true;
    String receivedMessage;
    Thread thread;
    ConnectRunnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMessage = findViewById(R.id.etMessage);
        input01 = findViewById(R.id.input01);
        handler = new Handler();


        String addr = input01.getText().toString().trim();
        runnable = new ConnectRunnable(addr);
        thread = new Thread(runnable);
        thread.start();
        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread testThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String sendMessage = etMessage.getText().toString().trim();
                        runnable.sendMessage(new Date() + sendMessage);
                    }
                });
                testThread.start();


            }
        });
    }

    class ConnectRunnable implements Runnable {
        String hostname;
        String sendMessage;

        public ConnectRunnable(String hostname) {
            this.hostname = hostname;
        }

        public void sendMessage(String sendMessage) {
            this.sendMessage = sendMessage;
        }

        @Override
        public void run() {
            try {
                int port = 5001;
                Socket socket = new Socket(hostname, port);
                ShowMainRunnalbe showMainRunnalbe = new ShowMainRunnalbe();
                while (isRunning) {
                    if(sendMessage != null){
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                        outputStream.writeUTF(sendMessage);
                        outputStream.flush();

                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        String message = inputStream.readUTF();
                        receivedMessage = message;
                        handler.post(showMainRunnalbe);
                        this.sendMessage = null;
                    } else
                        Thread.sleep(100);
                }
                socket.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ShowMainRunnalbe implements Runnable {

        @Override
        public void run() {
            if (!receivedMessage.isEmpty()) {
                Toast.makeText(getApplicationContext(), receivedMessage, Toast.LENGTH_LONG).show();
            }
        }
    }
}
