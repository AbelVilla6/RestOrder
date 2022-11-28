package com.example.restorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText inputUserName, inputPassword,inputEmail;
    Button bSignIn;
    Vibrator vibratorS;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        inputUserName=findViewById(R.id.username);
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.passwd);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(RegistrationActivity.this);

        bSignIn = findViewById(R.id.button1);
        vibratorS = (Vibrator)getSystemService(VIBRATOR_SERVICE);


        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorS.vibrate(100);
                if(checkCredentials()){
                    Toast.makeText(RegistrationActivity.this, "Account Created succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, LoginCustomerActivity.class));
                }
            }
        });
    }
    private boolean checkCredentials(){
        String username = inputUserName.getText().toString();
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

        if(username.isEmpty() || username.length()<7){
            showError(inputUserName, "Your username is not valid!");
            return false;
        }
        else if (email.isEmpty() || !email.contains("@")){
            showError(inputEmail, "Email is not valid!");
            return false;
        }
        else if (password.isEmpty() || password.length()<7){
            showError(inputPassword, "Password must have at least 7 charaacters");
            return false;
        }
        else{
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait, while cheking your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Toast.makeText(RegistrationActivity.this,"Succesfull Registration",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistrationActivity.this, LoginCustomerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
       return true;
    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }

    public void log_inC(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginCustomerActivity.class));
    }
}