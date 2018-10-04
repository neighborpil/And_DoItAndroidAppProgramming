package com.neighborpil.tcpsockettest01;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText input01;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = findViewById(R.id.input01);
        handler = new Handler();


        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                sendMessage(addr);

            }
        });
    }

    private void sendMessage(String hostname){
        Thread thread = new Thread(new ConnectRunnable(hostname));
        thread.start();
    }

    class ConnectRunnable implements Runnable{
        String hostname;

        public ConnectRunnable(String hostName){
            this.hostname = hostName;
        }

        @Override
        public void run() {
            try{
                int port = 5001;
                Socket socket = new Socket(hostname, port);
                //ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("Hello Andoird");
                outputStream.flush();

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String message = inputStream.readUTF();
                handler.post(new ShowMainRunnable(message));

                socket.close();


            }catch (Exception ex1){
                ex1.printStackTrace();
            }
        }
    }

    class ShowMainRunnable implements  Runnable{

        String message;

        public ShowMainRunnable(String message){
            this.message = message;
        }


        @Override
        public void run() {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        }
    }
}
