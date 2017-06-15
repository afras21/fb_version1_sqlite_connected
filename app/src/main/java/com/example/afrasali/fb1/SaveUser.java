package com.example.afrasali.fb1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Afras Ali on 6/5/2017.
 */

public class SaveUser extends SQLiteOpenHelper {

    public SaveUser(Context context) {
        super(context, "db", null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table tbl_user (vchar_name text,vchar_pwd text,pic BLOB,vchar_email text,date text,month text,year text,post1 BLOB)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbl_user");
        onCreate(db);
    }
    public int LoginCheck(String luser){
        SQLiteDatabase db=getReadableDatabase();
        String[] args={luser};
        Cursor c=db.query("tbl_user",null,"vchar_name=?",args,null,null,null);

        return c.getCount();
    }
    public int PCheck(String luser,String lpwd){
        SQLiteDatabase db=getReadableDatabase();
        String[] args={luser,lpwd};
        Cursor c=db.query("tbl_user",null,"vchar_name=? and vchar_pwd=?",args,null,null,null);
        return c.getCount();
    }
}
