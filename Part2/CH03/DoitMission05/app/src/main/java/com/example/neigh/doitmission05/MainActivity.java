package com.example.neigh.doitmission05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnLogin);
    }

    public void onBtnLoginClicked(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("name", btnLogin.getText());
        startActivityForResult(intent, RequestCodes.MenuActivity.getValue());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RequestCodes.MenuActivity.getValue()){
            if(resultCode == RESULT_OK){
                String message = data.getStringExtra("name");
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
