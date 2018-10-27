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
import android.widget.Spinner;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.controller.Controller;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

public class NewIngredientActivity extends AppCompatActivity implements View.OnClickListener,
        LogToggle {

    String userName;
    TextView greeting;
    EditText nameET;
    Button logBtn, cancelBtn, submitBtn;
    RadioGroup categoryRG;
    RadioButton selectedRB;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ingredient);

        controller = Controller.getInstance();

        userName = SharedPrefsManager.getUserName(NewIngredientActivity.this);

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        nameET = (EditText) findViewById(R.id.nameET);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        categoryRG = (RadioGroup) findViewById(R.id.categoryRG);

        // need to figure out this
        //spiritRB = (RadioButton) findViewById(R.id.spirit)

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                logToggle(userName);
                break;

            case R.id.cancelBtn:
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.SelectIngredientActivity");
                startActivity(intent);
                break;

            case R.id.submitBtn:
                intent.putExtra("is new ingredient", true);
                intent.putExtra("ingredient_name", nameET.getText().toString());

                // this probably want to change to stringExtra instead???
                int selectedID = categoryRG.getCheckedRadioButtonId();
                selectedRB = (RadioButton) findViewById(selectedID);
                String catName = selectedRB.getText().toString();
                Log.i("Debug", catName);

                intent.putExtra("ingredient category", catName);
                categoryRG.clearCheck();
                intent.setClassName("com.ics499.mixme",
                        "com.ics499.mixme.UI.IngredientVolumeActivity");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void logToggle(String userName) {
        SharedPrefsManager.setUserName(NewIngredientActivity.this, null);
        Intent intent = new Intent();
        intent.setClassName("com.ics499.mixme",
                "com.ics499.mixme.UI.SearchActivity");
        startActivity(intent);
    }
}
