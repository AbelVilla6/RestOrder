package com.example.restorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity {
    TextView selectLang, langDialog,button;
    boolean selected_language = true;
    Context context;
    Resources resources;
    Button bNext;
    Vibrator vibrator;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        selectLang=(TextView) findViewById(R.id.textview1);
        langDialog =(TextView) findViewById(R.id.textview2);
        button = (Button) findViewById(R.id.bNext);
        bNext = (Button) findViewById(R.id.bNext);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                startActivity(new Intent(LanguageActivity.this, WelcomeActivity.class));

            }
        });

        langDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] languages={"Arab","Deutsch","Italiano","Français","English","Español"};

                int checkedItem;
                if(selected_language){
                    checkedItem=0;
                }
                else{
                    checkedItem=1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(LanguageActivity.this);

                builder.setTitle("Select a language")
                        .setSingleChoiceItems(languages, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                langDialog.setText((languages[i]));
                                if(languages[i].equals("English")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"en");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));

                                }
                                if(languages[i].equals("Español")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"es");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));
                                }
                                if(languages[i].equals("Arab")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"ar");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));
                                }
                                if(languages[i].equals("Italiano")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"it");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));
                                }
                                if(languages[i].equals("Deutsch")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"de");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));
                                }
                                if(languages[i].equals("Français")){
                                    context=LocaleHelper.setLocale(LanguageActivity.this,"fr");
                                    resources=context.getResources();
                                    selectLang.setText(resources.getString(R.string.language));
                                    button.setText(resources.getString(R.string.next));
                                }
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                dialog.dismiss();
                            }
                        });
                builder.create().show();
                }
        });

    }

    public void welcome(View view) {
        startActivity(new Intent(LanguageActivity.this, WelcomeActivity.class));
    }
}
