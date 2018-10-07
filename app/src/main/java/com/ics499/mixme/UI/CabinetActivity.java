package com.ics499.mixme.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

public class CabinetActivity extends AppCompatActivity implements LogToggle, View.OnClickListener {

    TextView greeting;
    Button logBtn;
    String userName;
    Button searchDrinksBtn;

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
}
