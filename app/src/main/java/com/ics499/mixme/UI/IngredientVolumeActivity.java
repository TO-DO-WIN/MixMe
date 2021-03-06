package com.ics499.mixme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

public class IngredientVolumeActivity extends AppCompatActivity implements LogToggle, View.OnClickListener {

    String userName;
    TextView greeting;
    Button logBtn, cancelBtn, submitBtn;
    TextView nameTV;
    EditText volumeET;
    RadioGroup unitsRG;
    RadioButton selectedRB;

    Controller controller;

    public final int NO_ID = -1;
    double ingredientVolume;
    int ingredientId, units = 0, categoryID;
    boolean isNewIngredient = false;
    String newIngredientName, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = Controller.getInstance();

        userName = SharedPrefsManager.getUserName(IngredientVolumeActivity.this);

        if (userName == null) {

            // This should never happen...shouldn't be in CreateDrink without logged in
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_ingredient_volume);



        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        nameTV = (TextView) findViewById(R.id.nameTV);

        Intent intent = getIntent();

        isNewIngredient = intent.getBooleanExtra("is new ingredient", false);
        if (isNewIngredient) {
            newIngredientName = intent.getStringExtra("ingredient_name");

            cat = intent.getStringExtra("ingredient category");

            //categoryID = (intent.getIntExtra("ingredient category", 0) - 1) % 9;
            ingredientId = NO_ID;
            nameTV.setText(newIngredientName);
        } else {
            ingredientId = intent.getIntExtra("ingredientID", 0);
            nameTV.setText(controller.getIngredientName(ingredientId));
        }
/////////////////////////////////////////////////////////////////////
        volumeET = (EditText) findViewById(R.id.volumeET);

        unitsRG = (RadioGroup) findViewById(R.id.unitsRG);


////////////////////////////////////////////////////////////
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);


    }

    public void logToggle(String userName) {

        if (userName != null) {
            SharedPrefsManager.setUserName(IngredientVolumeActivity.this, null);
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
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.cancelBtn:
                intent.putExtra("use values", true);
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.CreateDrinkActivity");
                startActivity(intent);
                break;

            case R.id.submitBtn:
                // will need try catch for bad data input
                ingredientVolume = Double.parseDouble(volumeET.getText().toString());

                int selectedID = unitsRG.getCheckedRadioButtonId();
                selectedRB = (RadioButton) findViewById(selectedID);
                String units = selectedRB.getText().toString();

                controller.setCreationIngredient(ingredientId, ingredientVolume, units, newIngredientName, cat);

                Log.d("Debug", "categoryID" + categoryID);

                intent.putExtra("use values", true);
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.CreateDrinkActivity");
                startActivity(intent);
                break;
        }
    }
}
