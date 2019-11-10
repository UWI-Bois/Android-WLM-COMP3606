package com.example.tut;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tut.models.DBHelper;
import com.example.tut.models.ItemContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SQLiteDatabase db = (new DBHelper(this).getReadableDatabase());
        ArrayList<Map> items = new ArrayList<>();
        ListView lv = (ListView)findViewById(R.id.items_list);

        Cursor i = db.query(
                ItemContract.ItemEntry.TABLE_NAME,
                new String[] {
                        ItemContract.ItemEntry._ID,
                        ItemContract.ItemEntry.NAME,
                        ItemContract.ItemEntry.PRICE,
                        ItemContract.ItemEntry.IMAGE,
                },
                null, null, null, null, null
        );

        while(i.moveToNext()){
            int id = i.getInt(i.getColumnIndex(ItemContract.ItemEntry._ID));
            double price = i.getInt(i.getColumnIndex(ItemContract.ItemEntry.PRICE));
            String name = i.getString(i.getColumnIndex(ItemContract.ItemEntry.NAME));
            int imageId = i.getInt(i.getColumnIndex(ItemContract.ItemEntry.IMAGE));
            // store the data in a single map, so each map has data about one item
            Map map = new HashMap();
            map.put("name", name);
            map.put("price", price);
            map.put("image", imageId);
            map.put("itemid", id);
            items.add(map);
        }
        ArrayAdapter<Map> adapter = new ItemAdapter(this, items);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent i = new Intent(ItemsActivity.this, ItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("itemid", position); // place the position of the selected item
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public class ItemAdapter extends  ArrayAdapter<Map>{
        public ItemAdapter(Context context, ArrayList<Map> items){
            super(context, 0, items);
        }

        public View getView(int position, View v, ViewGroup p){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            Map iMap = getItem(position);
            int imageId = (Integer) iMap.get("imageId");
            String name = (String) iMap.get("name");
            double price = (double) iMap.get("price");
            ((ImageView)v.findViewById(R.id.imgIcon)).setImageResource(imageId);
            ((TextView)v.findViewById(R.id.txtName)).setText(name);
            ((TextView)v.findViewById(R.id.txtPrice)).setText((new Double(price).toString()));
            return v;
        }
    }


}
