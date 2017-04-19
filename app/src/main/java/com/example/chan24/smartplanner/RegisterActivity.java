package com.example.chan24.smartplanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db= new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //final EditText user = (EditText)findViewById(R.id.user);
        //final EditText pass = (EditText)findViewById(R.id.pass);
        //final EditText mail = (EditText)findViewById(R.id.mail);
        //final Button signup =(Button)findViewById(R.id.signup);

    }
    public void signup(View view)
    {
        EditText user = (EditText)findViewById(R.id.user);
        EditText pass = (EditText)findViewById(R.id.pass);
        EditText mail = (EditText)findViewById(R.id.mail);

        boolean res =db.insertdata(user.getText().toString(),pass.getText().toString(),mail.getText().toString());
        if(res) {
            //Toast.makeText(this, "Inserted ", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }


    }

}
