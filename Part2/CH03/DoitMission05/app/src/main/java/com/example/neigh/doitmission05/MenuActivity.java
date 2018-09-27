package com.example.neigh.doitmission05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        showReceivedMessage(intent);
    }

    private void showReceivedMessage(Intent intent){
        String name = intent.getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    public void onBtnProductManagementClicked(View v){
        Button btnProductManagement = (Button)findViewById(R.id.btnProductManagement);
        String name = btnProductManagement.getText().toString();
        sendResult(name);
    }

    public void onBtnSalesManagementClicked(View v){
        Button btnSalesManagement = (Button)findViewById(R.id.btnSalesManagement);
        String name = btnSalesManagement.getText().toString();
        sendResult(name);
    }

    public void onBtnCustomerManagementClicked(View v){
        Button btnCustomerManagement = (Button)findViewById(R.id.btnCustomerManagement);
        String name = btnCustomerManagement.getText().toString();
        /*sendResult(name);*/

        Intent intent = new Intent(this, CustomerManagementActivity.class);
        intent.putExtra("name", name);
        startActivityForResult(intent, RequestCodes.CustomerManagementActivity.getValue());
    }

    private void sendResult(String name){
        Intent intent = new Intent();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RequestCodes.CustomerManagementActivity.getValue()){
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                String menu = getString(R.string.btnMenu);
                String login = getString(R.string.btnLogin);
                if(name.equals(menu)){
                    Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                } else if (name.equals(login)) {

                    finish();
                }

            }
        }
    }
}
