package com.neighborpil.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.webkit.ConsoleMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive() 메소드 호출됨.");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            String content = messages[0].getMessageBody().toString();
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            writeLogs(messages, sender, content, receivedDate);
            sendToActivity(context, sender, content, receivedDate);
        }
    }

    private void writeLogs(SmsMessage[] messages, String sender, String content, Date receivedDate) {
        Log.i(TAG, "Message Length: " + messages.length);

        // SMS 발신 번호 확인
        Log.i(TAG, "SMS Sender: " + sender);
        // SMS 메시지 확인
        Log.i(TAG, "SMS Content: " + content);
        // SMS 수신시간 확인
        Log.i(TAG, "SMS Received Date: " + receivedDate.toString());
    }

    private void sendToActivity(Context context, String sender, String content, Date receivedDate){
        // 메시지를 보여줄 액티비티
        Intent myIntent = new Intent(context, SmsActivity.class);

        // 플래그를 이용
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                          Intent.FLAG_ACTIVITY_SINGLE_TOP |
                          Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("content", content);
        myIntent.putExtra("receivedDate", format.format(receivedDate));
        context.startActivity(myIntent);
    }

    /**
     * 인텐트 안에 들어있는 SMS 메세지 파싱
     *
     * @param bundle
     * @return
     */
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // API 23 이상
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
