package com.neighborpil.sampledialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
    }

    private void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼을 눌렀습니다.";
                textView.setText(message);
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼을 눌렀습니다";
                textView.setText(message);
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니오 버튼을 눌렀습니다";
                textView.setText(message);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
