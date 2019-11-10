package com.example.fileswithobjects;

/**
 * Created by mhosein
 */
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;



public class Serializer {

    public static void main (String args[]) {

        Serializer serializer = new Serializer();
       // serializer.serializeAddress("wall street", "united state");
    }

    public void serializePerson (String fName, String lName, int ord){

        Person per = new Person(fName, lName, ord); //create Person object
        try{

            FileOutputStream fout = new FileOutputStream("person.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(per);
            oos.close();
            System.out.println("Finished writing person object to file 'person.ser'");

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
