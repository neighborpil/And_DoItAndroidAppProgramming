package com.neighborpil.samplecallintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButton2Clicked(View v){
        Intent intent = new Intent();
        intent.putExtra("name", "mike");
        setResult(RESULT_OK, intent);
        finish();
    }
}
