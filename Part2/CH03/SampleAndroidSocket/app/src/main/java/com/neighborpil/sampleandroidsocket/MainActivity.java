package com.neighborpil.sampleandroidsocket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText input01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input01 = findViewById(R.id.input01);

        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                ConnectThread thread = new ConnectThread(addr);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread{
        String hostname;

        public ConnectThread(String address){
            hostname = address;
        }

        @Override
        public void run() {
            try{
                int port = 5001;

                Socket socket = new Socket(hostname, port);
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject("Hello Andoird");
                outstream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String obj = (String)inputStream.readObject();
                Log.e("MainActivity", "서버에서 온 메시지 : " + obj);
                //Toast.makeText(getApplicationContext(), obj, Toast.LENGTH_LONG).show();
                socket.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
