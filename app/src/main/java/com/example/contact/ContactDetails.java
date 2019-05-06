package com.example.contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {

    TextView tv1,tv2;
    Button call_btn,jason_list;
    ImageView profile_image;
    String username,phone_number;
    public static final int CAMERA_REQUEST=9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__details);

     tv1 = findViewById(R.id.username);
     tv2 = findViewById(R.id.phone_number);
     call_btn = findViewById(R.id.Call);
     profile_image = findViewById(R.id.profile_image);
     jason_list = findViewById(R.id.JASON_list);

     username = getIntent().getStringExtra("username");
     phone_number = getIntent().getStringExtra("phone_number");

     tv1.setText(username);
     tv2.setText(phone_number);

     call_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent callintent = new Intent(Intent.ACTION_CALL);
             callintent.setData(Uri.parse("tel:"+phone_number));
             startActivity(callintent);
         }
     });
     jason_list.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(), JsonList.class);
         }
     });


     profile_image.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(intent,CAMERA_REQUEST);
         }


     });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        profile_image.setImageBitmap(bitmap);
    }
}
