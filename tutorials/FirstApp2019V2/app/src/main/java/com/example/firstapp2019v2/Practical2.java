package com.example.firstapp2019v2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Practical2 extends AppCompatActivity {

    private ImageView viewToggle;
    private ImageButton btnToggle;
    private Spinner spinnerItems;
    private EditText editTextStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical2);

        viewToggle = (ImageView) findViewById(R.id.imgViewToggle);
        btnToggle = findViewById(R.id.imgBtnToggle);
        spinnerItems = findViewById(R.id.spinnerItems);
        editTextStock = findViewById(R.id.editTextStock);

        spinnerItems.setOnItemSelectedListener(new CustomOnItemSelectedListener());


    }

    public void onClick(View view){
        System.out.print("\n\nONCLICK!\n\n");
//        Toast.makeText(this, "ON CLICK", Toast.LENGTH_SHORT).show();
        if(view == btnToggle){
            Toast.makeText(Practical2.this,
                    "image button clicked!", Toast.LENGTH_SHORT).show();
            viewToggle.setImageResource(R.drawable.dog);
            if(viewToggle.getVisibility() == View.VISIBLE){
                viewToggle.setVisibility(View.INVISIBLE);
            }
            else if(viewToggle.getVisibility() == View.INVISIBLE){
                viewToggle.setVisibility(View.VISIBLE);
            }

        }

//        else if(view == btnToggle){
//            Toast.makeText(this, "BOOP!", Toast.LENGTH_SHORT).show();
//        }

        return;
    }

    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView <?> parent, View view, int pos,long id) {
            String item = parent.getItemAtPosition(pos).toString();
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + item,
                    Toast.LENGTH_SHORT).show();

            if(item.equals("Butter")){
                editTextStock.setText("1");
            }
            else if(item.equals("Milk")){
                editTextStock.setText("2");
            }
            else if(item.equals("Cheese")){
                editTextStock.setText("5");
            }
            else if(item.equals("Lard")){
                editTextStock.setText("566");
            }
            else if(item.equals("I cant believe its not buttah")){
                editTextStock.setText("12");
            }
            else{
                editTextStock.setText("Please choose an item from the spinner!");
            }
        }
        @Override
        public void onNothingSelected(AdapterView <?> arg0) {
            editTextStock.setText("Please choose an item from the spinner!");
        }
    }
}
