package com.example.asieunarineasg1;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ToyDetailActivity extends AppCompatActivity {

    private int item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle.containsKey("itemid")){
            int itemid = bundle.getInt("itemid");
            this.item = itemid;
            int defaultVal = 0; // incase we get an invalid number
            String[] itemList = getResources().getStringArray(R.array.toys_list);
            String[] itemDescriptions = getResources().getStringArray(R.array.toys_description);
            String[] itemPrices = getResources().getStringArray(R.array.string_prices);
            String itemName = itemList[itemid];
            String itemPrice = itemPrices[itemid];
            String itemDesc = itemDescriptions[itemid];
            TypedArray itemImages = getResources().obtainTypedArray(R.array.toys_images);
            //set the textView with the name of the item
            TextView txtName = (TextView)findViewById(R.id.txtViewToyName);
            txtName.setText(itemName);
            //set the text view with the desc
            TextView txtDesc = (TextView)findViewById(R.id.txtViewToyDesc);
            txtDesc.setText(itemDesc);
            //set the text view with the price
            TextView txtPrice = (TextView)findViewById(R.id.txtViewPrice);
            txtPrice.setText(itemPrice);
            //set the img view with the desc
            ImageView imgView = (ImageView) findViewById(R.id.imgViewToyDetail);
            imgView.setImageResource(itemImages.getResourceId(itemid, defaultVal));
        }
    }



}
