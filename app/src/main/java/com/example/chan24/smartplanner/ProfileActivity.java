package com.example.chan24.smartplanner;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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


        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Username");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=dataSnapshot.getValue().toString();
                TextView u=(TextView)findViewById(R.id.userName);
                u.setText(s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Age");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s= dataSnapshot.getValue().toString();
                TextView a=(TextView)findViewById(R.id.age);
                a.setText(s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Gender");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s= dataSnapshot.getValue().toString();
                RadioButton rb =(RadioButton)findViewById(R.id.female);
                RadioButton rb1 =(RadioButton)findViewById(R.id.male);

                if (rb.getText().toString().contentEquals(s) ){
                    rb.setChecked(true);
                }

                else if (rb1.getText().toString().contentEquals(s)){
                    rb1.setChecked(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Phone");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s=dataSnapshot.getValue().toString();
                TextView p=(TextView)findViewById(R.id.phone);
                p.setText(s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});
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
        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Gender");
        databaseReference2.setValue(g);
        DatabaseReference databaseReference3=FirebaseDatabase.getInstance().getReference().child("Profile").child(user.getUid()).child("Phone");
        databaseReference3.setValue(p);

        startActivity(new Intent(getApplicationContext(),UserArea.class));
    }
}
