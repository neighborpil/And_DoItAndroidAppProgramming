package com.example.neigh.sampletoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View v){
        try{
            Toast toastView = Toast.makeText(this, "위치가 바뀐 토스트 메세지입니다.",
                    Toast.LENGTH_LONG);
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());

            toastView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, xOffset, yOffset);
            toastView.show();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public void onButton2Clicked(View v){
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toastborder,
                (ViewGroup)findViewById(R.id.))
    }
}
