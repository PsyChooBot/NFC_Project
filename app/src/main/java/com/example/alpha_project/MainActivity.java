package com.example.alpha_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private  ImageButton settings;

    private boolean play=false;
 LottieAnimationView lottiePlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        lottiePlay = findViewById(R.id.lottiePlay);

        lottiePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play){
                    lottiePlay.setMinAndMaxProgress(1.0f,1.0f);
                    lottiePlay.playAnimation();
                    play = false;
                } else {
                    lottiePlay.setMinAndMaxProgress(0.0f,1.0f);
                    lottiePlay.playAnimation();
                    play = true;

                }
            }
        });



        settings = (ImageButton) findViewById(R.id.settingsBTN);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();

            }

        });

      /*  share = (Button) findViewById(R.id.shareBTN);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShare();
            }
        });*/
    }


        public void openSettings() {
         Intent setting = new Intent(this, SettingsActivity.class);

            startActivity(setting);
    }




}

