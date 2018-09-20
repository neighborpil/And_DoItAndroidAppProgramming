package com.neighborpil.samplelayoutinflater;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new MyOnClickListener());
        setContentView(R.layout.activity_main);


    }

    public class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "버튼 눌려짐", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        return super.getSystemService(name);
    }


}
