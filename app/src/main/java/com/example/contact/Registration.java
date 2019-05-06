package com.example.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    DatabaseHelper db;
    EditText edt1,edt2,edt3,edt4;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        bt1 = findViewById(R.id.bt_register);
        bt2 = findViewById(R.id.cencel);
        db = new DatabaseHelper(this);

       bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s1 = edt1.getText().toString();
               String s2 = edt2.getText().toString();
               String s3 = edt3.getText().toString();
               String s4 = edt4.getText().toString();

               if((s1=="")||(s2=="")||(s3=="")||(s4==""))
               {Toast.makeText(Registration.this,"Please fill up all fields",Toast.LENGTH_SHORT).show();}
               else
               {
                    if(s2.equals(s3))
                    {
                        Boolean chkphone_number = db.chkphone_number(s4);
                        if(chkphone_number==true)
                        {
                            Boolean insert = db.insert(s1,s2,s4);
                            Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(Registration.this,Login.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"User exist,Please Login",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password and Confirm password should match",Toast.LENGTH_LONG).show();
                    }

               }
           }
       });





        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });


        }
}
