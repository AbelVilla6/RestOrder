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

public class CancelReservationActivity extends AppCompatActivity {
    Button bCancel;
    Vibrator vibratorC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        bCancel = findViewById(R.id.button2);
        vibratorC = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorC.vibrate(100);
                Toast.makeText(CancelReservationActivity.this, "Reservation cancelled", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CancelReservationActivity.this, MenuCustomerActivity.class));

            }
        });
    }


    public void log_out(View view) {
        Toast.makeText(this, "See you soon!", Toast.LENGTH_SHORT).show();
        logoutMenu(CancelReservationActivity.this);
    }

    private void logoutMenu(CancelReservationActivity menuCustomerActivity) {
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

    public void menu_customer(View view) {
        Toast.makeText(this, "Reservation cancelled", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CancelReservationActivity.this, MenuCustomerActivity.class));
    }
}
