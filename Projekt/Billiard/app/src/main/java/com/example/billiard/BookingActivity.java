package com.example.billiard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    private static final String LOG_TAG = BookingActivity.class.getName();
    private FirebaseUser user;
    private TextView displayTV;
    private TextView dateTV;
    int szam=0;
    private  TextView timeTV;
    private Button dialogBt;
    Date date;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private NotificationHandler mNotificationHandler;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            finish();
        }
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timeTV = findViewById(R.id.timeText);
        dateTV = findViewById(R.id.dateText); // Initialize dateTV
        displayTV=findViewById(R.id.display);

        dialogBt = findViewById(R.id.dialogButton);
        mNotificationHandler=new NotificationHandler(this);
        dialogBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        szam=Integer.valueOf(displayTV.getText().toString());

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("booking");
    }

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date=new Date(year-1900,month,day);
                //Showing the picked value in the textView
                dateTV.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

                openTimePicker(); // Call openTimePicker() after setting the date
            }
        }, 2024, 05, 12);

        datePickerDialog.show();
    }
    private void openTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                //Showing the picked value in the textView
                timeTV.setText(String.valueOf(hour)+ ":"+String.valueOf(minute));

            }
        }, 10, 00, true);

        timePickerDialog.show();
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
        }else {
            return false;
        }
    }
    private void startContact(){
        Intent intent=new Intent(this,ContactActivity.class);
        startActivity(intent);
    }
    private void startBilliardListActivity(){
        Intent intent=new Intent(this,BilliardListActivity.class);
        startActivity(intent);
    }
    private void startBooking(){
        Intent intent=new Intent(this, BookingActivity.class);
        startActivity(intent);
    }

    public void addTable(View view) {
        szam++;
        displayTV.setText(String.valueOf(szam));
    }

    public void removeTable(View view) {
        if(szam>0){
            szam--;
            displayTV.setText(String.valueOf(szam));
        }
    }

    public void Booking(View view) {
        if(timeTV.getText().toString().length()!=0 && szam!=0){
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Mentés");
            alert.setMessage("Sikeres foglalás");
            PoolItem item=new PoolItem();
            item.setCount(szam);
            item.setName(user.getDisplayName());
            item.setDate(date);
            mItems.add(item)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(LOG_TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        alert.create().show();
                        mNotificationHandler.send("Már alig várunk téged!");
                    })
                    .addOnFailureListener(e -> {
                        Log.w(LOG_TAG, "Error adding document", e);
                        alert.setMessage("Hiba történt a mentés során");
                        alert.create().show();
                    });
        }

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
