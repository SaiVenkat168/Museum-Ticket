package com.example.ticketprice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        getSupportActionBar().hide();


        TextView print=findViewById(R.id.print);
        print.setVisibility(View.GONE);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(DisplayActivity.this, MainActivity.class));
            }
        });

        int toDisplay = R.drawable.ic_launcher_background;
        String text= 0+"";
        double kPrice=0.0, aPrice=0.0, sPrice=0.0;

        ImageView museumImg= findViewById(R.id.museumImage);
        TextView museumTitle= findViewById(R.id.museumTitle);

        EditText kidsPrice= (EditText) findViewById(R.id.kids);
        EditText adultsPrice= (EditText) findViewById(R.id.adults);
        EditText seniorPrice= (EditText) findViewById(R.id.senior);

        Bundle extras= getIntent().getExtras();
        String museum_name= extras.getString("MUSEUM");

        if(museum_name.compareTo("SJM") == 0)
        {
            toDisplay=R.drawable.salar;
            text= "Salar Jung Museum";
            kPrice=20;
            aPrice=75;
            sPrice=50;
        }
        else if(museum_name.compareTo("NM") == 0)
        {
            toDisplay=R.drawable.nizam;
            text= "The Nizam's Museum";
            kPrice=30;
            aPrice=70;
            sPrice=45;
        }
        else if(museum_name.compareTo("BSM") == 0)
        {
            toDisplay=R.drawable.birla;
            text= "B.M. Birla Science Museum";
            kPrice=150;
            aPrice=250;
            sPrice=200;
        }
        else if(museum_name.compareTo("NTM") == 0)
        {
            toDisplay = R.drawable.nehru;
            text= "Nehru Centenary Tribal Museum";
            kPrice=15;
            aPrice=50;
            sPrice=35;
        }
        else if(museum_name.compareTo("VMS") == 0)
        {
            toDisplay = R.drawable.img;
            text= "Village Museum, Shilparamam";
            kPrice=30;
            aPrice=50;
            sPrice=40;
        }
        museumImg.setImageResource(toDisplay);
        museumTitle.setText(text);
        kidsPrice.setText("₹ "+Double.toString(kPrice));
        adultsPrice.setText("₹ "+Double.toString(aPrice));
        seniorPrice.setText("₹ "+Double.toString(sPrice));



        museumImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle extras= getIntent().getExtras();
                String museum_name= extras.getString("MUSEUM");
                String url = null;

                if(museum_name.compareTo("SJM") == 0)
                    url="https://www.salarjungmuseum.in/";
                else if(museum_name.compareTo("NM") == 0)
                    url="http://www.thenizamsmuseum.com/";
                else if(museum_name.compareTo("BSM") == 0)
                    url="https://www.bestbus.in/tourist-attractions/details/hyderabad/birla-science-museum";
                else if(museum_name.compareTo("NTM") == 0)
                    url="https://map.sahapedia.org/museum/Nehru-Cenetenary%20Tribal%20Museum%20/9196";
                else if(museum_name.compareTo("VMS")==0)
                    url="https://www.shilparamam.in/home/";

                Intent in= new Intent();
                in.setAction(Intent.ACTION_VIEW);
                in.addCategory(Intent.CATEGORY_BROWSABLE);
                in.setData(Uri.parse(url));
                startActivity(in);
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        TextView calc = findViewById(R.id.calculate);

        EditText ticketPrice= (EditText) findViewById(R.id.ticketPrice);
        EditText salesTax= (EditText) findViewById(R.id.salesTax);
        EditText totalPrice= (EditText) findViewById(R.id.ticketTotal);

        final int[] k = {0,0,0};
        final int[] ad = {0,0,0};
        final int[] se = {0,0,0};
        final double d[]={0.0,0.0,0.0};
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                double kid_price=Double.parseDouble((kidsPrice.getText().toString()).substring(2));
                double adult_price=Double.parseDouble((adultsPrice.getText().toString()).substring(2));
                double senior_price=Double.parseDouble((seniorPrice.getText().toString()).substring(2));

                int kids=Integer.parseInt(spinner1.getSelectedItem().toString());
                int adults=Integer.parseInt(spinner3.getSelectedItem().toString());
                int seniors=Integer.parseInt(spinner2.getSelectedItem().toString());
                k[0] =kids;
                ad[0]=adults;
                se[0]=seniors;
                k[1]= (int) kid_price;
                ad[1]= (int) adult_price;
                se[1]= (int) senior_price;


                double ticket_price=0.0, tax=0.0, total=0.0;

                ticket_price= (kids*kid_price)+(adults*adult_price)+(seniors*senior_price);
                tax=0.08875*ticket_price;
                total=ticket_price+tax;

                ticketPrice.setText("₹"+String.format("%.2f", ticket_price));
                salesTax.setText("₹"+String.format("%.2f", tax));
                totalPrice.setText("₹"+String.format("%.2f", total));
                print.setVisibility(View.VISIBLE);
                d[0]=ticket_price;
                d[1]=tax;
                d[2]=total;


            }
        });


        k[2]=k[1]*k[0];
        ad[2]=ad[1]*ad[0];
        se[2]=se[1]*se[0];

        String x = text;
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(DisplayActivity.this,PrintActivity.class);
                in.putExtra("kids",k[0]);
                in.putExtra("adults",ad[0]);
                in.putExtra("seniors",se[0]);
                in.putExtra("ticket",d[0]);
                in.putExtra("tax",d[1]);
                in.putExtra("total",d[2]);
                in.putExtra("museum", x);
                in.putExtra("kp",k[1]);
                in.putExtra("ap",ad[1]);
                in.putExtra("sp",se[1]);
                in.putExtra("kc",k[2]);
                in.putExtra("ac",ad[2]);
                in.putExtra("sc",se[2]);
                startActivity(in);

            }
        });


    }
}