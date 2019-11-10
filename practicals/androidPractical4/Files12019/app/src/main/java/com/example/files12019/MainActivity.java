//package files12019.example.com;
package com.example.files12019;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.files12019.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MainActivity extends Activity {
    private Button myButton, myButton2, buttonSave, buttonOpen;
    private RadioButton radio1, radio2;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpen = (Button) findViewById(R.id.buttonOpen);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        buttonOpen.setOnClickListener(new Button_Clicker());
        buttonSave.setOnClickListener(new Button_Clicker());
//**
    }

    public void doCalculations() {
        //TextView tv = (TextView)findViewById(R.id.textView1);
        Context context = getApplicationContext();
        CharSequence text = "In doCalculations()";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //String text1="";
        EditText mEdit;

        mEdit = (EditText) findViewById(R.id.EditText01);
        int quantity = Integer.parseInt(mEdit.getText().toString());
        mEdit = (EditText) findViewById(R.id.EditText02);
        double price = Double.parseDouble(mEdit.getText().toString());
        double cost = price * quantity;

        mEdit = (EditText) findViewById(R.id.EditText03);
        mEdit.setText(cost + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    class Button_Clicker implements Button.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v == myButton) {
                myButton.setText("Clicked!");
                doCalculations();
                Log.d("MyApp", "I am here");
            } else if (v == myButton2) {   //Do an Intent to activate Screen 3
                myButton2.setText("Clicked. Going to new Screen ...3!");
                Context context = getApplicationContext();
                /*Intent intent = new Intent(context, PizzaScreen3Activity.class);
                startActivity(intent); */
                Log.d("MyApp", "I am trying to go to Screen 3");
                CharSequence text = "Started screen 3.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if (v == radio1)
                Log.d("MyApp", "radio1 clicked");
            else if (v == radio2)
                Log.d("MyApp", "radio2 clicked");
            else if (v==buttonOpen)
                     openFile(v);
            else if (v==buttonSave)
                saveFile(v);



        }
    }


    public void saveFile (View view) {
        //Do an Intent to activate Screen 3
        buttonSave.setText("Saving File...");
        String filename = "pizza_file";
        String string = "100 pizzas sold!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            string = "110 pizzas sold!";
            outputStream.write(string.getBytes());
            string = "120 pizzas sold!";
            outputStream.write(string.getBytes());
            string = "140 pizzas sold!";
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Context context = getApplicationContext();
        Intent intent = new Intent (context, PizzaScreen3Activity.class);
        startActivity(intent);
        */
        Log.d("MyApp", "Trying to save to File.......");
    }

    public void openFile (View view) {
        //Do an Intent to activate Screen 3
        buttonOpen.setText("Opening File...");
        String filename = "pizza_file";
        String string;
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            Scanner in = new Scanner(inputStream);
            String input;
            input = in.nextLine();
            int count = 0;
            while (!input.equals("")) {
                count++;
                Context context = getApplicationContext();
                CharSequence text = "\n" + input + " was read from the file " + filename + " counter = " + count;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                input = in.nextLine();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("MyApp", "Trying to open and read File.......");
    }

}
/*
Some logcat output:
02-08 23:02:44.578    1008-1008/com.example.hellofirst D/MyAppï¹• radio2 clicked
02-08 23:02:46.495    1008-1008/com.example.hellofirst D/MyAppï¹• radio1 clicked
02-08 23:02:49.826    1008-1008/com.example.hellofirst D/MyAppï¹• Check box selected
02-08 23:03:15.575    1008-1008/com.example.hellofirst D/MyAppï¹• I am here


*/
