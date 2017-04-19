
package com.example.chan24.smartplanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db =new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText name = (EditText)findViewById(R.id.name);
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
        Button b =(Button)findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //StringBuffer str =new StringBuffer();
                int flag=1;
                String n=name.getText().toString();
                String p =password.getText().toString();
                    Cursor res=db.retriving();
                    while(res.moveToNext())
                    {
                        if(n.equals(res.getString(0)))
                        {
                            if(p.equals(res.getString(1)))
                            {
                                flag =0;
                                Intent i =new Intent(getApplicationContext(),UserArea.class);
                                i.putExtra("name",n);
                                startActivity(i);
                            }
                        }

                    }
                    if(flag==1)
                    {
                        Toast.makeText(getApplicationContext(),"Enter proper details",Toast.LENGTH_SHORT).show();
                    }



            }
        });
    }
}
