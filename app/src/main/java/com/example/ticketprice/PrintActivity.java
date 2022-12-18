package com.example.ticketprice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ticketprice.databinding.ActivityPrintBinding;

public class PrintActivity extends AppCompatActivity
{

    ActivityPrintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityPrintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle in=getIntent().getExtras();
        String museum = in.getString("museum");
        binding.museum.setText(museum);
        //int kp=in.getInt()
        binding.knumber.setText(String.format("₹%s", in.getInt("kids")));
        binding.anumber.setText(String.format("₹%s", in.getInt("adults")));
        binding.snumber.setText(String.format("₹%s", in.getInt("seniors")));
        binding.kprice.setText(String.format("₹%s", in.getInt("kp")));
        binding.aprice.setText(String.format("₹%s", in.getInt("ap")));
        binding.sprice.setText(String.format("₹%s", in.getInt("sp")));
//        binding.kcost.setText(String.format("₹%s", in.getInt("kc")));
//        binding.acost.setText(String.format("₹%s", in.getInt("ac")));
//        binding.scost.setText(String.format("₹%s", in.getInt("sc")));


        int k=in.getInt("kids")*in.getInt("kp");
        int ad=in.getInt("adults")*in.getInt("ap");
        int se=in.getInt("seniors")*in.getInt("sp");
        binding.kcost.setText(String.format("₹%s", k));
        binding.acost.setText(String.format("₹%s", ad));
        binding.scost.setText(String.format("₹%s", se));


        binding.justprice.setText(String.format("₹%s", in.getDouble("ticket")));
        binding.tax.setText(String.format("₹%s", in.getDouble("tax")));
        binding.total.setText(String.format("₹%s", in.getDouble("total")));



    }
}