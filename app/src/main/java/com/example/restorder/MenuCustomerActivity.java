package com.example.restorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuCustomerActivity extends AppCompatActivity {
    Button bMakeR;
    Button bCancelR;
    Vibrator vibratorM;
    Vibrator vibratorC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customer);

        bMakeR = findViewById(R.id.button3);
        vibratorM = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        bCancelR = findViewById(R.id.button2);
        vibratorC = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        bMakeR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorM.vibrate(100);
                startActivity(new Intent(MenuCustomerActivity.this, MakeReservationActivity.class));

            }
        });
        bCancelR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorC.vibrate(100);
                startActivity(new Intent(MenuCustomerActivity.this, CancelReservationActivity.class));

            }
        });
    }


    public void log_out(View view) {
        Toast.makeText(this, "See you soon!", Toast.LENGTH_SHORT).show();
        logoutMenu(MenuCustomerActivity.this);
    }

    private void logoutMenu(MenuCustomerActivity menuCustomerActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(menuCustomerActivity);
        builder.setTitle("LogOut");
        builder.setMessage("Are you should you want to log out ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void cancel_reservation(View view) {
        startActivity(new Intent(MenuCustomerActivity.this, CancelReservationActivity.class));
    }
    public void make_reservation(View view) {
        startActivity(new Intent(MenuCustomerActivity.this, MakeReservationActivity.class));
    }



}
