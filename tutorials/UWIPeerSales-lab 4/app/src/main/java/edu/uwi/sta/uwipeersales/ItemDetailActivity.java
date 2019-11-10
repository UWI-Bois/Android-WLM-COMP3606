package edu.uwi.sta.uwipeersales;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ItemDetailActivity extends AppCompatActivity {

	private int item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey("itemid")){
			int itemid = bundle.getInt("itemid");
			this.item = itemid;
			int defaultVal = 0; // in the unlikely event that the number is invalid we set a default
			String [] itemList = getResources().getStringArray(R.array.items_available);
			String [] itemDescriptions = getResources().getStringArray(R.array.items_description);
			String itemName = itemList[itemid];
			String itemDescription = itemDescriptions[itemid];
			TypedArray itemImages = getResources().obtainTypedArray(R.array.items_images);
			// Set the TextView with the name of the item
			TextView txtName = (TextView)findViewById(R.id.txt_name);
			txtName.setText(itemName);
			// Set the Text View with the description
			TextView txtDescription =(TextView)findViewById(R.id.txt_description);
			txtDescription.setText(itemDescription);
			// Set the image view to the image that corresponds to the selected item
			ImageView imgView = (ImageView)findViewById(R.id.img_icon);
			imgView.setImageResource(itemImages.getResourceId(itemid, defaultVal));
		}
	}

	public void addToCart(View view){
		//To keeps things simple we will store only one item

		int size;
		SharedPreferences sp = getApplicationContext().getSharedPreferences("Cart", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if(sp.contains("cartSize")){
			size=sp.getInt("cartSize",0);
		}
		else{
			size=0;
		}
		size+=1;
		editor.putInt("cartSize",size);
		editor.putInt("cartItem"+size,this.item);
		editor.apply();//Can also use editor.commit() apply does the save asynchronously in the background while commit immediately saves to storage
		Snackbar.make(view, "Item Successfully added to the Cart", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
	}



}
