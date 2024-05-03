package com.example.billiard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY=99;
    EditText userNameEditText;
    EditText userEmailEditText;
    EditText passwordEditText;
    EditText passwordAginEditText;
    EditText phoneEditText;
    EditText addressEditText;
    Spinner spinner;
    RadioGroup accountTypeGroup;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        int secret_key= getIntent().getIntExtra("SECRET_KEY",0);
        if(secret_key != 99){
            finish();
        }
        userNameEditText=findViewById(R.id.UserNameET);
        userEmailEditText=findViewById(R.id.UserEmailET);
        passwordEditText=findViewById(R.id.PasswordET);
        passwordAginEditText=findViewById(R.id.PasswordAgainET);
        phoneEditText=findViewById(R.id.PhoneEditText);
        preferences=getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        String userName= preferences.getString("username","");
        String password=preferences.getString("password","");
        userNameEditText.setText(userName);
        passwordEditText.setText(password);
        passwordAginEditText.setText(password);
        mAuth=FirebaseAuth.getInstance();
        Log.i(LOG_TAG,"onCreate");
    }
    public void cancel(View view) {
        finish();
    }

    public void registeR(View view) {
        String userName=userNameEditText.getText().toString();
        String email=userEmailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String passwordAgain=passwordAginEditText.getText().toString();
        if(!password.equals(passwordAgain)){
            Log.e(LOG_TAG,"Nem egyezik meg a két jelszó!");
            return;
        }

        String phoneNumber=phoneEditText.getText().toString();

        Log.i(LOG_TAG, " Regisztrált: " + userName + ", jelszo: " + password);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "User created successfully");
                    startBilliard();
                } else {
                    Log.d(LOG_TAG, "User wasn't created successfully");
                    Toast.makeText(RegisterActivity.this, "User was't created successfully: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    private void startBilliard(/*registered user data*/){
        Intent intent=new Intent(this,BilliardListActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG,"onRestart");
    }
}