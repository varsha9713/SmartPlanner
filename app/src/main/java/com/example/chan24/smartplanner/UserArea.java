package com.example.chan24.smartplanner;

import android.app.Dialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserArea extends AppCompatActivity {



    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;

    public String s ="";
    //TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_area);
        if (googleServicesAvailable()){
            //Toast.makeText(this,"Perfect",Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout = (DrawerLayout)findViewById(R.id.activity_user_area);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseAuth fa = FirebaseAuth.getInstance();
        FirebaseUser fu = fa.getCurrentUser();
        DatabaseReference dr =FirebaseDatabase.getInstance().getReference().child("Profile").child(fu.getUid()).child("Username");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s=dataSnapshot.getValue().toString();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                TextView t=(TextView)findViewById(R.id.textView2);
                t.setText("Welcome "+s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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

        Button save =(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox sh =(CheckBox)findViewById(R.id.shopping);
                CheckBox d =(CheckBox)findViewById(R.id.dining);
                CheckBox s =(CheckBox)findViewById(R.id.supermarket);
                TextView dist =(TextView)findViewById(R.id.distance);
                String shopping="no";
                String dining="no";
                String supermarket="no";
                String distance;

                if(sh.isChecked()){
                    shopping="yes";
                }
                if (d.isChecked()){
                    dining="yes";
                }
                if (s.isChecked()){
                    supermarket="yes";
                }

                distance=dist.getText().toString();

                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("shopping",shopping);
                i.putExtra("dining",dining);
                i.putExtra("supermarket",supermarket);
                i.putExtra("distance",distance);
                startActivity(i);

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
