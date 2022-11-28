package com.example.restorder;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class LoginCustomerActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101010;
    Button bLogInC;
    Vibrator vibrator;
    ImageView imageView;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    FirebaseAuth mAuth;


    EditText inputPassword,inputEmail;
    ProgressDialog mLoadingBar;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        bLogInC = findViewById(R.id.button1);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);


        bLogInC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(100);
                if(checkCredentials()){
                    startActivity(new Intent(LoginCustomerActivity.this, MenuCustomerActivity.class));
                }
            }
        });


        inputEmail=findViewById(R.id.editText2);
        inputPassword=findViewById(R.id.editText);
        mAuth=FirebaseAuth.getInstance();


        mLoadingBar= new ProgressDialog(LoginCustomerActivity.this);


        imageView=findViewById(R.id.imageView4);
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "FingerPrint sensor is not recognized in this device", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "FingerPrint sensor is not available", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginCustomerActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(LoginCustomerActivity.this, MenuCustomerActivity.class));
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        imageView.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }



    public void sign_in(View view){
        startActivity(new Intent(LoginCustomerActivity.this, RegistrationActivity.class));
    }

    public void menu_customer(View view) {
        if(checkCredentials()){
        startActivity(new Intent(LoginCustomerActivity.this, MenuCustomerActivity.class));}
    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }
    private boolean checkCredentials(){
        boolean result=false;
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")){
            showError(inputEmail, "Email is not valid!");
            return result;
        }
        else if (password.isEmpty() || password.length()<7){
            showError(inputPassword, "Password must have at least 7 charaacters");
            return result;
        }
        else{
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait, while checking your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mLoadingBar.dismiss();
                        //this.result=true;
                        Toast.makeText(LoginCustomerActivity.this,"Succesfully Logged In",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginCustomerActivity.this, MenuCustomerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                    else{
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginCustomerActivity.this, "Wrong credentials or Account does not exist", Toast.LENGTH_SHORT).show();
                    }
                }

            });
            return result;
        }
    }
}