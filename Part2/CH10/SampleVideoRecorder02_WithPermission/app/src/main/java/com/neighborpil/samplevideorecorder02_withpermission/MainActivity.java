package com.neighborpil.samplevideorecorder02_withpermission;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private static String EXTERNAL_STORAGE_PATH = "";
    private static String RECORDED_FILE = "video_recorded";
    private static int fileIndex = 0;
    private static String filename = "";

    MediaPlayer player;
    MediaRecorder recorder;

    private Camera camera = null;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragments();


        // check external storage
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "External Storage Media is not mounted.");
        } else {
            EXTERNAL_STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        // create a SurfaceView instance and add it to the layout
        SurfaceView surface = new SurfaceView(this);
        holder = surface.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        FrameLayout frame = (FrameLayout) findViewById(R.id.videoLayout);
        frame.addView(surface);


        Button btnRecord = (Button) findViewById(R.id.btnRecord);
        Button btnRecordStop = (Button) findViewById(R.id.btnRecordStop);
        Button btnOpenMap = (Button) findViewById(R.id.btnOpenMap);
        Button btnCloseMap = (Button) findViewById(R.id.btnOpenMap);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (recorder == null) {
                        recorder = new MediaRecorder();
                    }

                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

                    filename = createFilename();
                    Log.d(TAG, "current filename : " + filename);
                    recorder.setOutputFile(filename);

                    recorder.setPreviewDisplay(holder.getSurface());
                    recorder.prepare();
                    recorder.start();

                } catch (Exception ex) {
                    Log.e(TAG, "Exception : ", ex);

                    recorder.release();
                    recorder = null;
                }
            }
        });

        btnRecordStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recorder == null)
                    return;

                recorder.stop();
                recorder.reset();
                recorder.release();
                recorder = null;

                ContentValues values = new ContentValues(10);
                values.put(MediaStore.MediaColumns.TITLE, "RecordedVideo");
                values.put(MediaStore.Audio.Media.ALBUM, "Video Album");
                values.put(MediaStore.Audio.Media.ARTIST, "SamwooImmersion");
                values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Video");
                values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
                values.put(MediaStore.Audio.Media.DATA, filename);

                Uri videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
                if (videoUri == null) {
                    Log.d("SampleVideoRecorder", "Video insert failed.");
                    return;
                }
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, videoUri));
            }
        });


        checkDangerousPermissions();
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int i = 0; i < permissions.length; i++){
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if(permissionCheck == PackageManager.PERMISSION_DENIED)
                break;
        }

        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        else{
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]))
                Toast.makeText(this, "권한이 없으면 사용 할 수 없습니다", Toast.LENGTH_LONG).show();
            else
                ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1){
            for(int i = 0; i < permissions.length; i++){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, permissions[i] + "Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, permissions[i] + "Denied", Toast.LENGTH_SHORT).show();
            }
        }

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }



    private String createFilename() {
        fileIndex++;
        String newFilename = "";
        if (EXTERNAL_STORAGE_PATH == null || EXTERNAL_STORAGE_PATH.equals(""))
            newFilename = RECORDED_FILE + fileIndex + ".mp4";
        else
            newFilename = EXTERNAL_STORAGE_PATH + "/" + RECORDED_FILE + fileIndex + ".mp4";

        return newFilename;
    }

    @Override
    protected void onPause() {

        if(camera != null){
            camera.release();
            camera = null;
        }

        if(recorder != null){
            recorder.release();
            recorder = null;
        }

        if(player != null){
            player.release();
            player = null;
        }


        super.onPause();
    }

    public void addFragments(){

        fragmentMap = new FragmentMap();
        getSupportFragmentManager().beginTransaction().add(R.id.videoLayout, fragmentMap).commit();
    }
    FragmentMap fragmentMap;
    public void onBtnOpenMapClicked(View view){

        fragmentMap.setVisibilityOn();
    }

    public void onBtnCloseMapClicked(View view){
        fragmentMap.setVisibilityOff();

    }


}
