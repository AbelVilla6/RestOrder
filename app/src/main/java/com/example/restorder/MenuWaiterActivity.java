package com.example.restorder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class MenuWaiterActivity extends AppCompatActivity {
    Button bSetDevice;
    Button bManage;
    Vibrator vibratorSD;
    Vibrator vibratorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_waiter);

        bSetDevice = findViewById(R.id.button3);
        vibratorSD = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        bManage = findViewById(R.id.button2);
        vibratorM = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        bSetDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorSD.vibrate(100);
                startActivity(new Intent(MenuWaiterActivity.this, DeviceInTableActivity.class));

            }
        });
        bManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorM.vibrate(100);
                startActivity(new Intent(MenuWaiterActivity.this, ManageRestaurantActivity.class));

            }
        });

    }
    public void set_device(View view) {
        startActivity(new Intent(MenuWaiterActivity.this, DeviceInTableActivity.class));
    }

    public void log_out(View view) {
        Toast.makeText(this, "See you soon!", Toast.LENGTH_SHORT).show();
        logoutMenu(MenuWaiterActivity.this);
    }

    private void logoutMenu(MenuWaiterActivity menuCustomerActivity) {
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
    public void manage_restaurant(View view) {
        startActivity(new Intent(MenuWaiterActivity.this, ManageRestaurantActivity.class));
    }

}
