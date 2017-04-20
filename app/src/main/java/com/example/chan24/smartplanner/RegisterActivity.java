package com.example.chan24.smartplanner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String m;
    private String p;
    private String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference= FirebaseDatabase.getInstance().getReference();


        final EditText user = (EditText)findViewById(R.id.user);
        final EditText pass = (EditText)findViewById(R.id.pass);
        final EditText mail = (EditText)findViewById(R.id.mail);
        final Button signup =(Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = user.getText().toString();
                p = pass.getText().toString();
                m = mail.getText().toString();

                if (u.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (p.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (m.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(m, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //init cond
                            databaseReference = FirebaseDatabase.getInstance().getReference();
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            databaseReference.child("Profile").child(user.getUid()).child("Username").setValue(u);
                            databaseReference.child("Profile").child(user.getUid()).child("Password").setValue(p);
                            databaseReference.child("Profile").child(user.getUid()).child("Email").setValue(m);
                            databaseReference.child("Profile").child(user.getUid()).child("Age").setValue("");
                            databaseReference.child("Profile").child(user.getUid()).child("Gender").setValue("");
                            databaseReference.child("Profile").child(user.getUid()).child("Phone").setValue("");




                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
