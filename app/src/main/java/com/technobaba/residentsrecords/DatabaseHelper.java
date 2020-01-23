package com.technobaba.residentsrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME="residents.db";
    public static final int DB_VERSION=1;
    public static final String tbl_qry="create table residents_info("+
            "rid integer primary key autoincrement,"+
            "rname text,"+
            "rgender text,"+
            "rage integer,"+
            "rmob text,"+
            "raddress text,"+
            "rdetails text,"+
            "rother text)";
    Context context2;

    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
        System.out.println("Database Created");
        context2=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tbl_qry);
        System.out.println("Table Created");
    }

    public void addInfo(String n,String g,int a,String m,String ad,String d,String o,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("rname",n);
        cv.put("rgender",g);
        cv.put("rage",a);
        cv.put("rmob",m);
        cv.put("raddress",ad);
        cv.put("rdetails",d);
        cv.put("rother",o);

        db.insert("residents_info", null, cv);
        System.out.println("Record successfully added");
    }

    public void editInfo(int id,String n,String g,int a,String m,String ad,String d,String o,SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("rname",n);
        cv.put("rgender",g);
        cv.put("rage",a);
        cv.put("rmob",m);
        cv.put("raddress",ad);
        cv.put("rdetails",d);
        cv.put("rother",o);

        db.update("residents_info",cv,"rid = "+id,null);
    }

    public Cursor viewAll(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM residents_info",null);
        return cursor;
    }

    public Cursor viewNameAndId(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT rid, rname FROM residents_info",null);
        return cursor;
    }

    public void deleteById(int id, SQLiteDatabase db){
        String params = Integer.toString(id);
        db.execSQL("DELETE FROM residents_info WHERE rid = '"+params+"'");
    }

    public void clearAll(SQLiteDatabase db)
    {
        db.execSQL("DELETE FROM residents_info");
        db.execSQL("DELETE FROM sqlite_sequence WHERE name='residents_info'");
        Toast.makeText(context2, "All stored entries form the table has been deleted", Toast.LENGTH_SHORT).show();
    }

    public Cursor findContactActivity(int id, SQLiteDatabase db)
    {
        String[] params = new String[]{Integer.toString(id)}; //id needed to be typecasted to String
        Cursor cursor = db.query("residents_info",null,"rid = ?",params,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}