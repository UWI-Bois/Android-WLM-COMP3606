package edu.uwi.sta.uwipeersales;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
	ArrayList<Map> items;
	adapter custom;
	ListView cart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		items=new ArrayList<>();
		cart=(ListView)findViewById(R.id.listView);

		SharedPreferences sp = getApplicationContext().getSharedPreferences("Cart",MODE_PRIVATE);
		int size= sp.getInt("cartSize", 0);

		int[] arr= new int[size+1];
		int i;
		if(size>0){
			for(i=1;i<=size;i++){
				arr[i]=sp.getInt("cartItem"+i,-1);
			}

			String[] itemList= getResources().getStringArray(R.array.items_available);
			String[] itemDesc= getResources().getStringArray(R.array.items_available);
			TypedArray img = getResources().obtainTypedArray(R.array.items_images);

			for(i=1;i<=size;i++){
				Map temp= new HashMap();
				temp.put("id",i);
				temp.put("name",itemList[arr[i]]);
				temp.put("desc",itemDesc[arr[i]]);
				temp.put("image",img.getResourceId(arr[i], -1));
				items.add(temp);
			}
			custom= new adapter(this,items,sp);
			cart.setAdapter(custom);
		}
	}

}
