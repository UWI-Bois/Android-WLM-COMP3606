package com.example.fileswithobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListActivity extends Activity {

    private ArrayList<Person> persons;
    public int SIZE;
    public String FILENAME;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        lv = findViewById(R.id.list);
        SIZE = 4;
        persons = new ArrayList<>();
        FILENAME = "customer_file2";
        openFile();
        if(persons.size() > 0) initList();
    }
    private void initList() {
        SIZE = persons.size();
        int i = 0;
        String[] s = new String[SIZE];
        for (Person p :
                persons) {
            if(p != null){
                s[i] = p.toString();
                i++;
            }

        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openFile () {
        FileInputStream inputStream;
        try{
            inputStream = openFileInput(FILENAME);
            Log.d("MyApp", "getting persons from file for list!!");
            ObjectInputStream ois = new ObjectInputStream(inputStream); // for reading objects from the file
            Person temp; // cast binary obj from file to Person
            temp = (Person)ois.readObject();
            while(temp != null){
                persons.add(temp);
                temp = (Person)ois.readObject();
            }
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // return peopleList;
        Log.d("MyApp" ,"got persons[]");
        for(Person p: persons){
            if(p != null)
                Log.d("MyApp", p.toString());
        }
    }
}
