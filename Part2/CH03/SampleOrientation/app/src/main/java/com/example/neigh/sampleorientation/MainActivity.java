package com.example.neigh.sampleorientation;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showToast("onCreate 호출됨", ToastLength.Long);
        editText = (EditText)findViewById(R.id.editText);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                Toast.makeText(getApplicationContext(), "값을 복원했습니다. : " + name,
                        Toast.LENGTH_LONG).show();
            }
        });
        // 저장되어 있던 값 복원
        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            editText.setText(name);
            Toast.makeText(getApplicationContext(), "값을 복원했습니다 : " + name,
                     Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //showToast("onStart 호출됨", ToastLength.Long);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //showToast("onStop 호출됨", ToastLength.Long);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //showToast("onDestroy 호출됨", ToastLength.Long);
    }

    public void showToast(String data, ToastLength length){
        /*textView.setText(data);*/
        Toast.makeText(getApplicationContext(), data,
                length == ToastLength.Long ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("방향 : ORIENTATION_LANDSCAPE", ToastLength.Long);
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("방향 : ORIENTATION_PORTRAIT", ToastLength.Long);
        }
    }

    public enum ToastLength{
        Long, Short
    }
}
