package com.example.billiard;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BilliardListActivity extends AppCompatActivity {
    private static final String LOG_TAG = BilliardListActivity.class.getName();
    private FirebaseUser user;
    private FrameLayout redCircle;
    private TextView countTextView;
    private int cartItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooldal);
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
            Log.d(LOG_TAG, "Contact-ra kattintottunk");
            return true;
        } else if (id == R.id.opening_hours) {
            Log.d(LOG_TAG, "opening_hours-ra kattintottunk");
            return true;
        } else if (id == R.id.cart) {
            Log.d(LOG_TAG, "cart-ra kattintottunk");
            return true;
        } else if (id == R.id.booking) {
            Log.d(LOG_TAG, "booking-ra kattintottunk");
            return true;
        } else if (id == R.id.search_bar) {
            Log.d(LOG_TAG, "search_bar-ra kattintottunk");
            return true;
        } else if (id == R.id.setting_button) {
            Log.d(LOG_TAG, "setting_button-ra kattintottunk");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
  /*  @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.cart);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = (TextView) rootView.findViewById(R.id.view_alert_count_textview);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }*/


}