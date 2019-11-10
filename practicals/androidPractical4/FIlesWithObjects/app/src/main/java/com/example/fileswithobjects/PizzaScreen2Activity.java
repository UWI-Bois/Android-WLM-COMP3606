package com.example.fileswithobjects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PizzaScreen2Activity extends Activity {
    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_screen2);
        myButton = (Button) findViewById(R.id.button3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_pizza_screen2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void launchScreen3 (View view) {
         //Do an Intent to activate Screen 3
            myButton.setText("Going to Screen3...!");
            Context context = getApplicationContext();
            Intent intent = new Intent (context, PizzaScreen3Activity.class);
            startActivity(intent);
            Log.d("MyApp", "I am trying to go to Screen 3");
    }

}
