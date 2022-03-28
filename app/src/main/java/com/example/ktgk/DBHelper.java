package com.example.ktgk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table USER(email TEXT primary key, name TEXT, age TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists USER");
    }

    public Boolean insertuserdata(String email, String name, String age, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("phone", phone);
        long result = DB.insert("USER", null, contentValues);
        if(result==-1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdata(String email, String name, String age, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("phone", phone);
        Cursor cursor = DB.rawQuery("Select * from USER where email = ?", new String[]{email});
        if(cursor.getCount()>0) {
            long result = DB.update("USER", contentValues, "email=?", new String[]{email});
            if(result==-1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from USER where email = ?", new String[]{email});
        if(cursor.getCount()>0) {


            long result = DB.delete("USER", "email=?", new String[]{email});
            if(result==-1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from USER", null);
        return cursor;
    }
}
