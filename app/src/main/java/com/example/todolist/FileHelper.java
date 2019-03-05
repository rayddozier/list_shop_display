package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<String> items, Context context){ //write data to file

        //method for writing data to listinfo.dat
        //public static void writeData(ArrayList<String> items, Context context)

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            //initialize file output
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }   catch (IOException e) {
            e.printStackTrace();
        }

        //printStackTrace() prints the locations where the exception occurred in the source code,
        // thus allowing the author who wrote the program to see what went wrong.

    }

    public static ArrayList<String> readData(Context context){ //open data from file
        //parameters for readdata are the same except there are no paramaters for item
        ArrayList<String> itemsList = null; //initially the itemslist is null
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            //initialize the file input (filename)
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject();
            //read that file and assign what it reads to the arraylist
        } catch (FileNotFoundException e) {

            itemsList = new ArrayList<>();
            e.printStackTrace();


                                //It helps to trace the exception. For example you are writing some methods in your program
                                // and one of your methods causes bug. Then printstack will help you to identify which method causes the bug. Stack will help like this:
                                //First your main method will be called and inserted to stack, then the second method will be called and inserted to the stack in LIFO order
                                //and if any error occurs somewhere inside any method then this stack will help to identify that method.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemsList;
    }

}
