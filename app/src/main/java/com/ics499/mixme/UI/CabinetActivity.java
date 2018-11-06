package com.ics499.mixme.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class CabinetActivity extends AppCompatActivity implements LogToggle, View.OnClickListener,
        CabinetRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;

    TextView ingredsTV;
    CabinetRecyclerViewAdapter adapter;
    Button addIngredsBtn;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(CabinetActivity.this);

        if (userName == null) {

            // This should never happen...shouldn't be in Cabinet without logged in
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_cabinet);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
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

        addIngredsBtn = (Button) findViewById(R.id.addIngredientBtn);
        addIngredsBtn.setOnClickListener(this);

        ingredsTV = (TextView) findViewById(R.id.ingredientsTV);

        controller = Controller.getInstance();
        ArrayList<Integer> userIngredIDs = controller.getUserIngredientIDs();
        ArrayList<String> items = new ArrayList<>();
        items.addAll(controller.getUserIngredients());
        ArrayList<String> makableNames = new ArrayList<>();
        String text = "You can make these drinks.";
        int posOfText = items.size();
        controller.searchDrinks(userIngredIDs, makableNames);
        items.add(text);
        items.addAll(makableNames);

        // no need to use recylerView if no ingredients in cabinet
        // display a text instead.
        if (posOfText < 1){
            ingredsTV.setText("You do not have any ingredients, and therefore you also cannot make any drinks.");
        }
        else {
            RecyclerView rv = findViewById(R.id.cabinetRV);
            rv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CabinetRecyclerViewAdapter(this, items, posOfText);
            adapter.setClickListener(this);
            rv.setAdapter(adapter);
        }
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
            case R.id.addIngredientBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.AddIngredientsToCabinetActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void logToggle(String userName) {

        if (userName != null) {
            SharedPrefsManager.setUserName(CabinetActivity.this, null);
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.SearchActivity");
            startActivity(intent);
        } else {

            // This should never happen...shouldn't be in Cabinet without logged in
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LoginActivity");
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
