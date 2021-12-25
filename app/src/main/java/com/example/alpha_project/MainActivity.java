package com.example.alpha_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private  Button settings;
    private Button share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.settingsBTN);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();

            }

        });

        share = (Button) findViewById(R.id.shareBTN);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShare();
            }
        });
    }


        public void openSettings() {
         Intent setting = new Intent(this, SettingsActivity.class);

            startActivity(setting);
    }



    public void openShare(){
        Intent intent = new Intent(this,ShareActivity.class);
        startActivity(intent);
    }
}

