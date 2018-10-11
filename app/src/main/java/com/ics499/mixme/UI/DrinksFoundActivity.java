package com.ics499.mixme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.model.Drink;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class DrinksFoundActivity extends AppCompatActivity implements LogToggle,
        View.OnClickListener, DrinkRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;

    DrinkRecyclerViewAdapter adapterOne, adapterTwo;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Debug","In DrinksFoundActivity");
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(DrinksFoundActivity.this);

        if (userName != null) {
            setContentView(R.layout.activity_drinks_found);
            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

        } else {
            setContentView(R.layout.search_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
        }

        logBtn.setOnClickListener(this);

        ArrayList<String> makables = getIntent().getStringArrayListExtra("makableNames");
        ArrayList<String> nearMakables = getIntent().getStringArrayListExtra("nearMakableNames");

        StringBuilder test = new StringBuilder();
        for (String s: makables){
            test.append(s);
            test.append("\t");
        }
        Log.d("Debug", test.toString());

        RecyclerView rv = findViewById(R.id.rvDrinks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapterOne = new DrinkRecyclerViewAdapter(this, makables);
        adapterOne.setClickListener(this);
        rv.setAdapter(adapterOne);

        RecyclerView rv2 = findViewById(R.id.rvnearDrinks);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        adapterTwo = new DrinkRecyclerViewAdapter(this, nearMakables);
        adapterTwo.setClickListener(this);
        rv2.setAdapter(adapterTwo);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void logToggle(String userName) {
        if (userName != null){
            SharedPrefsManager.setUserName(DrinksFoundActivity.this, null);
            logBtn.setText("Log In");
        } else {
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LoginActivity");
            startActivity(intent);
        }

    }
}
