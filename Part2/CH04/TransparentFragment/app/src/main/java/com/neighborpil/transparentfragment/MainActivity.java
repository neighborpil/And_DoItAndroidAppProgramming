package com.neighborpil.transparentfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment2).commit();
    }
}
