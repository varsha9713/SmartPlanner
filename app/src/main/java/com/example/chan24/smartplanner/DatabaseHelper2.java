package com.example.chan24.smartplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Niharika on 19-04-2017.
 */

public class DatabaseHelper2 extends SQLiteOpenHelper {
    final static public String dbname = "ProfileDetails";
    final static public String tablename ="ExtraDetails";
    final static public String col1 ="Gender";
    final static public String col2 ="Age";
    final static public String col3= "Phone";
    final static public String query ="create table "+tablename+"(Gender varchar(10),Age number(2),Phone number(11))";
    public DatabaseHelper2(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    onCreate(db);
    }

    public boolean insertdata(String gender ,int age ,long phone )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col1,gender);
        cv.put(col2,age);
        cv.put(col3,phone);

        long res=db.insert(tablename,null,cv);
        if(res==-1)
            return false;
        else
            return true;

    }
    Cursor retriving()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor c =db.rawQuery("select * from "+tablename,null);
        return c;

    }
}
