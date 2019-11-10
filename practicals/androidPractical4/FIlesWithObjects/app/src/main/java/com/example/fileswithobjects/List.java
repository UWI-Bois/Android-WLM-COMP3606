package com.example.fileswithobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import com.example.fileswithobjects.Person;

public class List extends AppCompatActivity {

    private ArrayList<Person> persons;
    public int SIZE;
    public String FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        SIZE = 4;
        persons = new ArrayList<>();
        FILENAME = "customer_file2";
        openFile();
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
            Log.d("MyApp", p.toString());
        }
    }
}
