package com.example.fileswithobjects;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
    private Button myButton, myButton2, btnLV;
    private RadioButton radio1, radio2;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                myButton = (Button) findViewById(R.id.button1);
                myButton2 = (Button) findViewById(R.id.buttonScr2);
                btnLV = findViewById(R.id.btn_lv);

                radio1 = (RadioButton) findViewById(R.id.radioButton);
                radio2 = (RadioButton) findViewById(R.id.radioButton2);
                check = (CheckBox) findViewById(R.id.checkBox);

                myButton.setOnClickListener(new Button_Clicker());
                myButton2.setOnClickListener(new Button_Clicker());
                radio1.setOnClickListener(new Button_Clicker());
                radio2.setOnClickListener(new Button_Clicker());

                check = (CheckBox)findViewById(R.id.checkBox);
                check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.d("MyApp","Check box selected");
            }
        });
//**
    }

    class Button_Clicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {

            if(v == myButton)
            {
                myButton.setText("Clicked!");
                doCalculations();
                Log.d("MyApp","I am here");
            }
            else if (v == btnLV){
                Intent i = new Intent(getApplicationContext(), List.class);
            }
            else if(v == myButton2)
            {   //Do an Intent to activate Screen 3
                myButton2.setText("Clicked. Going to new Screen ...3!");
                Context context = getApplicationContext();
                Intent intent = new Intent (context, PizzaScreen3Activity.class);
                startActivity(intent);
                Log.d("MyApp","I am trying to go to Screen 3");
                CharSequence text = "Started screen 3.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (v == radio1)
                Log.d("MyApp","radio1 clicked");
            else if (v == radio2)
                Log.d("MyApp","radio2 clicked");


        }
    }



    public void doCalculations(){
        //TextView tv = (TextView)findViewById(R.id.textView1);
        Context context = getApplicationContext();
        CharSequence text = "In doCalculations()";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //String text1="";
        EditText mEdit;

        mEdit   = (EditText)findViewById(R.id.EditText01);
        int quantity = Integer.parseInt(mEdit.getText().toString());
        mEdit   = (EditText)findViewById(R.id.EditText02);
        double price = Double.parseDouble(mEdit.getText().toString());
        double cost = price * quantity;

        mEdit = (EditText)findViewById(R.id.EditText03);
        mEdit.setText(cost + "");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
/*
Some logcat output:
02-08 23:02:44.578    1008-1008/com.example.hellofirst D/MyAppï¹• radio2 clicked
02-08 23:02:46.495    1008-1008/com.example.hellofirst D/MyAppï¹• radio1 clicked
02-08 23:02:49.826    1008-1008/com.example.hellofirst D/MyAppï¹• Check box selected
02-08 23:03:15.575    1008-1008/com.example.hellofirst D/MyAppï¹• I am here


*/
