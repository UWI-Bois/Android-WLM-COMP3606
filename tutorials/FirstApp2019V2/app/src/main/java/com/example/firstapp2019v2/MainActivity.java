package com.example.firstapp2019v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button myButton, myButton2;
    private ImageButton imageButton;
    private RadioButton radio1, radio2;
    private CheckBox check;
    Spinner spinner1;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new Button_Clicker());
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        myButton = (Button) findViewById(R.id.button1);
        myButton2 = (Button) findViewById(R.id.button2);
        radio1 = (RadioButton) findViewById(R.id.radioButton);
        radio2 = (RadioButton) findViewById(R.id.radioButton2);
        check = (CheckBox) findViewById(R.id.checkBox);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
    }



    class Button_Clicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v == myButton){
                myButton.setText("Clicked!");
                doCalculations();
                Log.d("MyApp","I am here");
            }
            else if (v == myButton2) {
                Toast.makeText(MainActivity.this,
                        "Button 2 clicked!", Toast.LENGTH_SHORT).show();
                image.setImageResource(R.drawable.picture1);
            }
            else if (v == imageButton) {
                Toast.makeText(MainActivity.this,
                        "Image button clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_LONG).show();
            }
            else if (v == radio1) Log.d("MyApp","radio1 clicked");
            else if (v == radio2) Log.d("MyApp","radio2 clicked");
        }
    }

    class CustomOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView <?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView <?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    public void doCalculations(){
        //TextView tv = (TextView)findViewById(R.id.textView1);
        Context context = getApplicationContext();
        CharSequence text = "In doCalculations()";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        EditText mEdit1, mEdit2, mEdit3;
        mEdit1   = (EditText)findViewById(R.id.EditText01);
        mEdit2   = (EditText)findViewById(R.id.EditText02);
        String val1 = mEdit1.getText().toString();
        String val2 = mEdit2.getText().toString();

        if(val1.equals("") || val2.equals("")){ // check this contidion, idk if null is correct, perhaps check for ""??
            Toast toast1 = Toast.makeText(context, "error in calc: field missing!", duration);
            toast1.show();
            return;
        }
        else{
            int quantity = Integer.parseInt((mEdit1.getText().toString()));
            double price = Double.parseDouble(mEdit2.getText().toString());
            double cost = price * quantity;
            mEdit3 = (EditText)findViewById(R.id.EditText03);
            mEdit3.setText(cost + "");
        }
        return;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void viewPractical2(View view){
        System.out.println("view practical1 button CLICKED!");
        Toast.makeText(this, "PRACTICAL 2", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Practical2.class);
        startActivity(i);
        return;
    }

}








