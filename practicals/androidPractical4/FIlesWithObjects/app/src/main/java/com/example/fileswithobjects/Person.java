package com.example.fileswithobjects;

/**
 * Created by mhosein 2018.
 */
import android.app.Application; //to have access to
import android.content.Context;
import android.widget.Toast;

import java.io.*;

public class Person extends Application implements Serializable {
//
    public String firstName;
    public String lastName;
    public int orders;
    private static int IDCounter = 0;
    private int id;

    public Person(String first, String last, int ord) {
        firstName = first;
        lastName = last;
        orders = ord;
        IDCounter++;
        id = IDCounter;

    }

    public String getFirstName () {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public int getOrders () {
        return orders;
    }


    public void updateOrders(int change){

        orders = orders + change;
    }


    public String toString() {
        return new String(id + " " + firstName +  " " + lastName + " " +  orders);
    }

}
