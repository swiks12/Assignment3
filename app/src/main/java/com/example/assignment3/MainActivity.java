package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView;
private ArrayList<String> arrayList;
private ArrayAdapter adapter;
private Button addItem;
private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        addItem = (Button) findViewById(R.id.add_item);
        arrayList = new ArrayList<String>();
        arrayList.add("item 0");
        arrayList.add("item 1");
        arrayList.add("item 2");
        arrayList.add("item 3");
        arrayList.add("item 4");
        arrayList.add("item 5");
        arrayList.add("item 6");
        arrayList.add("item 8");
        arrayList.add("item 9");
        arrayList.add("item 10");

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        save=findViewById(R.id.save);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = arrayList.size() + 1;
                arrayList.add("Item: " + count);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "you clicked item name:" + adapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StringBuilder stringBuilder=new StringBuilder();
        for(String s:arrayList){
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        SharedPreferences settings=getSharedPreferences("PREFS",0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("arraylist",stringBuilder.toString());
        editor.commit();


    }
});


    }}


