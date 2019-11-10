package me.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    private TextView bedName, bedPrice, bedDesc;
    private TextView shelfName, shelfPrice, shelfDesc;
    private TextView tableName, tablePrice, tableDesc;
    private TextView chairName, chairPrice, chairDesc;

    private EditText hadGoodDay, editTextTotal, editTextDiscount, editTextGrandTotal;

    private Button btnCalc, btnCatalog;

    private double discount, total, saved, grandTotal;
    private String str;

    private boolean buyChair, buyTable, buyShelf, buyBed;

    private Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.total = 0.0;
        this.saved = 0.0;
        this.grandTotal = 0.0;
        initActivity();
        initFields();
    }

    private void initActivity(){
        i = new Intent(this, Catalog.class);
        editTextDiscount = findViewById(R.id.editText_discount);
        btnCatalog = findViewById(R.id.btn_catalog);
        editTextTotal = findViewById(R.id.editText_total);
        editTextGrandTotal = findViewById(R.id.editText_grandTotal);
        btnCalc = findViewById(R.id.btn_calc);
        this.discount = getIntent().getDoubleExtra("discount", 1.0);
        this.str = getIntent().getStringExtra("str");
        this.buyChair = getIntent().getBooleanExtra("chair", false);
        this.buyTable = getIntent().getBooleanExtra("table", false);
        this.buyShelf = getIntent().getBooleanExtra("shelf", false);
        this.buyBed = getIntent().getBooleanExtra("bed", false);
        String [] items = getResources().getStringArray(R.array.items_available);
        String [] itemPrices = getResources().getStringArray(R.array.items_prices);
        int [] prices = getResources().getIntArray(R.array.prices);

        String dayMsg = "the type of day you had: " + this.str + " - have a discount: " + this.discount;

        editTextDiscount.setClickable(false);
        editTextDiscount.setFocusable(false);
        editTextDiscount.setFocusableInTouchMode(false);

        editTextTotal.setClickable(false);
        editTextTotal.setFocusable(false);
        editTextTotal.setFocusableInTouchMode(false);

        editTextGrandTotal.setClickable(false);
        editTextGrandTotal.setFocusable(false);
        editTextGrandTotal.setFocusableInTouchMode(false);

        chairName = findViewById(R.id.textView_chairName);
        chairDesc = findViewById(R.id.textView_chairDesc);
        chairPrice = findViewById(R.id.textView_chairPrice);
        chairName.setText(items[0]);
        if(buyChair){
            chairDesc.setText("Added");
            total += prices[0];
        }
        else chairDesc.setText("Not Added");
        chairPrice.setText(itemPrices[0]);

        tableName = findViewById(R.id.textView_tableName);
        tableDesc = findViewById(R.id.textView_tableDesc);
        tablePrice = findViewById(R.id.textView_tablePrice);
        tableName.setText(items[1]);
        if(buyTable){
            tableDesc.setText("Added");
            total += prices[1];
        }
        else tableDesc.setText("Not Added");
        tablePrice.setText(itemPrices[1]);

        shelfName = findViewById(R.id.textView_shelfName);
        shelfDesc = findViewById(R.id.textView_shelfDesc);
        shelfPrice = findViewById(R.id.textView_shelfPrice);
        if(buyShelf){
            shelfDesc.setText("Added");
            total += prices[2];
        }
        else shelfDesc.setText("Not Added");
        shelfName.setText(items[2]);
        shelfPrice.setText(itemPrices[2]);

        bedName = findViewById(R.id.textView_bedName);
        bedDesc = findViewById(R.id.textView_bedDesc);
        bedPrice = findViewById(R.id.textView_bedPrice);
        if(buyBed){
            bedDesc.setText("Added");
            total += prices[3];
        }
        else bedDesc.setText("Not Added");
        bedName.setText(items[3]);
        bedPrice.setText(itemPrices[3]);

        hadGoodDay = findViewById(R.id.editText_hadGoodDay);
        hadGoodDay.setText(dayMsg);
        hadGoodDay.setClickable(false);
        hadGoodDay.setFocusable(false);
        hadGoodDay.setFocusableInTouchMode(false);

        BtnListner btnListner = new BtnListner();
        btnCatalog.setOnClickListener(btnListner);
        btnCalc.setOnClickListener(btnListner);


    }

    class BtnListner implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v == btnCalc){
                editTextTotal.setText("Total: " + total);

                saved = total * discount;
                editTextDiscount.setText("Discount: " + saved);

                grandTotal = total - saved;
                editTextGrandTotal.setText("Grand Total: " + grandTotal);
            }
            if(v == btnCatalog){
                i.putExtra("total", grandTotal);
                i.putExtra("discount", discount);
                startActivity(i);
            }
        }
    }

    private void initFields(){
        hadGoodDay.setText("Discount: " + discount);
    }

}
