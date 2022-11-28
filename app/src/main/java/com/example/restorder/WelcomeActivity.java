package com.example.restorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {
    Button bWaiter;
    Button bCustomer;
    Vibrator vibratorW;
    Vibrator vibratorC;
    //Spinner spinner;
    //public static final String[] Languages = {"Select Language", "English","Spanish"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        bWaiter = findViewById(R.id.button1);
        bCustomer = findViewById(R.id.button2);
        vibratorW = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibratorC = (Vibrator)getSystemService(VIBRATOR_SERVICE);



        bWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorW.vibrate(100);
                startActivity(new Intent(WelcomeActivity.this, LoginWaiterActivity.class));

            }
        });
        bCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorC.vibrate(100);
                startActivity(new Intent(WelcomeActivity.this, LoginCustomerActivity.class));

            }
        });
/*
        spinner.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if(selectedLang.equals("English")){
                    setLocal(WelcomeActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("Spanish")){
                    setLocal(WelcomeActivity.this,"es");
                    finish();
                    startActivity(getIntent());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
*/
    }
    /*public void setLocal(Activity activity,String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }*/

    public void sign_in(View view) {
        startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
    }
    public void log_inC(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginCustomerActivity.class));
    }
    public void log_inW(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginWaiterActivity.class));
    }

}