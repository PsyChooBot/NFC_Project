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

        // This method is called when the second activity finishes
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            /* check that it is the SecondActivity with an OK result */
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    // get String data from Intent
                    String nome = getIntent().getStringExtra("name");
                    String cognome = getIntent().getStringExtra("surname");
                    String phone = getIntent().getStringExtra("Phone Number");
                    String mail = getIntent().getStringExtra("E-mail");
                    String instagram = getIntent().getStringExtra("Instagram Tag");
                    String linkedin = getIntent().getStringExtra("Linkedin");
                }
            }
        }

    public void openShare(){
        Intent intent = new Intent(this,ShareActivity.class);
        startActivity(intent);
    }
}

