package com.example.tut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private int item;
    private ListView listActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listActivities = findViewById(R.id.listActivities);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ListView lv = listActivities;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                Intent i = new Intent(MainActivity.this, ItemDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("itemid", position); // place the position of the selected item
//                i.putExtras(bundle);
//                startActivity(i);
//                View v = parent.getSelectedView();
//                int vId = v.getId();
                String text = parent.getItemAtPosition(position).toString();

//                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

                if(text.equals("View Items")){
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    viewList(view);
                }
                if(text.equals("View Cart")){
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    viewCart(view);
                }
                if(text.equals("Practical 2")){
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    viewPractical2(view);
                }
                if(text.equals("Practical 1")){
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    viewPractical1(view);
                }
                if(text.equals("Radio Buttons")){
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    viewRadioButton(view);
                }
//                System.out.println()
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewList(View view){
        System.out.println("View List CLICKED!");
        //Intent i = new Intent(Main.this, ItemsActivity.class);
        Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
        startActivity(i);
        return;
    }
    public void viewCart(View view){
        System.out.println("View Cart CLICKED!");
        Toast.makeText(this, "CARTTT", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), CartActivity.class);
        startActivity(i);
        return;
    }

    public void viewRadioButton(View view){
        System.out.println("view radio button CLICKED!");
        Intent i = new Intent(getApplicationContext(), RadioButtons.class);
        startActivity(i);
        return;
    }

    public void viewPractical1(View view){
        System.out.println("view practical1 button CLICKED!");
        Intent i = new Intent(getApplicationContext(), Practical1.class);
        startActivity(i);
        return;
    }
    public void viewPractical2(View view){
        System.out.println("view practical1 button CLICKED!");
        Intent i = new Intent(getApplicationContext(), Practical2.class);
        startActivity(i);
        return;
    }

//    class Button_Clicker implements Button.OnClickListener
//    {
//        private Button myButton, myButton2;
//
//        @Override
//
//        public void onClick(View v) {
//
//
//
//            if(v == myButton)
//
//            {
//
//                myButton.setText("Clicked!");
//
//                doCalculations();
//                Context context = getApplicationContext();
//                CharSequence text = "Button 2 clicked!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//
//                Log.d("MyApp","Button 1 clicked");
//
//            }
//            else if (v == myButton2) {
//                Log.d("MyApp", "Button 2");
//                Context context = getApplicationContext();
//                CharSequence text = "Button 2 clicked!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//
//        }
//
//    }
//    public void onCheckboxClicked(View view) {
//        boolean checked = ((CheckBox) view).isChecked();
//        Context c = getApplicationContext();
//        if (checked)
//            Toast.makeText(c,
//                    "Checked", Toast.LENGTH_LONG).show();
//        else Toast.makeText(c,"Unchecked", Toast.LENGTH_LONG).show();
//    }
//
//
//    public void doCalculations(EditText e1, EditText e2, EditText e3){
//        //TextView tv = (TextView)findViewById(R.id.textView1);
//        Context context = getApplicationContext();
//        CharSequence text = "In doCalculations()";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//
//        //String text1="";
//        EditText mEdit;
//
//        //mEdit   = (EditText)findViewById(R.id.editTextName1);
//        int quantity = Integer.parseInt(e1.getText().toString());
//        //mEdit   = (EditText)findViewById(R.id.EditText02);
//        double price = Double.parseDouble(e2.getText().toString());
//        double cost = price * quantity;
//
//        //mEdit = (EditText)findViewById(R.id.EditText03);
//        e3.setText(cost + "");
//    }



}
