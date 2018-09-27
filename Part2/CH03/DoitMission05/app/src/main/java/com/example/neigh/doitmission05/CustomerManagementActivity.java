package com.example.neigh.doitmission05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CustomerManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);
    }

    public void onBtnMenuClicked(View v){
        Button button = (Button)findViewById(R.id.btnMenu);
        String name = button.getText().toString();

        setResult(name);
    }

    private void setResult(String name) {
        Intent intent = new Intent();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBtnLoginClicked(View v){
        Button button = findViewById(R.id.btnLogin);
        String name = button.getText().toString();

        setResult(name);
    }
}
