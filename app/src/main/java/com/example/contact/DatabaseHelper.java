package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text, password text ,phone_number text primary key)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String usename , String password, String phone_number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",usename);
        contentValues.put("password",password);
        contentValues.put("phone_number",phone_number);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1)return false;
        else return true;
    }
    public boolean chkphone_number(String phone_number)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("Select * from user where phone_number=?",new String[]{phone_number});
        if(cursor.getCount()>0)return false;
        else return true;

    }
    public boolean match(String username,String password)
    {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = (Cursor) db.rawQuery("Select * from user where username=? and password=?",new String[]{username,password});
                    if(cursor.getCount()==1)return true;
                    else
                        return false;

    }
    public boolean chk_username(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("Select *from user where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public List<UserData> getAllContacts() {
        List<UserData> contactList = new ArrayList<UserData>();

        String selectQuery = "SELECT  * FROM user";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                UserData contact = new UserData();
                contact.setUsername(cursor.getString(0));
                contact.setPhone_number(cursor.getString(2));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
    public void deleteContact(UserData contact) {
        SQLiteDatabase db = this.getWritableDatabase();
       // db.delete("user","where username=?",new String[]{String.valueOf(contact.getUsername())});
       db.delete("user","username=?",new String[]{String.valueOf(contact.getUsername())});

        db.close();
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

    }


}
