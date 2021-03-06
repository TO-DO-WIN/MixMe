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

    TextView greeting, canMake;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;

    DrinkRecyclerViewAdapter adapter;
    Controller controller;
    ArrayList<Object> drinkObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(DrinksFoundActivity.this);

        if (userName != null) {
            setContentView(R.layout.activity_drinks_found);
            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

        } else {
            setContentView(R.layout.drinks_found_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
        }

        logBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
        createDrinkBtn.setOnClickListener(this);

        favesBtn = (Button) findViewById(R.id.favesNVBtn);
        favesBtn.setOnClickListener(this);

        shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
        shoppingBtn.setOnClickListener(this);

        cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
        cabinetBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        ArrayList<String> makables = getIntent().getStringArrayListExtra("makableNames");
        ArrayList<String> nearMakables = getIntent().getStringArrayListExtra("nearMakableNames");
        ArrayList<String> nearMakableMatch = getIntent().getStringArrayListExtra("nearMakableMatch");

        canMake = (TextView) findViewById(R.id.canMake);

        if(makables.size()==0){
            canMake.setText("Sorry, there are no drinks you can make with the ingredients selected");
        }


        for (String d: makables){
            drinkObjects.add(new DrinkForAdapter(d, "100"));
        }
        drinkObjects.add("You can almost make these drinks");
        for (int i = 0; i < nearMakables.size(); i++){
            drinkObjects.add(new DrinkForAdapter(nearMakables.get(i), nearMakableMatch.get(i)));
        }


        RecyclerView rv = findViewById(R.id.rvDrinks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DrinkRecyclerViewAdapter(this, drinkObjects);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.searchNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.SearchActivity");
                startActivity(intent);
                break;

            case R.id.createNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.CreateDrinkActivity");
                startActivity(intent);
                break;

            case R.id.favesNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.FavoritesActivity");
                startActivity(intent);
                break;

            case R.id.shoppingNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.ShoppingListActivity");
                startActivity(intent);
                break;

            case R.id.cabinetNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.CabinetActivity");
                startActivity(intent);
                break;

            case R.id.randomNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.RandomActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        // probably need to handle for clicking on the text item which is not a DrinkForAdapter

        DrinkForAdapter drink = (DrinkForAdapter) drinkObjects.get(position);
        String nameString = drink.getDrinkName();

        Intent intent = new Intent();
        intent.putExtra("drink", nameString);
        intent.setClassName("com.ics499.mixme",
                "com.ics499.mixme.UI.DrinkRecipeActivity");
        startActivity(intent);

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

    public class DrinkForAdapter{
        public String drinkName;
        public String drinkPercent;

        DrinkForAdapter(String drinkName, String drinkPercent){
            this.drinkName = drinkName;
            this.drinkPercent = drinkPercent;
        }

        public String getDrinkName() {
            return drinkName;
        }

        public void setDrinkName(String drinkName) {
            this.drinkName = drinkName;
        }

        public String getDrinkPercent() {
            return drinkPercent;
        }

        public void setDrinkPercent(String drinkPercent) {
            this.drinkPercent = drinkPercent;
        }
    }
}
