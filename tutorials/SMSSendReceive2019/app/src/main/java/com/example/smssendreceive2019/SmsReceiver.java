package com.example.smssendreceive2019;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        System.out.println("Received SMS");
        Toast.makeText(context, "SMS Message Received", Toast.LENGTH_SHORT).show();
        SmsMessage[] recMsg = null;
        String str = "";
        if (bundle != null)
        {
            //---Access the received SMS message ---
            Object[] pdus = (Object[]) bundle.get("pdus");
            recMsg = new SmsMessage[pdus.length];
            for (int i=0; i<recMsg.length; i++){
                String format = bundle.getString("format");
                recMsg[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                str += "SMS Message received from: " + recMsg[i].getOriginatingAddress();
                str += "=>";
                str += recMsg[i].getMessageBody().toString();
                str += "\n";
            }
            //---display the SMS message received---
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        }
    }
}
