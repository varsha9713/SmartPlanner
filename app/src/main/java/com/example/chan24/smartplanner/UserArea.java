package com.example.chan24.smartplanner;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class UserArea extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        if (googleServicesAvailable()){
            Toast.makeText(this,"Perfect",Toast.LENGTH_SHORT).show();
        }

        Intent i =getIntent();
        String s = i.getStringExtra("name");
        TextView v =(TextView)findViewById(R.id.textView2);
        v.setText("Welcome "+s);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_user_area);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_profile :
                        Intent i =new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(i);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.my_location :
                        Intent i1 =new Intent(getApplicationContext(),MapsActivity.class);
                        startActivity(i1);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.log_out :
                        Intent i2 =new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i2);
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(getApplicationContext(),"You have successfully logged out!",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS){
            return true;
        }
        else if (api.isUserResolvableError(isAvailable)){
            Dialog dialog =api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
        else{
            Toast.makeText(this,"Cant connect to play services",Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
