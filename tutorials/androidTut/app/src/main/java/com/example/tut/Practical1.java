package com.example.tut;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Practical1 extends AppCompatActivity {

    private RadioButton red, green;
    private CheckBox vat;
    private EditText costArea;
    private Button calc;

    public int redVal = 5;
    public int greenVal = 10;
    private double cost = 0.00;
    public double vatVal = 1.0 + 0.15; // value = 15%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        red = findViewById(R.id.radBtnRed);
        green = findViewById(R.id.radBtnGreen);
        vat = findViewById(R.id.chkBoxVAT);
        costArea = findViewById(R.id.editTxtCost);
        calc = findViewById(R.id.btnCalculate);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void onClick(View view) {

        if(view == red){
            red.setText("RED!");
            cost = redVal;
//                    costArea.setText("$" + cost);
            Toast.makeText(Practical1.this,
                    "red 1 clicked!", Toast.LENGTH_SHORT).show();
        }
        else if(view == green){
            green.setText("GREEN!");
            cost = greenVal;
//                    costArea.setText("$" + greenVal);
            Toast.makeText(Practical1.this,
                    "green 1 clicked!", Toast.LENGTH_SHORT).show();
        }
        if(vat.isChecked()){
            //cost *= vatVal;
            Toast.makeText(Practical1.this,
                    "var 1 clicked!", Toast.LENGTH_SHORT).show();
        }
        if(calc.isPressed()){
            cost = doCalc();
            costArea.setText("$" + cost);
//            Toast.makeText(Practical1.this,
//                    "calc 1 clicked!", Toast.LENGTH_SHORT).show();
        }

//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }

    private  double  doCalc(){
        double val = 0.00;
        if(!red.isChecked() && !green.isChecked()){
            Toast.makeText(Practical1.this,
                    "Please select a color!", Toast.LENGTH_SHORT).show();
            return val;
        }
        else if(red.isChecked()){
            val = redVal;
        }
        else if(green.isChecked()){
            val = greenVal;
        }
        if(vat.isChecked()){
            val *= vatVal;
        }
//        val = cost;
        return val;
    }

}
