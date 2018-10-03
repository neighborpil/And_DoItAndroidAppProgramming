package com.neighborpil.samplethreadanimation;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ArrayList<Drawable> drawableList = new ArrayList<>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        startAnimation();
    }

    private void startAnimation( ){
        Resources res = getResources();
        Drawable d = res.getDrawable(R.drawable.emo_im_laughing);

        drawableList.add(d);
        drawableList.add(res.getDrawable(R.drawable.emo_im_crying));
        drawableList.add(res.getDrawable(R.drawable.emo_im_happy));
        drawableList.add(res.getDrawable(R.drawable.emo_im_sad));
        drawableList.add(res.getDrawable(R.drawable.emo_im_surprised));

        AnimThread thread = new AnimThread();
        thread.start();
    }

    class AnimThread extends  Thread{
        @Override
        public void run() {
            int index = 0;
            for(int i = 0; i < 100; i++){
                final Drawable drawable = drawableList.get(index);
                index += 1;
                if(index > 4)
                    index = 0;


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try{
                    Thread.sleep(500);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}