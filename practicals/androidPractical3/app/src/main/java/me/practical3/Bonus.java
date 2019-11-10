package me.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Bonus extends AppCompatActivity {

    private ListView lv;
    private String[] extra_items = {
            "Nails", "Screws",
            "Knobs", "Yeet"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        lv = findViewById(R.id.listView);

//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_bonus, extra_items);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, extra_items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();
                if(s.contains("Nails")) s+= " : 3";
                if(s.contains("Knobs")) s+= " : 5";
                if(s.contains("Screws")) s+= " : 8";
                if(s.contains("Tape")) s+= " : 1";
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class lvOnClick implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
