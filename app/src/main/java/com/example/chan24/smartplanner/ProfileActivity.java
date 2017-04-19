package com.example.chan24.smartplanner;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {
    public String str="";
    DatabaseHelper2 db=new DatabaseHelper2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i =getIntent();
        str=i.getStringExtra("Name");

        TextView uname = (TextView)findViewById(R.id.userName);
        uname.setText(str);

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
            //Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();




        int a= Integer.parseInt(age.getText().toString());
        int p = Integer.parseInt(phone.getText().toString());
        boolean res =db.insertdata(selectedRadioButton.getText().toString(),a,p);
        if(res) {
            //Toast.makeText(this, "Inserted ", Toast.LENGTH_SHORT).show();
            Intent i2 =new Intent(getApplicationContext(),UserArea.class);
            //i2.putExtra("Name",str);
            startActivity(i2);

        }


    }
}
