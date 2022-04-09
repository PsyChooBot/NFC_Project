package com.example.alpha_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.net.DatagramPacket;

public class NFC_Activity extends AppCompatActivity {

    private static final int PICKFILE_VCF_FILE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        getSupportActionBar().hide();
        check_en_NFC_ABeam(); // controllo nfc attivo e beam attivo
    }

    public void sendFile(View view) // funzione di selezione file da inviare
    {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*"); // tipo generale
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(chooseFile, PICKFILE_VCF_FILE);
        Toast.makeText(this, "Seleziona un vcard", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) // effettivo invio vcf file
    {
        if (requestCode == PICKFILE_VCF_FILE && resultCode == Activity.RESULT_OK)
        {
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri uriVcf = null;
            if (resultData != null) {
                uriVcf = resultData.getData(); // uso  di uri (tipo indirizzo) per  l'invio
                try {
                    NfcAdapter nfcAdapter;
                    nfcAdapter = NfcAdapter.getDefaultAdapter(this);
                    nfcAdapter.setBeamPushUris(new Uri[] {uriVcf}, this); // vcf file accodato su android beam
                    Toast.makeText(this, "Avvicina il secondo dispositivo! L'invio rimarrà attivo per 45s.", Toast.LENGTH_LONG).show();
                    // possibile schermata dell'invio //aggiungere modo per concludere la trasmissione file
                } catch (Exception e) {
                    Toast.makeText(this, "Qualcosa è andato storto riprovare l'invio", Toast.LENGTH_LONG).show();
                }
                // Perform operations on the document using its URI.

                //tempo attesa prima di tornare nella main activity (pairing tramite nfc)
                Handler h= new Handler();
                h.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        //tempo attesa prima di tornare nella main activity (pairing tramite nfc)
                        Toast.makeText(NFC_Activity.this, "Time exceeded", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },45000);//definisco la durata della schermata in millisec

            }
        }
    }
    public void check_en_NFC_ABeam() // controllo
    {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!nfcAdapter.isEnabled() || !nfcAdapter.isNdefPushEnabled())
        {
            Toast.makeText(this, "To use this app enable NFC and Android Beam!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
    }

    //  roba accodata beam rimossa, aggiungere eventuali messaggini tipo toast-> basta tornare nella home page per togliere gli accodati

    public void BACK(View view) // usare bottone per tornare alla home
    {
        finish();
        Toast.makeText(this, "Puoi disattivare NFC", Toast.LENGTH_SHORT).show();
    }


}