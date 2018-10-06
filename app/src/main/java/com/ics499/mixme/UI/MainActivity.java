package com.ics499.mixme.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ics499.mixme.R;
import com.ics499.mixme.utilities.SharedPrefsManager;


/**
 * Main activity will display logo while connecting to DB and checking SharedPrefernces to
 * determine if a user is currently logged in or not. Upon determination of logged in status,
 * intent will be started for either Cabinet (logged in) or LogIn (not logged in).
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();

        // Check if User is Already Logged In

        if (SharedPrefsManager.getUserName(MainActivity.this) == null){
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.LoginActivity");
        } else {
            intent.setClassName("com.ics499.mixme",
                    "com.ics499.mixme.UI.CabinetActivity");
        }
        startActivity(intent);
    }
}
