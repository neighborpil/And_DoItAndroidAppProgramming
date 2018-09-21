package com.neighborpil.samplelifecycle;

import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY_SIMPLE_DATA = "data";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        textView = (TextView)findViewById(R.id.textView);


        Intent intent = getIntent();
        processIntent(intent);

    }

    private void processIntent(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA);

            StringBuilder sb = new StringBuilder();
            sb.append("전달받은 데이터 : \n");
            sb.append("name : ");
            sb.append(data.getNumber());
            sb.append("\n");
            sb.append("message : ");
            sb.append(data.getMessage());
            textView.setText(sb.toString());
        }
    }

    public void onBtnBackClicked(View v){
        Intent intent = new Intent();
        intent.putExtra("name", "mike");
        setResult(RESULT_OK, intent);
        finish();
    }


}
