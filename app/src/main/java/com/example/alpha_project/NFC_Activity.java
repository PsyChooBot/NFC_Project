package com.example.alpha_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;

public class NFC_Activity extends AppCompatActivity {

    NfcAdapter nfcAdapter;

    boolean androidBeamAvailable = false;     // Condizione per indicare  che Android Beam è disponibile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        //Caso NFC non disponibile nel device
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)){
            /*
            disattiviamo  le opzioni di nfc, cioè nel nostro caso disattiviamo il bottone di Share
             */

            //Caso Android Beam file transfer non disponibile nel device
        } else if (Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN_MR1){
            //se android beam non è disponibile non possiamo continuare con lesecuzione del programma
            androidBeamAvailable = false;
            /*
            disattiviamo le opzioni  di file trnasfer tramite android beam
             */

            //Android beam file transfer è disponibile quindi possiamo continuare
        } else {
            androidBeamAvailable = true;
            nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        }

    }
}