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
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class PizzaScreen3Activity extends Activity {
    private ArrayList<Person> persons;
    private static final int SIZE = 5;
    private static final String FILENAME = "customer_file2";
    private Button buttonOpen, buttonSave, buttonUpdate, btnLV;
    private EditText fname, lname, newOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_screen3);

        persons = new ArrayList<>();

        buttonOpen = (Button) findViewById(R.id.buttonOpen);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonUpdate= (Button) findViewById(R.id.buttonUpdate);
        fname = (EditText) findViewById(R.id.editTextFname);
        lname = (EditText) findViewById(R.id.editTextLastname);
        newOrders = (EditText) findViewById(R.id.editTextOrders);

        btnLV = findViewById(R.id.btn_lv);
        btnLV.setOnClickListener(new Button_Clicker());

        // buttonExit = (Button) findViewById(R.id.buttonExit);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_pizza_screen2, menu);
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

    public void exitApp (View v) {
        finish();
    }
    public void saveFile (View view) {
        //Do an Intent to activate Screen 3
        int size = 4; //4 customers
        buttonSave.setText("Saving File...");
        String string = "test";

        persons.add(new Person("Bill", "Smith", 20) );
        persons.add(new Person("Fred", "Bloggs", 30) );
        persons.add(new Person("Joe", "Phillips", 40) );
        persons.add(new Person("Ria", "Maharaj", 50) );
        persons.add(null);

        Log.d("MyApp", "Data loaded in array. Printing from array....");
        printFromArray(persons);
        Log.d("MyApp", "Attempting to write to file....");
        //Person per = new Person(fName, lName, ord); //create Person object
        try {
            FileOutputStream fout;
            fout = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            for (Person p :
                    persons) {
                oos.writeObject(p);
            }
            oos.close();
            System.out.println("Finished writing person objects to file ' + FILENAME");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //


        Log.d("MyApp", "File write successful.");
    }

    public void printFromArray (ArrayList<Person> p) {
        for (Person i :
                p) {
            System.out.println(p.toString());
        }
    }

    public void openFile (View view) {
        //Do an Intent to activate Screen 3
        buttonOpen.setText("Opening File...");
        FileInputStream inputStream;
        ArrayList<Person> p = new ArrayList<>();
        try {
            inputStream = openFileInput(FILENAME);
             //
            Log.d("MyApp", "Opened the file.");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Person temp = (Person) ois.readObject();
            while(temp != null){
                p.add(temp);
                temp = (Person) ois.readObject();
            }
             ois.close();
             }catch(Exception ex){
                  ex.printStackTrace();
             }
        Log.d("MyApp", "Loaded the array from the file.");
        Log.d("MyApp", "Printing the array contents.");
        printFromArray (p);

       // return peopleList;
    }

    public void updateFile (View view) {
        Log.d("MyApp", "Attempting to update to file....");
        buttonOpen.setText("Opening File...");
        String string;
        FileInputStream inputStream;
        //
        Person peopleList[] = new Person [SIZE];
        try {
            inputStream = openFileInput(FILENAME);
            //
            Log.d("MyApp", "Opened the file.");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            for (int j = 0; j < SIZE; j++) {
                Person tempP = (Person) ois.readObject();
                peopleList[j] = tempP;
            }
            ois.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        Log.d("MyApp", "Loaded the array from the file.");
        Log.d("MyApp", "Printing the array contents.");
//        printFromArray (peopleList, SIZE);

        boolean updated = false;
        //Try to update record;

        for (int j = 0; j < SIZE; j++) {
            if (peopleList[j].getFirstName().equals(fname.getText().toString()) && peopleList[j].getLastName().equals(lname.getText().toString())) {
                peopleList[j].updateOrders(Integer.parseInt(newOrders.getText().toString()));
                Log.d("MyApp", "Record was updated in the array " + peopleList[j].getLastName());
                updated = true;
            }

        }
        //Only if a record was updated, should the file be rewritten
        if (updated) {
            Log.d("MyApp", "Printing the UPDATED array contents.");
//            printFromArray (peopleList, SIZE);
            Log.d("MyApp", "Attempting to update file with changes....");
            try {
                FileOutputStream fout;
                fout = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                for (int j = 0; j < SIZE; j++)
                    oos.writeObject(peopleList[j]);
                oos.close();
                System.out.println("Finished writing person objects to file " + FILENAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("MyApp", "Finished attempting write changes to file...");
        }
    }
    class Button_Clicker implements Button.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if (v == btnLV){
                Intent i = new Intent(getApplicationContext(), List.class);
                startActivity(i);
            }
        }
    }
}
/*
03-15 12:04:15.196    1571-1583/example.com.userinterface11 I/art﹕ Background sticky concurrent mark sweep GC freed 6494(290KB) AllocSpace objects, 0(0B) LOS objects, 16% free, 2MB/2MB, paused 1.130ms total 201.273ms
03-15 12:04:36.414    1571-1571/example.com.userinterface11 D/MyApp﹕ Data loaded in array. Printing from array....
03-15 12:04:36.415    1571-1571/example.com.userinterface11 I/System.out﹕ Bill Smith 20
03-15 12:04:36.415    1571-1571/example.com.userinterface11 I/System.out﹕ Fred Bloggs 30
03-15 12:04:36.415    1571-1571/example.com.userinterface11 I/System.out﹕ Joe Phillips 40
03-15 12:04:36.415    1571-1571/example.com.userinterface11 I/System.out﹕ Ria Maharaj 50
03-15 12:04:36.415    1571-1571/example.com.userinterface11 D/MyApp﹕ Attempting to write to file....
03-15 12:04:36.587    1571-1571/example.com.userinterface11 I/System.out﹕ Finished writing person objects to file ' + FILENAME
03-15 12:04:36.587    1571-1571/example.com.userinterface11 D/MyApp﹕ File write successful.
03-15 12:04:56.017    1571-1571/example.com.userinterface11 D/MyApp﹕ Opened the file.
03-15 12:04:56.068    1571-1571/example.com.userinterface11 D/MyApp﹕ Loaded the array from the file.
03-15 12:04:56.068    1571-1571/example.com.userinterface11 D/MyApp﹕ Printing the array contents.
03-15 12:04:56.069    1571-1571/example.com.userinterface11 I/System.out﹕ Bill Smith 20
03-15 12:04:56.069    1571-1571/example.com.userinterface11 I/System.out﹕ Fred Bloggs 30
03-15 12:04:56.069    1571-1571/example.com.userinterface11 I/System.out﹕ Joe Phillips 40
03-15 12:04:56.077    1571-1571/example.com.userinterface11 I/System.out﹕ Ria Maharaj 50
03-15 12:05:04.951    1571-1571/example.com.userinterface11 D/MyApp﹕ Attempting to update to file....
03-15 12:05:04.953    1571-1571/example.com.userinterface11 D/MyApp﹕ Opened the file.
03-15 12:05:05.084    1571-1571/example.com.userinterface11 D/MyApp﹕ Loaded the array from the file.
03-15 12:05:05.085    1571-1571/example.com.userinterface11 D/MyApp﹕ Printing the array contents.
03-15 12:05:05.085    1571-1571/example.com.userinterface11 I/System.out﹕ Bill Smith 20
03-15 12:05:05.085    1571-1571/example.com.userinterface11 I/System.out﹕ Fred Bloggs 30
03-15 12:05:05.085    1571-1571/example.com.userinterface11 I/System.out﹕ Joe Phillips 40
03-15 12:05:05.085    1571-1571/example.com.userinterface11 I/System.out﹕ Ria Maharaj 50
03-15 12:05:19.903    1571-1571/example.com.userinterface11 D/InputEventConsistencyVerifier﹕ KeyEvent: ACTION_UP but key was not down.
    in android.widget.EditText{155430da VFED..CL .F....ID 32,597-32,680 #7f080014 app:id/custOrders}
    0: sent at 6947298000000, KeyEvent { action=ACTION_UP, keyCode=KEYCODE_TAB, scanCode=15, metaState=0, flags=0x8, repeatCount=0, eventTime=6947298, downTime=6947152, deviceId=0, source=0x101 }
03-15 12:05:30.756    1571-1571/example.com.userinterface11 D/MyApp﹕ Attempting to update to file....
03-15 12:05:30.758    1571-1571/example.com.userinterface11 D/MyApp﹕ Opened the file.
03-15 12:05:30.903    1571-1571/example.com.userinterface11 D/MyApp﹕ Loaded the array from the file.
03-15 12:05:30.903    1571-1571/example.com.userinterface11 D/MyApp﹕ Printing the array contents.
03-15 12:05:30.903    1571-1571/example.com.userinterface11 I/System.out﹕ Bill Smith 20
03-15 12:05:30.903    1571-1571/example.com.userinterface11 I/System.out﹕ Fred Bloggs 30
03-15 12:05:30.903    1571-1571/example.com.userinterface11 I/System.out﹕ Joe Phillips 40
03-15 12:05:30.903    1571-1571/example.com.userinterface11 I/System.out﹕ Ria Maharaj 50
03-15 12:05:30.904    1571-1571/example.com.userinterface11 D/MyApp﹕ Record was updated in the array Bloggs
03-15 12:05:30.904    1571-1571/example.com.userinterface11 D/MyApp﹕ Printing the UPDATED array contents.
03-15 12:05:30.990    1571-1571/example.com.userinterface11 I/System.out﹕ Bill Smith 20
03-15 12:05:30.991    1571-1571/example.com.userinterface11 I/System.out﹕ Fred Bloggs 130
03-15 12:05:31.013    1571-1571/example.com.userinterface11 I/System.out﹕ Joe Phillips 40
03-15 12:05:31.013    1571-1571/example.com.userinterface11 I/System.out﹕ Ria Maharaj 50
03-15 12:05:31.014    1571-1571/example.com.userinterface11 D/MyApp﹕ Attempting to update file with changes....
03-15 12:05:31.064    1571-1571/example.com.userinterface11 I/System.out﹕ Finished writing person objects to file customer_file2
03-15 12:05:31.064    1571-1571/example.com.userinterface11 D/MyApp﹕ Finished attempting write changes to file...
03-15 12:05:31.298    1571-1583/example.com.userinterface11 I/art﹕ Background partial concurrent mark sweep GC freed 5557(247KB) AllocSpace


NOTE: Exercise caution if running this app on your actual phone.

EXERCISES
1. Try to figure out where the data file is located.
2. Add and ID field for each customer (unique integer)
3. Display all the data written to the file and all the data retrieved from the file
4. Accept an ID an integer "update" and locate the customer in the file. ADd "update"
   to his orders. If not found, print a message.


 */