package com.neighborpil.doitmission04;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.TypedValue;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMessage;
    TextView tvTextLength;
    private boolean exceeded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.etMessage);
        tvTextLength = (TextView) findViewById(R.id.tvTextLength);

        etMessage.addTextChangedListener(new MyWatcher());

    }

    public class MyWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            changeTextCount(s);
            changeTextColorWhenExceed80(s);
        }
    }

    private void changeTextCount(Editable s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.length());
        sb.append(" / 80 바이트");

        tvTextLength.setText(sb.toString());
    }

    private void changeTextColorWhenExceed80(Editable s) {

        if (s.length() > 80) {
            if (exceeded != true) {
                Toast.makeText(getApplicationContext(), "Exceed 80 letters", Toast.LENGTH_LONG).show();
                exceeded = true;
            }
            tvTextLength.setTextColor(Color.RED);
        } else// if(s.length() <= 80)
            exceeded = false;
    }


    public void onBtnSendClicked(View v) {
        Toast.makeText(getApplicationContext(), etMessage.getText(), Toast.LENGTH_LONG).show();
    }

    public void onBtnCloseClicked(View v) {
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int width = etMessage.getWidth(); // 반환값은 px
        etMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);
        float korSize = etMessage.getPaint().measureText("한");
        etMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, width / 8 / (korSize / 100));
        // 좀 이상하게 나온다
    }
}
