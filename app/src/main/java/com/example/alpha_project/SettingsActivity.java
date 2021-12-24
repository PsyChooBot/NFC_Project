package com.example.alpha_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void Save_Return(View view) {

        EditText name = (EditText) findViewById(R.id.nome);
        EditText surname = (EditText) findViewById(R.id.cognome);
        EditText phonenumber = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.mail);
        EditText instagram= (EditText) findViewById(R.id.instagram);
        EditText linkedin = (EditText) findViewById(R.id.linkedin);


        Bundle data = new Bundle();
        data.putString("name", name.getText().toString());
        data.putString("Surname", surname.getText().toString());
        data.putString("Phone Number", phonenumber.getText().toString());
        data.putString("E-mail", email.getText().toString());
        data.putString("Instagram Tag", instagram.getText().toString());
        data.putString("Linkedin", linkedin.getText().toString());
 
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        setResult(RESULT_OK, intent);
        finish();
    }


}
