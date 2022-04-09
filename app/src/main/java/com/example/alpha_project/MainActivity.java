package com.example.alpha_project;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
        //istruzioni preliminari all'avvio dell'app controllo nfc e beam
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "App can not be executed", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Toast.makeText(this, "Android Beam not supported", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "App can not  be executed", Toast.LENGTH_SHORT).show();
            finish();
        }

        getSupportActionBar().hide();

        lottiePlay = findViewById(R.id.lottiePlay);

        lottiePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play){
                    lottiePlay.setMinAndMaxProgress(1.0f,1.0f);
                    lottiePlay.playAnimation();
                    play = false;
                }
                else
                {
                    lottiePlay.setMinAndMaxProgress(0.0f,1.0f);
                    lottiePlay.playAnimation();
                    play = true;

                }
                Handler h= new Handler();
                h.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        openShare();
                    }
                },1300);//definisco la durata della schermata in millisec
            }
        });

        settings = (ImageButton) findViewById(R.id.settingsBTN);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
    }

    public void openSettings() {
        Intent setting = new Intent(this, SettingsActivity.class);
        startActivity(setting);
    }

    public void openShare(){
        Intent intent = new Intent(this,NFC_Activity.class);
        startActivity(intent);
    }

}

