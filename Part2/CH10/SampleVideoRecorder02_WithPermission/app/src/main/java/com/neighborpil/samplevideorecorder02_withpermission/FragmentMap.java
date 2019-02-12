package com.neighborpil.samplevideorecorder02_withpermission;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FragmentMap extends Fragment {

    ImageView imageView;
    LinearLayout linearContainer;
    int imageIndex = 0;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragmentmap, container, false);
        linearContainer = rootView.findViewById(R.id.container);
        imageView = rootView.findViewById(R.id.imageView);
        imageIndex = 0;
        return rootView;
    }

    public void onBtnNextClicked(View view){
        Resources res = getResources();
        if(imageIndex == 0){

            BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.cat2);
            changeImage(bitmap);
            imageIndex = 1;
        } else if(imageIndex == 1){
            BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.cat1);
            changeImage(bitmap);
            imageIndex = 0;
        }


    }

    private void changeImage(BitmapDrawable bitmap){
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void setVisibilityOn(){

        linearContainer.setVisibility(View.VISIBLE);
    }

    public void setVisibilityOff(){
        linearContainer.setVisibility(View.GONE);
    }
}
