package com.example.billiard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContactActivity extends AppCompatActivity {
    private static final String LOG_TAG = ContactActivity.class.getName();
    private FirebaseUser user;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Log.d(LOG_TAG, "sikeres belépés");
        }else{
            Log.d(LOG_TAG, "sikertelen belépés");
            finish();
        }
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poolmenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out_button) {
            FirebaseAuth.getInstance().signOut();
            Log.d(LOG_TAG, "log out-ra kattintottunk");
            finish();
            return true;
        } else if (id == R.id.contact) {
            startContact();
            Log.d(LOG_TAG, "Contact-ra kattintottunk");
            return true;
        } else if (id == R.id.opening_hours) {
            startBilliardListActivity();
            Log.d(LOG_TAG, "opening_hours-ra kattintottunk");
            return true;
        } else if (id == R.id.booking) {
            startBooking();
            Log.d(LOG_TAG, "booking-ra kattintottunk");
            return true;
        }  else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void startBilliardListActivity(){
        Intent intent=new Intent(this,BilliardListActivity.class);
        startActivity(intent);
    }
    private void startContact(){
        Intent intent=new Intent(this,ContactActivity.class);
        startActivity(intent);
    }
    private void startBooking(){
        Intent intent=new Intent(this, BookingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
        if (isUserLoggedIn()) {
            startMainFunctionality();
        } else {
            startLoginActivity();
        }

    }private void startMainFunctionality() {
        Log.i(LOG_TAG, "Felhasználó be van jelentkezve. Az alkalmazás fő tevékenysége elindítva.");
    }    private boolean isUserLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    private void startLoginActivity() {
        Log.i(LOG_TAG, "Felhasználó nincs bejelentkezve. A bejelentkezési tevékenység elindítva.");
    }
}
