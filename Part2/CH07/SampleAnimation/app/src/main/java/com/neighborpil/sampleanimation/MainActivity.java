package com.neighborpil.sampleanimation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //View rootView;
    ImageView swingImage, waterImage, skyImage;
    Animation shakeAnimation, dropAnimation, flowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //swing
        swingImage = findViewById(R.id.swingImage);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        swingImage.setAnimation(shakeAnimation);

        //water
        waterImage = findViewById(R.id.waterImage);
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        waterImage.setAnimation(dropAnimation);

        //sky
        skyImage = findViewById(R.id.skyImage);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        skyImage.setAnimation(flowAnimation);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sky_background);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        ViewGroup.LayoutParams params = skyImage.getLayoutParams();
        params.width = bitmapWidth;
        params.height = bitmapHeight;

        skyImage.setImageBitmap(bitmap);

        flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Toast.makeText(getApplicationContext(), "onWindowFocusChanged : " + hasFocus, Toast.LENGTH_LONG).show();

        if(hasFocus){
            shakeAnimation.start();
            dropAnimation.start();
            flowAnimation.start();
        } else{
            shakeAnimation.reset();
            dropAnimation.reset();
            flowAnimation.reset();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(getApplicationContext(), "Attached", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        Toast.makeText(getApplicationContext(), "Detached", Toast.LENGTH_LONG).show();
    }

    private final class AnimationAdapter implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation ended", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
