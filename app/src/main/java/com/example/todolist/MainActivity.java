package com.example.todolist;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList; //import arraylist

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    //setup View variables in main activity

    //declaring View variables

    private EditText itemET;
    private Button btn; // //private Button btn;
    private ListView itemsList; // private ListView itemsList;


    private ArrayList<String> items; //declaring arraylist for storing items
    private ArrayAdapter<String> adapter; //declaring arrayadapter for adapting those items to the list

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate means when the activity first starts
        super.onCreate(savedInstanceState); //necessary for main activity
        setContentView(R.layout.activity_main); //activity that gets loaded


        //id variables are variables that refer to the ID
        //setup id variables

        itemET = findViewById(R.id.item_edit_text); //initialize ID variables from their corresponding IDs
        btn = findViewById(R.id.add_btn); //btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list); //itemsList = findViewbyId(R.id.items_list);

        items = FileHelper.readData(this); //initialize variable called items that will read data from filehelper class

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); //takes strings from text that users type in list

        //initialize array adapter
        //adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, items);

        itemsList.setAdapter(adapter); //set array adapter to itemslist so that whatever you put in list will be applied
        //to the arraylist


        btn.setOnClickListener(this); //set onclick listener for btn
        itemsList.setOnItemClickListener(this); //set onclick listener for itemlist


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString(); //take what item entered and put to string "itemEntered"
                adapter.add(itemEntered); //add that text to the array adapter
                itemET.setText(""); //after item is added then itemET is cleared

                FileHelper.writeData(items, this); //writes data to file

                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show(); //makes text

                //Toast.makeText(this, "item added," Toast.LENGTH_SHORT).show();

                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //position corresponds to string position in array
        items.remove(position); //item removed based on position
        adapter.notifyDataSetChanged();
        //Notifies the attached observers that the underlying data has been changed and
        // so any View reflecting the data set should refresh itself.
        FileHelper.writeData(items, this);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

    }
}

