
package com.example.chan24.smartplanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText name = (EditText)findViewById(R.id.email);
        final EditText password = (EditText)findViewById(R.id.password);
        final Button login = (Button)findViewById(R.id.login);
        final TextView register = (TextView)findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(regintent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n=name.getText().toString();
                String p =password.getText().toString();

                progressDialog = new ProgressDialog(LoginActivity.this);


                if (n.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (p.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Authenticating...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                firebaseAuth.getInstance().signInWithEmailAndPassword(n, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserArea.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Enter correct credentials or register and try again!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });
    }
}
