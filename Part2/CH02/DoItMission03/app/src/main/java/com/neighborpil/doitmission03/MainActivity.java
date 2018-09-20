package com.neighborpil.doitmission03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public enum ImageVisibility{
        Up,
        Down
    }

    ImageView imageViewUp;
    ImageView imageViewDown;
    ImageVisibility visibility = ImageVisibility.Up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewUp = (ImageView)findViewById(R.id.ivUp);
        imageViewDown = (ImageView)findViewById(R.id.ivDown);

        changeVisibility(ImageVisibility.Up);
    }

    public void onBtnUpClicked(View v){
        changeVisibility(ImageVisibility.Up);
    }

    public void onBtnDownClicked(View v){
        changeVisibility(ImageVisibility.Down);
    }

    private void changeVisibility(ImageVisibility visibility){
        switch (visibility){
            case Up:
                imageViewUp.setVisibility(View.VISIBLE);
                imageViewDown.setVisibility(View.INVISIBLE);
                break;
            case Down:
                imageViewUp.setVisibility(View.INVISIBLE);
                imageViewDown.setVisibility(View.VISIBLE);
                break;
        }
    }
}
