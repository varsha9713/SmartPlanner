package com.example.chan24.smartplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Niharika on 17-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final static public String dbname = "UserDetails";
    final static public String tablename ="RegisterDetails";
    final static public String col1 ="Username";
    final static public String col2 ="Password";
    final static public String col3= "Mail";
    //final static public String col4= "Age";
    //final static public String col5="Sex";
    //final static public String col6="Phone";
    public static final String query="create table "+tablename+" (Username varchar(50) primary key ,Password varchar(50),Mail varchar(60))";

    public DatabaseHelper(Context context) {
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

    public boolean insertdata(String username ,String password,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(col1,username);
        cv.put(col2,password);
        cv.put(col3,mail);

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
