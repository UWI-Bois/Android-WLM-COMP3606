package com.example.tut;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tut.models.CartContract;
import com.example.tut.models.DBHelper;

public class ItemDetailActivity extends AppCompatActivity {

    private int item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if(bundle.containsKey("itemid")){
            int itemid = bundle.getInt("itemid");
            this.item = itemid;
            int defaultVal = 0; // incase we get an invalid number
            String[] itemList = getResources().getStringArray(R.array.items_available);
            String[] itemDescriptions = getResources().getStringArray(R.array.items_description);
            String[] itemPrices = getResources().getStringArray(R.array.items_prices);
            String itemName = itemList[itemid];
            String itemPrice = itemPrices[itemid];
            String itemDesc = itemDescriptions[itemid];
            TypedArray itemImages = getResources().obtainTypedArray(R.array.items_images);
            //set the textView with the name of the item
            TextView txtName = (TextView)findViewById(R.id.txtName);
            txtName.setText(itemName);
            //set the text view with the desc
            TextView txtDesc = (TextView)findViewById(R.id.txtDesc);
            txtDesc.setText(itemDesc);
            //set the text view with the price
            TextView txtPrice = (TextView)findViewById(R.id.txtPrice);
            txtPrice.setText(itemPrice);
            //set the img view with the desc
            ImageView imgView = (ImageView) findViewById(R.id.imgIcon);
            imgView.setImageResource(itemImages.getResourceId(itemid, defaultVal));
        }
    } //end onCreate();

//    public void addToCart(View view){
//        System.out.println("addToCart()");
//        SharedPreferences sp = getApplicationContext().getSharedPreferences("uwiPrefs",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("itemCart", this.item);
//
//        if(editor.commit()) {
//            Snackbar.make(view, "Item successfully added to cart", Snackbar.LENGTH_LONG)
//                    .setAction("Undo", (v) -> {
//                        editor.remove("itemCart");
//                        if (editor.commit()) {
//                            Snackbar.make(view, "Removed Item from Cart", Snackbar.LENGTH_LONG).show();
//                        }
//                    }).show();
//        }
//    }

    public void addToCart(final View view){
        int item = this.item; // the item selected was added as a property of the class when launches
        // get the sql from helper
        SQLiteOpenHelper helper = new DBHelper(this); // this is the activity
        final SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CartContract.CartEntry.ITEM, item);
        final long cartID = db.insert(CartContract.CartEntry.TABLE_NAME, null, cv);
        if(cartID != -1){
            Snackbar.make(view, "Item Successfully added to the Cart", Snackbar.LENGTH_LONG)
//                    .setAction("Undo", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v){
//                            String sql = "DELETE FROM " +
//                                    CartContract.CartEntry.TABLE_NAME +
//                                    " WHERE " + CartContract.CartEntry._ID +
//                                    " = " + cartID + ";";
//                            db.execSQL(sql);
//                            Snackbar.make(view, "removed item from cart", Snackbar.LENGTH_LONG).show();
//                        }
//                    }).show();
                    .setAction("Undo", (v) -> {
                        try{
                            db.delete(CartContract.CartEntry.TABLE_NAME, CartContract.CartEntry.ITEM + "=" + cartID, null);
                            Snackbar.make(view, "Removed Item from Cart", Snackbar.LENGTH_LONG).show();
                        }catch(Exception e){
                            e.printStackTrace();
                            Snackbar.make(view, "unable to remove item from cart", Snackbar.LENGTH_LONG).show();
                        }
                    }).show();
        }
    }

}
