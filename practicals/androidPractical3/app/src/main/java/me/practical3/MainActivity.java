package me.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.RadialGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioGood, radioBad;
    private TextView txtDay;
    private double good, bad;
    public boolean goodMsg, badMsg;
    public String goodStr, badStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.good = 0.05;
        this. bad = 0.1;
        this.goodMsg = false;
        this.badMsg = false;
        this.goodStr = "good";
        this.badStr = "bad";
    }

    public void goodDay(View v) {
        Intent intent = new Intent(getApplicationContext(), Catalog.class);
        radioGood = findViewById(R.id.radioBtn_goodDay);
        radioBad = findViewById(R.id.radioBtn_badDay);
        txtDay = findViewById(R.id.txtView_day);
        if(v == radioGood)
        {
            goodMsg = true;
            badMsg = false;
            Toast.makeText(MainActivity.this,
                    "Very good! :)", Toast.LENGTH_SHORT).show();
            Log.d("MyApp","I am here in good day button");
            System.out.println("good day clicked!!");
            txtDay.setText("Nice! (5% Discount)");
            intent.putExtra("discount", good );
            intent.putExtra("msg", goodMsg );
            intent.putExtra("str", goodStr );
            startActivity(intent);
        }
        else if (v == radioBad) {
            badMsg = true;
            goodMsg = false;
            Toast.makeText(MainActivity.this,
                    "Oh no :(", Toast.LENGTH_SHORT).show();
            Log.d("MyApp","I am here in bad day button");
            System.out.println("bad day clicked!!");
            txtDay.setText("Unfortunate. (10% Discount)");
            intent.putExtra("discount", bad );
            intent.putExtra("msg", badMsg );
            intent.putExtra("str", badStr );
            startActivity(intent);
        }

    }
}
