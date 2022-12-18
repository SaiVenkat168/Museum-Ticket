package com.example.ticketprice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    String museum;
    String[] listItem;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        list = findViewById(R.id.museums);
        ArrayList<String> a = new ArrayList<>();
        a.add("Salar Jung Museum");
        a.add("The Nizam's Museum");
        a.add("B.M. Birla Science Museum");
        a.add("Nehru Centenary Tribal Museum");
        a.add("Village Museum, Shilparamam");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,a);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent in=new Intent(MainActivity.this,DisplayActivity.class);
                String c=list.getItemAtPosition(i).toString();
                switch (c)
                {
                    case "Salar Jung Museum":
                        in.putExtra("MUSEUM", "SJM");
                        break;
                    case "The Nizam's Museum":
                        in.putExtra("MUSEUM", "NM");
                        break;
                    case "B.M. Birla Science Museum":
                        in.putExtra("MUSEUM", "BSM");
                        break;
                    case "Nehru Centenary Tribal Museum":
                        in.putExtra("MUSEUM", "NTM");
                        break;
                    case "Village Museum, Shilparamam":
                        in.putExtra("MUSEUM", "VMS");
                        break;
                }
                startActivity(in);
            }
        });
    }
}