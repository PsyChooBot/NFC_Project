package com.example.alpha_project;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SettingsActivity extends AppCompatActivity {

    protected EditText etname, etphon,etmail,etsurname, etins, etlinke, etAddr, etTwit, etNote;

    private Button btn;
    private static final String VCF_DIRECTORY = "/vcf_contact";
    private File vcfFile;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Surname = "surnameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String Ins = "insKey";
    public static final String Linke = "linkeKey";
    public static final String Addr = "addrKey";
    public static final String Twit = "twitKey";
    public static final String Note = "noteKey";


    private boolean save=false, reset=false;
    LottieAnimationView usericon;
    LottieAnimationView lottiereset;
    LottieAnimationView lottiesave;

    ImageButton back;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        usericon = findViewById(R.id.usericon);
        usericon.playAnimation();




        etname = findViewById(R.id.nome);
        etsurname = findViewById(R.id.cognome);
        etphon = findViewById(R.id.phone);
        etmail = findViewById(R.id.mail);
        etins= findViewById(R.id.instagram);
        etlinke= findViewById(R.id.linkedin);
        etAddr= findViewById(R.id.address);
        etTwit= findViewById(R.id.twitter);
        etNote= findViewById(R.id.note);


        /* Imposto i valori dell'EditText con i valori memorizzati : per esempio quando esco dall'activity setting e ritorno al main avendo richiamato nel codice onPause salvo 
        le varie informazioni e se non avevo scritto niente di default salvo il valore di default*/
        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String n = sharedpreferences.getString(Name,null);
        String su = sharedpreferences.getString(Surname,null);
        String ph = sharedpreferences.getString(Phone,null);
        String e = sharedpreferences.getString(Email,null);
        String i = sharedpreferences.getString(Ins,null);
        String l = sharedpreferences.getString(Linke,null);
        String a = sharedpreferences.getString(Addr,null);
        String t = sharedpreferences.getString(Twit,null);
        String no = sharedpreferences.getString(Note,null);

        etname.setText(n);
        etsurname.setText(su);
        etphon.setText(ph);
        etmail.setText(e);
        etins.setText(i);
        etlinke.setText(l);
        etAddr.setText(a);
        etTwit.setText(t);
        etNote.setText(no);

         lottiesave = findViewById(R.id.lottiesave);

       //Button b1 = (Button) findViewById(R.id.save);
        // è il tasto per salvare i dati

        lottiesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(save){
                    lottiesave.setMinAndMaxProgress(1.0f,1.0f);
                    lottiesave.playAnimation();
                    save = false;
                } else {
                    lottiesave.setMinAndMaxProgress(0.0f,1.0f);
                    lottiesave.playAnimation();
                    save = true;

                }

                String n = etname.getText().toString();
                String su = etsurname.getText().toString();
                String ph = etphon.getText().toString();
                String e = etmail.getText().toString();
                String i = etins.getText().toString();
                String l = etlinke.getText().toString();
                String a = etAddr.getText().toString();
                String t = etTwit.getText().toString();
                String no = etNote.getText().toString();


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, n);
                editor.putString(Surname, su);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.putString(Ins, i);
                editor.putString(Linke, l);
                editor.putString(Addr, a);
                editor.putString(Twit, t);
                editor.putString(Note, no);

                editor.apply();
                Toast.makeText(getApplicationContext(), "Saved"+" \nName -  " + etname.getText().toString() + " \n" + "Cognome -  " + etsurname.getText().toString()
                        + "\nE-Mail -  " + etmail.getText().toString() + " \n" + "Contact -  " + etphon.getText().toString()+ "\nInstagram -  " + etins.getText().toString()
                        +"\nLinkedin -  " + etlinke.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


       // Button reset = (Button) findViewById(R.id.RESETBTN);
        lottiereset = findViewById(R.id.lottiereset);
        lottiereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reset){
                    lottiereset.setMinAndMaxProgress(1.0f,1.0f);
                    lottiereset.playAnimation();
                    reset = false;
                } else {
                    lottiereset.setMinAndMaxProgress(0.0f,1.0f);
                    lottiereset.playAnimation();
                    reset = true;

                }
                openReset();
            }

        });

       //Button indietro = (Button) findViewById(R.id.ritorno);
        back = findViewById(R.id.ritorno);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReturn();
            }

        });
        //Bottone per creare il file vcf
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // creazione directory vcf per la prima volta
                    File vcfdirectory = new File(getExternalFilesDir(null) + VCF_DIRECTORY); // riferimento file
                    if (!vcfdirectory.exists()) // controllo esistenza directory
                        vcfdirectory = createDirectory(VCF_DIRECTORY);

                    // have the object build the directory structure, if needed.

                    /*Following line specifies name of the VCF or vCard file.
                    Here, we will fetch current time as a name of vCard file.
                    It also includes millisecond so that always, unique name will be generated.*/
                    vcfFile = new File(vcfdirectory, "N:"/*inserire tipologia vcard: lavoro, personale, immmediato*/+etname.getText().toString()+" "+etsurname.getText().toString() + ".vcf");

                    FileWriter fw = null;
                    //below code will generate VCF or vCard file
                    fw = new FileWriter(vcfFile);
                    fw.write("BEGIN:VCARD\r\n");
                    fw.write("VERSION:3.0\r\n");
                    fw.write("N:" + etname.getText().toString()  +" "+ etsurname.getText().toString() + "\r\n");
                    fw.write("FN:" + etname.getText().toString()  +" "+ etsurname.getText().toString() + "\r\n");
                    fw.write("TEL;CELL;VOICE:" + etphon.getText().toString() + "\r\n");
                    fw.write("ADR;WORK:;;" + etAddr.getText().toString() + "\r\n");
                   // ADR;HOME:;;
                    fw.write("URL;INS:" + "https://www.instagram.com/" + etins.getText().toString() + "\r\n");
                    fw.write("URL;TWITTER:" + "https://twitter.com/" + etTwit.getText().toString() + "\r\n");   // aggiungere edit text twitter
                    fw.write("URL;LINKEDIN:"+ etlinke.getText().toString() + "\r\n"); // +"https://" da app linkedin
                    fw.write("EMAIL;TYPE=PREF,INTERNET:" + etmail.getText().toString() + "\r\n"); // aggiungere campo note
                    fw.write("NOTE:" + etNote.getText().toString() + "\r\n");
                    fw.write("END:VCARD\r\n");
                    fw.close();


                    Toast.makeText(SettingsActivity.this, "VCard Created!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SettingsActivity.this, "path : "+ vcfdirectory.getPath(), Toast.LENGTH_LONG).show(); // path ubicazione file
                } catch (IOException e) {
                    /*printStackTrace() helps the programmer to understand where the actual problem occurred.
                    printStacktrace() is a method of the class Throwable of java.lang package.
                    It prints several lines in the output console. The first line consists of several strings.
                    It contains the name of the Throwable sub-class & the package information.
                    From second line onwards, it describes the error position/line number beginning with at.*/
                    e.printStackTrace();
                }
            }
        });

    }

    private File createDirectory(String vcfDirectory) {
        File file = new File(getExternalFilesDir(null) /*+ "/" */+ vcfDirectory);
        file.mkdir();
        Toast.makeText(getApplicationContext(), "Directory created successfully", Toast.LENGTH_LONG).show();
        return file;
    }
    /*  Metodo reset button*/
  @SuppressLint("SetTextI18n")
  public void openReset(){
      etname.setText(null);
      etsurname.setText(null);
      etphon.setText(null);
      etins.setText(null);
      etmail.setText(null);
      etlinke.setText(null);
      etAddr.setText(null);
      etTwit.setText(null);
      etNote.setText(null);


      String n = etname.getText().toString();
      String su = etsurname.getText().toString();
      String ph = etphon.getText().toString();
      String e = etmail.getText().toString();
      String i = etins.getText().toString();
      String l = etlinke.getText().toString();
      String a = etAddr.getText().toString();
      String t = etTwit.getText().toString();
      String no = etNote.getText().toString();


      SharedPreferences.Editor editor = sharedpreferences.edit();
      editor.putString(Name, n);
      editor.putString(Surname, su);
      editor.putString(Phone, ph);
      editor.putString(Email, e);
      editor.putString(Ins, i);
      editor.putString(Linke, l);
      editor.putString(Addr, l);
      editor.putString(Twit, l);
      editor.putString(Note, l);

      editor.apply();
      Toast.makeText(SettingsActivity.this,"Reseted",Toast.LENGTH_LONG).show();
    }

    /*  Metodo return button --- ritorno a MainActivity*/
    public void openReturn(){
       finish();

    }

}
