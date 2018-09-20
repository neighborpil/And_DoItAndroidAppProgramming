package com.neighborpil.samplescrollview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView)findViewById(R.id.scrollView);
        imageView = (ImageView)findViewById(R.id.imageView);
        //scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.apple);
        changeImage(bitmap);
    }

    public void onButton1Clicked(View v){
        Resources res = getResources();
        BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.apple2);

        changeImage(bitmap);
    }

    private  void changeImage(BitmapDrawable bitmap){
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onBtnBackClicked(View v){
        Toast.makeText(getApplicationContext(), "돌아가기1", Toast.LENGTH_LONG).show();
        finish();
    }
}
