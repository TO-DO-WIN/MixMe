package com.ics499.mixme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ics499.mixme.R;
import com.ics499.mixme.utilities.LogToggle;
import com.ics499.mixme.utilities.SharedPrefsManager;

public class SearchActivity extends AppCompatActivity implements LogToggle,
        View.OnClickListener {

    TextView greeting;
    Button logBtn;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = SharedPrefsManager.getUserName(SearchActivity.this);

        if (userName != null) {
            setContentView(R.layout.activity_search);
            greeting = (TextView) findViewById(R.id.greeting);
            greeting.setText(userName);
        }
        else
            setContentView(R.layout.search_guest);



        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setOnClickListener(this);
    }

    @Override
    public void logToggle(String userName) {
        if (userName != null){
            SharedPrefsManager.setUserName(SearchActivity.this, null);
            logBtn.setText("Log In");
        } else {
            Intent intent = new Intent();
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LoginActivity");
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.logBtn:
                logToggle(userName);
                break;
        }
    }
}
