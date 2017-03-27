
package com.example.chan24.smartplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

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
    }
}
