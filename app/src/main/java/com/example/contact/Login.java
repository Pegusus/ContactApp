package com.example.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper db;
    Button bt2,bt1;
    EditText edt1,edt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        db = new DatabaseHelper(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edt1.getText().toString();
                String s2 = edt2.getText().toString();

                if((s1=="")||(s2==""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                  boolean match = db.match(s1,s2);
                  if(match==true)
                  {
                      Intent intent = new Intent(Login.this, ContactsView.class);
                      startActivity(intent);
                  }
                  else
                  {
                     boolean chk_username = db.chk_username(s1);
                     if(chk_username==true){
                         Toast.makeText(getApplicationContext(),"Wrong passwrod",Toast.LENGTH_SHORT).show();
                     edt2.setText("");}
                     else
                     { Toast.makeText(getApplicationContext(),"User doesnot exist, Please Register",Toast.LENGTH_LONG).show();
                     edt1.setText("");edt2.setText("");}
                  }

                }

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });




    }
}
