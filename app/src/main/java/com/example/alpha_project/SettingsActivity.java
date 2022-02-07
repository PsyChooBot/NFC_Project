package com.example.alpha_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;



public class SettingsActivity extends AppCompatActivity {

    protected EditText etname, etphon,etmail,etsurname, etins, etlinke;
    public File f;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Surname = "surnameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Ins = "insKey";
    public static final String Linke = "linkeKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etname = findViewById(R.id.nome);
        etsurname = findViewById(R.id.cognome);
        etphon = findViewById(R.id.phone);
        etmail = findViewById(R.id.mail);
        etins= findViewById(R.id.instagram);
        etlinke= findViewById(R.id.linkedin);

        /* Imposto i valori dell'EditText con i valori memorizzati : per esempio quando esco dall'activity setting e ritorno al main avendo richiamato nel codice onPause salvo 
        le varie informazioni e se non avevo scritto niente di default salvo il valore di default*/
        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String n = sharedpreferences.getString(Name,null);
        String su = sharedpreferences.getString(Surname,null);
        String ph = sharedpreferences.getString(Phone,null);
        String e = sharedpreferences.getString(Email,null);
        String i = sharedpreferences.getString(Ins,null);
        String l = sharedpreferences.getString(Linke,null);

        etname.setText(n);
        etsurname.setText(su);
        etphon.setText(ph);
        etmail.setText(e);
        etins.setText(i);
        etlinke.setText(l);

        Button b1 = (Button) findViewById(R.id.save);
        // è il tasto per salvare i dati

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = etname.getText().toString();
                String su = etsurname.getText().toString();
                String ph = etphon.getText().toString();
                String e = etmail.getText().toString();
                String i = etins.getText().toString();
                String l = etlinke.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, n);
                editor.putString(Surname, su);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.putString(Ins, i);
                editor.putString(Linke, l);

                editor.apply();
                Toast.makeText(SettingsActivity.this,"Saved",Toast.LENGTH_LONG).show();
            }
        });


        Button reset = (Button) findViewById(R.id.RESETBTN);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReset();
            }

        });

        Button indietro = (Button) findViewById(R.id.ritorno);

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReturn();
            }

        });
    }

    /*  Metodo reset button*/
  public void openReset(){
      etname.setText(null);
      etsurname.setText(null);
      etphon.setText(null);
      etins.setText(null);
      etmail.setText(null);
      etlinke.setText(null);
      String n = etname.getText().toString();
      String su = etsurname.getText().toString();
      String ph = etphon.getText().toString();
      String e = etmail.getText().toString();
      String i = etins.getText().toString();
      String l = etlinke.getText().toString();

      SharedPreferences.Editor editor = sharedpreferences.edit();
      editor.putString(Name, n);
      editor.putString(Surname, su);
      editor.putString(Phone, ph);
      editor.putString(Email, e);
      editor.putString(Ins, i);
      editor.putString(Linke, l);

      editor.apply();
      Toast.makeText(SettingsActivity.this,"Reseted",Toast.LENGTH_LONG).show();
    }

    /*  Metodo return button --- ritorno a MainActivity*/
    public void openReturn(){
       finish();

    }

}
