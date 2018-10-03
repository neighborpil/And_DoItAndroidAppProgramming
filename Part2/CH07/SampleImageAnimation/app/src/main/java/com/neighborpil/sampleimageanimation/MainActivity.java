package com.neighborpil.sampleimageanimation;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    ImageSwitcher switcher;
    Handler mHandler = new Handler();
    ImageThread thread;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });

        switcher = findViewById(R.id.switcher);
        switcher.setVisibility(View.INVISIBLE);

        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setBackgroundColor(0xFF000000);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return  imageView;
            }
        });
    }

    private void startAnimation(){
        switcher.setVisibility(View.VISIBLE);
        thread = new ImageThread();
        thread.start();
    }

    private void stopAnimation(){
        running = false;
        try{
            thread.join();
        }catch (InterruptedException ex){}
    }

    class ImageThread extends Thread{
        int duration = 250;
        final int imageId[] = {
                R.drawable.emo_im_crying,
                R.drawable.emo_im_happy,
                R.drawable.emo_im_laughing,
                R.drawable.emo_im_surprised
        };
        int currentIndex = 0;

        @Override
        public void run() {
            running = true;
            while (running){
                synchronized (this){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            switcher.setImageResource(imageId[currentIndex]);
                        }
                    });

                    currentIndex++;
                    if(currentIndex == imageId.length)
                        currentIndex = 0;
                    try{
                        Thread.sleep(duration);
                    } catch (InterruptedException ex){}
                }
            }
        }
    }
}
