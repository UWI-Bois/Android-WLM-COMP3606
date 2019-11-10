package me.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Catalog extends AppCompatActivity {

    private TextView bedName, bedPrice, bedDesc;
    private TextView shelfName, shelfPrice, shelfDesc;
    private TextView tableName, tablePrice, tableDesc;
    private TextView chairName, chairPrice, chairDesc;
    private TextView txtVTotal;

    private CheckBox boxChair, boxTable, boxShelf, boxBed;

    private EditText hadGoodDay;

    private Button btnCart, btnTotal, btnBonus;

    private double discount;
    private double total;
    private String str;

    private boolean buyChair, buyTable, buyShelf, buyBed;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        this.discount = getIntent().getDoubleExtra("discount", 0.5);
        this.str = getIntent().getStringExtra("str");
        this.buyShelf = false;
        this.buyBed = false;
        this.buyChair = false;
        this.buyTable = false;
        this.i = new Intent(getApplicationContext(), Cart.class);
        i.putExtra("chair", buyChair);
        i.putExtra("table", buyTable);
        i.putExtra("shelf", buyShelf);
        i.putExtra("bed", buyBed);
        i.putExtra("discount", this.discount);
        i.putExtra("str", this.str);
        initActivity();
    }

    private void initActivity(){
        String [] items = getResources().getStringArray(R.array.items_available);
        String [] itemDescs = getResources().getStringArray(R.array.items_description);
        String [] itemPrices = getResources().getStringArray(R.array.items_prices);

        String dayMsg = "the type of day you had: " + this.str + " - have a discount: " + this.discount;
        txtVTotal = findViewById(R.id.txtV_total);
        btnTotal = findViewById(R.id.btn_total);
        btnBonus = findViewById(R.id.btn_bonus);

        chairName = findViewById(R.id.textView_chairName);
        chairDesc = findViewById(R.id.textView_chairDesc);
        chairPrice = findViewById(R.id.textView_chairPrice);
        chairName.setText(items[0]);
        chairDesc.setText(itemDescs[0]);
        chairPrice.setText(itemPrices[0]);
        boxChair = findViewById(R.id.checkBox_chair);

        tableName = findViewById(R.id.textView_tableName);
        tableDesc = findViewById(R.id.textView_tableDesc);
        tablePrice = findViewById(R.id.textView_tablePrice);
        tableName.setText(items[1]);
        tableDesc.setText(itemDescs[1]);
        tablePrice.setText(itemPrices[1]);

        boxTable = findViewById(R.id.checkBox_table);

        shelfName = findViewById(R.id.textView_shelfName);
        shelfDesc = findViewById(R.id.textView_shelfDesc);
        shelfPrice = findViewById(R.id.textView_shelfPrice);
        shelfDesc.setText(itemDescs[2]);
        shelfName.setText(items[2]);
        shelfPrice.setText(itemPrices[2]);
        boxShelf = findViewById(R.id.checkBox_shelf);

        bedName = findViewById(R.id.textView_bedName);
        bedDesc = findViewById(R.id.textView_bedDesc);
        bedPrice = findViewById(R.id.textView_bedPrice);
        bedDesc.setText(itemDescs[3]);
        bedName.setText(items[3]);
        bedPrice.setText(itemPrices[3]);
        boxBed = findViewById(R.id.checkBox_bed);

        hadGoodDay = findViewById(R.id.editText_hadGoodDay);
        hadGoodDay.setText(dayMsg);
        hadGoodDay.setClickable(false);
        hadGoodDay.setFocusable(false);
        hadGoodDay.setFocusableInTouchMode(false);

        btnCart = findViewById(R.id.btn_cart);

    }

    public void viewCart(View v){
        if(v == btnCart){
            startActivity(i);
        }
    }

    public void viewBonus(View v){
        if(v == btnBonus){
            Intent intent = new Intent(this, Bonus.class);
            startActivity(intent);
        }
    }

    public void getTotal(View v){
        if(v == btnTotal){
            Toast.makeText(this,"in get total", Toast.LENGTH_SHORT);
            total = getIntent().getDoubleExtra("total", 0.0);
            discount = getIntent().getDoubleExtra("discount", 0.5);
            txtVTotal.setText("" + total);
        }
    }

    public void checkBox(View v){

        if(v == boxShelf){
            if(boxShelf.isChecked()) {
                Toast.makeText(this, "thanks for buying the shelf", Toast.LENGTH_SHORT).show();
                buyShelf = true;
            }
            else buyShelf = false;
            i.putExtra("shelf", buyShelf);
        }


        if(v == boxTable){
            if(boxTable.isChecked())
                buyTable = true;
            else buyTable = false;
            i.putExtra("table", buyTable);
        }


        if(v == boxChair){
            if(boxChair.isChecked()) buyChair = true;
            else buyChair = false;
            i.putExtra("chair", buyChair);
        }

        if(v == boxBed){
            if(boxBed.isChecked()){
                buyBed = true;
            }
            else buyBed = false;
            i.putExtra("bed", buyBed);
        }
    }

}
