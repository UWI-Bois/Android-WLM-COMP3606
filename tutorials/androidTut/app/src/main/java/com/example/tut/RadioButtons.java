package com.example.tut;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RadioButtons extends Activity implements AdapterView.OnItemSelectedListener {

    private RadioButton myButton, myButton2;
    private ImageButton imageButton;
    public TextView textView;
    private Spinner spinner1;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttons);
        Toolbar toolbar = findViewById(R.id.toolbar);

        myButton = (RadioButton) findViewById(R.id.radioButton);
        myButton2 = (RadioButton) findViewById(R.id.radioButton2);
        imageButton= (ImageButton) findViewById(R.id.imageButton);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        textView = (TextView) findViewById(R.id.textView1);

        String[] books = {
                "book1",
                "book2",
                "book3"
        };

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.books, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {

        if(v == myButton)
        {
            myButton.setText("Clicked!");
            textView.setText("Clicked button 1!");
            //doCalculations();
            Toast.makeText(RadioButtons.this,
                    "Button 1 clicked!", Toast.LENGTH_SHORT).show();
            Log.d("MyApp","I am here in myButton");
            System.out.println("myButton clicked!!");
        }
        else if (v == myButton2) {
            Toast.makeText(RadioButtons.this,
                    "Button 2 clicked!", Toast.LENGTH_SHORT).show();
            //image.setImageResource(R.drawable.picture1);
            Log.d("MyApp","I am here in myButton2");
            System.out.println("myButton2 clicked!!");
        }
                else if (v == imageButton) {
                    Toast.makeText(RadioButtons.this,
                            "Image button clicked!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RadioButtons.this,
                            "OnClickListener : " +
                                    "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()),
                            Toast.LENGTH_LONG).show();
                }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textView.setText(parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textView.setText("please choose a book");
    }
}
