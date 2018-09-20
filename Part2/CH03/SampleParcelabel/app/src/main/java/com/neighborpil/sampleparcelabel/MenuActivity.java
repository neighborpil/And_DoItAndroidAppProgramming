package com.neighborpil.sampleparcelabel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView textView;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            Bundle bundle = intent.getExtras(); // intent안의 bundle객체 참조
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA); // 번들 객체 안의 SimpleData 객체 참조
            StringBuilder sb = new StringBuilder();
            sb.append("전달받은 데이터 : \n");
            sb.append("Number : " + data.number + "\n");
            sb.append("Message : " + data.message + "\n");

            textView.setText(sb.toString());
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
    }
}
