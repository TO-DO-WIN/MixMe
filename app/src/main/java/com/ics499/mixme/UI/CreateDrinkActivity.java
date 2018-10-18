package com.ics499.mixme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class CreateDrinkActivity extends AppCompatActivity implements LogToggle,
        View.OnClickListener, IngredientRecyclerViewAdapter.ItemClickListener{

    String userName;
    TextView greeting;
    EditText instructions;
    Button logBtn, submitBtn;
    Button searchDrinksBtn, favesBtn, shoppingBtn, cabinetBtn, randomBtn;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_drink);

        userName = SharedPrefsManager.getUserName(CreateDrinkActivity.this);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        searchDrinksBtn = (Button) findViewById(R.id.searchNVBtn);
        searchDrinksBtn.setOnClickListener(this);

        favesBtn = (Button) findViewById(R.id.favesNVBtn);
        favesBtn.setOnClickListener(this);

        shoppingBtn = (Button) findViewById(R.id.shoppingNVBtn);
        shoppingBtn.setOnClickListener(this);

        cabinetBtn = (Button) findViewById(R.id.cabinetNVBtn);
        cabinetBtn.setOnClickListener(this);

        randomBtn = (Button) findViewById(R.id.randomNVBtn);
        randomBtn.setOnClickListener(this);

        instructions = (EditText) findViewById(R.id.instructionsText);

        controller = Controller.getInstance();

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.submitBtn:
                String drinkString = instructions.getText().toString();

                //Temporary means of inputting a drink to be created.
                // first drink is parsed to create a name, ingredients, directions and glass type
                // these are separated by '-' There should be four elements
                String[] drinkAttributes = drinkString.split("-");

                // next ingredients, the second element is parsed and separated by :

                String[] ingredients = drinkAttributes[1].split(":");

                // Now ingredients array holds unkonwn number of ingredient strings
                // each string has a name, a volume and a unit these are separated by X

                ArrayList<String> ingredientNames = new ArrayList<>();
                ArrayList<String> ingredientVolumes = new ArrayList<>();
                ArrayList<String> ingredientUnits = new ArrayList<>();
                ArrayList<Integer> ingredientIDs = new ArrayList<>();
                for (String s: ingredients){
                    String[] parsedIngred = s.split("X");
                    ingredientNames.add(parsedIngred[0]);
                    ingredientVolumes.add(parsedIngred[1]);
                    ingredientUnits.add(parsedIngred[2]);
                    ingredientIDs.add(Integer.parseInt(parsedIngred[3]));
                }

                // TO-DO -- this will take an SBA instead of Integer ArrayList for IDs
                controller.createDrink(drinkAttributes[0], ingredientNames, ingredientVolumes,
                        ingredientUnits, ingredientIDs, drinkAttributes[2], drinkAttributes[3]);

                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.SearchActivity");
                startActivity(intent);
                break;

            case R.id.searchNVBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.SearchActivity");
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

    }

    @Override
    public void logToggle(String userName) {
        SharedPrefsManager.setUserName(CreateDrinkActivity.this, null);
        Intent intent = new Intent();
        intent.setClassName("com.ics499.mixme",
                "com.ics499.mixme.UI.SearchActivity");
        startActivity(intent);
    }
}
