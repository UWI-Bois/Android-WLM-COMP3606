package sta.uwi.edu.comp3606_practical5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

import static sta.uwi.edu.comp3606_practical5.MainActivity.SentRiddles;
import static sta.uwi.edu.comp3606_practical5.MainActivity.getHistoryByRiddle;
import static sta.uwi.edu.comp3606_practical5.MainActivity.riddlesHistory;

public class SmsReceiver extends BroadcastReceiver implements Serializable
{
    enum result {WON, ALMOST, LOST};
    public static ArrayList<Response> responses = new ArrayList<>();
    public static int responseSize = 0;

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
            for (int i=0; i<recMsg.length; i++)
            {
                String format = bundle.getString("format");
                recMsg[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                str += "SMS Message received from: " + recMsg[i].getOriginatingAddress();
                str += "=>";
                str += recMsg[i].getMessageBody().toString();
                str += "\n";
            }
            String Ans = recMsg[recMsg.length-1].getMessageBody().toString();
            String phoneNo = recMsg[recMsg.length-1].getOriginatingAddress();
            responses.add(new Response(phoneNo, Ans));
            responseSize++;
            HandleRiddles(phoneNo,Ans);
            //---display the SMS message received---
            Toast.makeText(context, recMsg[recMsg.length-1].getOriginatingAddress(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleRiddles(String phoneNo, String Ans)
    {
        for (int i = 0; i< SentRiddles.size(); i++)
        {
            if (SentRiddles.get(i).getPhoneNo().equals(phoneNo))
            {
                String msg;
                if(SentRiddles.get(i).checkAnswer(Ans) == 1)
                {
                    int rid = SentRiddles.get(i).getRiddleid();
                    for(Riddle r : riddlesHistory) if (r.getId() == rid)r.updateSolvedCount();
                    msg =  msgStringBuilder(result.WON, SentRiddles.get(i).getRiddle());
                    SentRiddles.remove(i);
                }
                else if(SentRiddles.get(i).checkAnswer(Ans) == 0) msg = msgStringBuilder(result.ALMOST, SentRiddles.get(i).getRiddle());
                else msg = msgStringBuilder(result.LOST, SentRiddles.get(i).getRiddle());
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                break;
            }
        }
    }

    private String msgStringBuilder(result r, String riddle)
    {
        String msg = "";
        int sc = getHistoryByRiddle(riddle).getSolvedCount();
        String position = intToOrdinal(sc);
        switch (r)
        {
            case WON:
                msg = "Riddle: " + riddle + "\nCorrect answer! You won!!!\nYou've placed " + position + " on the battlefield.\n";
                responses.get(responseSize-1).setStatus("WON");
                break;
            case ALMOST:
                msg =  "Riddle: " + riddle + "\nSorry, close but not quite.";
                responses.get(responseSize-1).setStatus("ALMOST");
                break;
            case LOST:
                msg = "Riddle: " + riddle + "\nWrong answer try again!";
                responses.get(responseSize-1).setStatus("LOST");
                break;
        }
        return msg;
    }

    private String intToOrdinal(int i)
    {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }
}
