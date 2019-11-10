package com.example.tut;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tut.models.CartContract;
import com.example.tut.models.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    ArrayList<Map> items;
    adapter custom;
    ListView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        items = new ArrayList<>();
//        cart = findViewById(R.id.cart_list);

        // feilds to specify what columns will be displayed (restriction)
        String[] fields = {CartContract.CartEntry.ITEM, CartContract.CartEntry.TIME};
        // specify that the records will be ordered by the time created
        String sortedOrder = CartContract.CartEntry.TIME + " DESC";
        // retrueve the database
        SQLiteOpenHelper helper = new DBHelper(this); // this is the activity
        final SQLiteDatabase db = helper.getReadableDatabase(); // use readable to prevent unecessary locks
        //the database will execute the query based on options we speicfy and store in the curosr
        Cursor res = db.query(CartContract.CartEntry.TABLE_NAME, fields, null, null, null, null, sortedOrder);
        ArrayList<String> itemList = new ArrayList();
        String[] items = getResources().getStringArray(R.array.items_available);
        // for each of the items retuned from the database query
        while(res.moveToNext()){
            //retrueive the index of the itme that is stored and find string for that itme
            int itemId = res.getInt(res.getColumnIndex(CartContract.CartEntry.ITEM));
            // add the string for the item in an arraylist
            itemList.add(items[itemId]);
        }
        //populate the istview created for the cart using an adapter
        ListView lv = (ListView) findViewById(R.id.cart_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        lv.setAdapter(adapter);
    }

}
