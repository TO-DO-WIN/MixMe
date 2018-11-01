package com.ics499.mixme.UI;

import android.content.Intent;
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

public class DrinkRecipeActivity extends AppCompatActivity implements LogToggle, View.OnClickListener,
        CreateRecyclerViewAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn, createDrinkBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;

    TextView drinkNameTV, drinkRatingTV, instructionsTV, glassTV;
    RecyclerView rv;
    Controller controller;
    CreateRecyclerViewAdapter adapter;

    Button addFavesBtn, rateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(DrinkRecipeActivity.this);

        if (userName != null) {
            setContentView(R.layout.activity_drink_recipe);
            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);
            logBtn = (Button) findViewById(R.id.logBtn);
            logBtn.setText("Log Out");

            createDrinkBtn = (Button) findViewById(R.id.createNVBtn);
            createDrinkBtn.setOnClickListener(this);

            favesBtn = (Button) findViewById(R.id.favesNVBtn);
            favesBtn.setOnClickListener(this);

            shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
            shoppingBtn.setOnClickListener(this);

            cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
            cabinetBtn.setOnClickListener(this);

            addFavesBtn = (Button) findViewById(R.id.addFavesBtn);
            addFavesBtn.setOnClickListener(this);

            rateBtn = (Button) findViewById(R.id.rateBtn);
            rateBtn.setOnClickListener(this);
        }
        else {
            setContentView(R.layout.drink_recipe_guest);
            logBtn = (Button) findViewById(R.id.logBtn);
        }

        logBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        controller = Controller.getInstance();

        Intent intent = getIntent();
        String drinkName = intent.getStringExtra("drink");
        controller.setRecipe(drinkName);

        ArrayList<String> recipeIngredients = controller.getRecipeIngredients();
        ArrayList<String> recipeVolumes = controller.getRecipeVolumes();
        ArrayList<String> recipeUnits = controller.getRecipeUnits();

        String instructions = controller.getRecipeInstructions();
        String glassType = controller.getRecipeGlassType();

        if (userName!=null) {
            ArrayList<Boolean> hasIngredient = controller.getHasIngredient(recipeIngredients);
            boolean isFavorite = controller.isFavorite(drinkName);
        }

        drinkNameTV = (TextView) findViewById(R.id.drink_name);
        drinkNameTV.setText(drinkName);

        drinkRatingTV = (TextView) findViewById(R.id.rating);
        drinkRatingTV.setText("Rating = 4.5");//////////////////////////////////// Save for later

        instructionsTV = (TextView) findViewById(R.id.instructionsTV);
        instructionsTV.setText(instructions);

        glassTV = (TextView) findViewById(R.id.glassTypeTV);
        glassTV.setText(glassType);

        RecyclerView rv = findViewById(R.id.rvIngredients);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // must pass values for user/non-user, and for ingredient being in user's cabinet and or shopping list
        // use different constructors for whether user or not, and within RecipeActivity or just in
        // create activity
        adapter = new CreateRecyclerViewAdapter(this, recipeIngredients, recipeVolumes,
                recipeUnits);
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

            case R.id.addFavesBtn:


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
    public void logToggle(String userName) {

        if (userName != null) {
            SharedPrefsManager.setUserName(DrinkRecipeActivity.this, null);
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
