package com.example.alpha_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class Home_Screen extends AppCompatActivity {

    LottieAnimationView loading; /*creo una variabile di tipo lottie che mi permette poi di gestire lanimation*/

    Handler h= new Handler(); //creo un hendler che mi permette di definire per quanto tempo la mia schermata home sarà visibile
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportActionBar().hide();

        loading = findViewById(R.id.loading);/*connessione della variabile di animazione con l'equivalente xml e avvio animazione*/
        loading.playAnimation();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i= new Intent(Home_Screen.this,MainActivity.class); //definisco che dopo che il la mia schermata avrà raggiunto il tempo di durata definito
                                                                                        //passo al main
                startActivity(i);
                finish();

            }
        },3500);//definisco la durata della mia schermata in millisec
    }
}