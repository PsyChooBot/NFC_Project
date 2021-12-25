package com.example.alpha_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class SettingsActivity extends AppCompatActivity {

    protected EditText etname, etphon,etmail,etsurname, etins, etlinke;
    public File f;
    private SharedPreferences mPrefs; // la mia preferenza per utilizzare onPause();
    private String name;
    private String surname;
    private String phon;
    private String mail;
    private String ins;
    private String linke;

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
        le varie informazioni e se non avevo scritto niente di default salvo null*/
        mPrefs = getSharedPreferences(getLocalClassName(), MODE_PRIVATE);
        name = mPrefs.getString("Name",null);
        etname.setText(name);
        surname = mPrefs.getString("Surname",null);
        etsurname.setText(surname);
        phon = mPrefs.getString("Phone Number",null);
        etphon.setText(phon);
        mail = mPrefs.getString("E-Mail",null);
        etmail.setText(mail);
        ins = mPrefs.getString("Instagram Tag",null);
        etins.setText(ins);
        linke = mPrefs.getString("LinkedinTag",null);
        etlinke.setText(linke);

        
        // è il tasto per creare un file vcf
        Button create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    openSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        /* Button that save information and create an vcard file vcf */

    public void openSave() throws IOException {
        // creo un file con path contact.vcf
        f=new File("contact.vcf");
        FileOutputStream fop=new FileOutputStream(f);

        if(f.exists())
        {   //inizio a scrivere nella stringa str ciò che voglio inserire nel file vcf
            String str="BEGIN:VCARD\n" +
                    "VERSION:4.0\n" +
                    "N:"+etname.getText().toString()+" " + etsurname.getText().toString()+";;;\n" +
                    "FN: " +etsurname.getText().toString()+" " + etname.getText().toString()+"\n"+
                    "PHON;TYPE=work,voice;VALUE=uri:tel:"+etphon.getText().toString()+"\n"+
                    "EMAIL:"+etmail.getText().toString()+"\n"+
                    "INSTAGRAM:"+etins.getText().toString()+"\n"+
                    "LINKEDIN:"+etlinke.getText().toString()+"\n"+
                    "END:VCARD";
            fop.write(str.getBytes()); // inserisco la stringa dentro il file
            //Now read the content of the vCard after writing data into it
            BufferedReader br = null;
            String sCurrentLine;
            br = new BufferedReader(new FileReader("contact.vcf"));
            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
            }
            //close the output stream and buffer reader
            fop.flush();
            fop.close();
            System.out.println("The data has been written");
        } else
            System.out.println("This file does not exist");
    }

    /*  Metodo reset button*/
  public void openReset(){
      etname.setText(null);
      etsurname.setText(null);
      etphon.setText(null);
      etins.setText(null);
      etmail.setText(null);
      etlinke.setText(null);
    }

    /*  Metodo return button --- ritorno a MainActivity*/
    public void openReturn(){
       finish();

    }

    // Anche se esco da SettingsActivity non perdo le informazioni inserite
    protected void onPause() {
        super.onPause();

        // Salvo i valori nelle preferenze
        SharedPreferences.Editor name = mPrefs.edit().putString("Name", etname.getText().toString());
        name.apply();
        SharedPreferences.Editor surname = mPrefs.edit().putString("Surname", etsurname.getText().toString());
        surname.apply();
        SharedPreferences.Editor phone = mPrefs.edit().putString("Phone Number", etphon.getText().toString());
        phone.apply();
        SharedPreferences.Editor e_mail = mPrefs.edit().putString("E-Mail", etmail.getText().toString());
        e_mail.apply();
        SharedPreferences.Editor instagram = mPrefs.edit().putString("Instagram Tag", etins.getText().toString());
        instagram.apply();
        SharedPreferences.Editor linkedin = mPrefs.edit().putString("LinkedinTag", etlinke.getText().toString());
        linkedin.apply();
    }


}
