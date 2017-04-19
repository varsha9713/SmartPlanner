package com.example.chan24.smartplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button cancel =(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),UserArea.class);
                startActivity(i);
            }
        });
    }

    public void save(View view)
    {
        EditText age = (EditText)findViewById(R.id.age);
        RadioGroup gender =(RadioGroup)findViewById(R.id.gender);
        EditText phone  = (EditText)findViewById(R.id.phone);
        RadioButton selectedRadioButton;

        int selectedId = gender.getCheckedRadioButtonId();
        selectedRadioButton = (RadioButton)findViewById(selectedId);
        String a= age.getText().toString();
        String p=phone.getText().toString();
        String g= selectedRadioButton.getText().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();

        databaseReference.child("Profile").child(user.getUid()).child("Age").setValue(a);
        databaseReference.child("Profile").child(user.getUid()).child("Gender").setValue(g);
        databaseReference.child("Profile").child(user.getUid()).child("Phone").setValue(p);
    }
}
