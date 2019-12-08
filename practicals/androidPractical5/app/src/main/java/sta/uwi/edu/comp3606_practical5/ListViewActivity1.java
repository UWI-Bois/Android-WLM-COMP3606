package sta.uwi.edu.comp3606_practical5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import static sta.uwi.edu.comp3606_practical5.SmsReceiver.responses;

public class ListViewActivity1 extends AppCompatActivity implements Serializable {

    Serializable smsr;
    private ListView lv;
    private int SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        SIZE = 0;

        smsr = getIntent().getSerializableExtra("smsr");
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        initList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void initList() {
        // update the list data from personsData
        System.out.println("initing List");
        determineSIZE(); // account for the end of list
        if(SIZE <= 0){
            Toast.makeText(this, "Array Empty! try opening file!", Toast.LENGTH_SHORT).show();
            return;
        }
        int i = 0;
        String[] s = new String[SIZE];
        while(i < SIZE){
            s[i] = responses.get(i).toString();
            i++;
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        lv.setAdapter(adapter);
    }
    public void determineSIZE(){
        this.SIZE = responses.size();
        System.out.println("SIZE: " + SIZE);
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
