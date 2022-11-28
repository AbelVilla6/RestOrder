package com.example.restorder;

import static com.example.restorder.R.id.table_number;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeviceInTableActivity extends AppCompatActivity {
    Button bSetTable;
    Vibrator vibratorST;
    private Spinner table_number;
    private Spinner number_of_people;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_table);

        bSetTable = findViewById(R.id.button2);
        vibratorST = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        bSetTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorST.vibrate(100);
                startActivity(new Intent(DeviceInTableActivity.this, MenuDeviceInTable.class));

            }
        });

        table_number = (Spinner) findViewById(R.id.table_number);
        ArrayList<String> elementos=new ArrayList<String>();
        elementos.add("1");
        elementos.add("2");
        elementos.add("3");
        elementos.add("4");
        elementos.add("5");
        elementos.add("6");
        elementos.add("7");
        elementos.add("8");
        elementos.add("9");
        elementos.add("10");

        number_of_people = (Spinner) findViewById(R.id.number_of_people);
        ArrayList<String> elementos2=new ArrayList<String>();
        elementos2.add("2 people");
        elementos2.add("4 people");
        elementos2.add("6 people");
        elementos2.add("8 people");

        ArrayAdapter adp=new ArrayAdapter<>(DeviceInTableActivity.this, R.layout.selected_table,elementos);
        adp.setDropDownViewResource(R.layout.dropdown_item);
        ArrayAdapter adp2=new ArrayAdapter<>(DeviceInTableActivity.this, R.layout.selected_table,elementos2);
        adp2.setDropDownViewResource(R.layout.dropdown_item);

        table_number.setAdapter(adp);
        number_of_people.setAdapter(adp2);

        table_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String elemento= (String) table_number.getAdapter().getItem(position);

                Toast.makeText(DeviceInTableActivity.this, "You have selected: " + elemento, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(DeviceInTableActivity.this, "You need to select a number", Toast.LENGTH_SHORT).show();
            }
        });
        number_of_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String elementoss= (String) table_number.getAdapter().getItem(position);
                Toast.makeText(DeviceInTableActivity.this, "You have selected: " + elementoss, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(DeviceInTableActivity.this, "You need to select a number", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void set_table(View view) {
        startActivity(new Intent(DeviceInTableActivity.this, MenuDeviceInTable.class));
    }

}
