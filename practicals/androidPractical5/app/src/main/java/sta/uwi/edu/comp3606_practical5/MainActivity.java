package sta.uwi.edu.comp3606_practical5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText phoneno_et, riddle_et, solution_et;
    Button send_b;
    Button respBtn, btn2;
    public static ArrayList<Riddle_plus_Target> SentRiddles;
    public static ArrayList<Riddle> riddlesHistory = new ArrayList<>();
    public SmsReceiver smsr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        send_b = findViewById(R.id.send);
        respBtn = findViewById(R.id.respBtn);

        phoneno_et = findViewById(R.id.phoneno_et);
        riddle_et = findViewById(R.id.riddle_et);
        solution_et = findViewById(R.id.solution_et);

        SentRiddles = new ArrayList<>();
        smsr = new SmsReceiver();

        respBtn.setOnClickListener(new Button_Clicker());
        send_b.setOnClickListener(new Button_Clicker());
        //btn2.setOnClickListener(new Button_Clicker());

    }

    class Button_Clicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if (v == respBtn){
                Context context = getApplicationContext();
                Intent i = new Intent(context, ListViewActivity1.class);
                i.putExtra("smsr", smsr);
                startActivity(i);
            }
            if(v == send_b){
                String phoneNo = phoneno_et.getText().toString();
                String riddle = riddle_et.getText().toString();
                String solution = solution_et.getText().toString();
                if(!riddle.equals("")&&!solution.equals(""))
                {
                    try
                    {
                        boolean alreadyPlaying = false;
                        for (int i = 0; i< SentRiddles.size(); i++)
                        {
                            if (SentRiddles.get(i).getPhoneNo().equals(phoneNo))
                            {
                                alreadyPlaying = true;
                                break;
                            }
                        }
                        if (!alreadyPlaying)
                        {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null,
                                    "Hello fellow champion! Think you can crack today's riddle?\n" + riddle, null, null);

                            if(getHistoryByRiddle(riddle)==null)
                            {
                                Riddle r = new Riddle(riddle,solution);
                                riddlesHistory.add(r);
                                SentRiddles.add(new Riddle_plus_Target(phoneNo,riddle,solution,r.getId()));
                                Toast.makeText(getApplicationContext(), "SMS Riddle Successfully Sent!",
                                        Toast.LENGTH_LONG).show();
                            }

                            else
                            {
                                SentRiddles.add(new Riddle_plus_Target(phoneNo,riddle,solution,getHistoryByRiddle(riddle).getId()));
                                Toast.makeText(getApplicationContext(), "SMS Riddle Successfully Sent!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else Toast.makeText(getApplicationContext(), "Error " + phoneNo + " is already working on a riddle", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),
                                "SMS Send Failed, error occurred!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else Toast.makeText(getApplicationContext(),"Error0x0: CANNOT LEAVE ABOVE FIELDS EMPTY!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public static Riddle getHistoryByRiddle(String riddle)
    {
        if(!riddlesHistory.isEmpty()) for(Riddle r : riddlesHistory) if (r.getRiddle().equals(riddle))return r;
        return null;
    }

}
