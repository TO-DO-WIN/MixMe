package com.ics499.mixme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

import java.util.ArrayList;

public class SelectIngredientActivity extends AppCompatActivity implements LogToggle, View.OnClickListener,
                SelectIngredientRVAdapter.ItemClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button cancelBtn, notFoundBtn;
    SearchView searchView;

    Controller controller;
    SelectIngredientRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(SelectIngredientActivity.this);

        if (userName == null) {

            // This should never happen...shouldn't be in CreateDrink without logged in
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LogInActivity");
            startActivity(intent);
        }

        setContentView(R.layout.activity_select_ingredient);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        notFoundBtn = (Button) findViewById(R.id.notFoundBtn);
        notFoundBtn.setOnClickListener(this);

        controller = Controller.getInstance();
        ArrayList<String> ingredients = controller.getIngredientList();

        RecyclerView rv = findViewById(R.id.rvSelectIngredient);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectIngredientRVAdapter(this, ingredients);
        adapter.setClickListener(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.cancelBtn:
                intent.putExtra("use values", true);
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.CreateDrinkActivity");
                startActivity(intent);
                break;

            case R.id.notFoundBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.NewIngredientActivity");
                startActivity(intent);
                break;
        }

    }

    @Override
    public void logToggle(String userName) {

        if (userName != null) {
            SharedPrefsManager.setUserName(SelectIngredientActivity.this, null);
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
        Intent intent = new Intent();

        // likely just one or the other, or sentd a string of the name and one or the other
        intent.putExtra("ingredientID", position);
        intent.putExtra("ingredientName", controller.getIngredientList().get(position));
        intent.setClassName("com.ics499.mixme",
                "com.ics499.mixme.UI.IngredientVolumeActivity");
        startActivity(intent);
    }
}
