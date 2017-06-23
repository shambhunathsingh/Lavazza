package com.lavazza.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SensitiveTech on 06-May-17.
 */

public class SQLitePreview extends SQLiteOpenHelper {

    public static final String TAG = SQLitePreview.class.getSimpleName();

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "lavazza1";

    // Login table name
    public static final String TABLE_USER = "preview";

    public static final String _ID = "_id";
    public static final String KEY_SERIAL_NO = "serial_no";
    public static final String KEY_PRODUCT = "product";
    public static final String KEY_OPENING = "opening";
    public static final String KEY_CLOSING = "closing";
    public static final String KEY_CONSUME = "consumption";
    public static final String KEY_CID = "cus_id";

    public SQLitePreview(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String PREVIEW_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + _ID + " INTEGER PRIMARY KEY," + KEY_SERIAL_NO + " TEXT,"
                + KEY_PRODUCT + " TEXT," + KEY_OPENING + " TEXT," + KEY_CLOSING + " TEXT,"
                + KEY_CONSUME + " TEXT," + KEY_CID + " TEXT" + ")";

        db.execSQL(PREVIEW_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    public void addUser(String serial_no, String product, String opening, String closing, String consume, String cid ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERIAL_NO, serial_no); // Name
        values.put(KEY_PRODUCT, product);
        values.put(KEY_OPENING, opening); // Email
        values.put(KEY_CLOSING, closing);
        values.put(KEY_CONSUME, consume);
        values.put(KEY_CID, cid);


        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public ArrayList<String> getName(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
            array.add(uname);
            array.add(uname1);
            array.add(uname2);
            array.add(uname3);
            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }


    public ArrayList<String> getSerial(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
//            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
//            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
//            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
//            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);
//            array.add(uname3);
//            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getProduct(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
//            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
//            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
//            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
//            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
//            array.add(uname);
            array.add(uname1);
//            array.add(uname2);
//            array.add(uname3);
//            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getOpen(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
//            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
//            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
//            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
//            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
//            array.add(uname);
//            array.add(uname1);
            array.add(uname2);
//            array.add(uname3);
//            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getClose(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
//            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
//            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
//            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
//            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
//            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);
            array.add(uname3);
//            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getConsume(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_SERIAL_NO + " = "+ serial_no , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
//            String uname = cur.getString(cur.getColumnIndex(KEY_SERIAL_NO));
//            String uname1=cur.getString(cur.getColumnIndex(KEY_PRODUCT));
//            String uname2=cur.getString(cur.getColumnIndex(KEY_OPENING));
//            String uname3=cur.getString(cur.getColumnIndex(KEY_CLOSING));
            String uname4=cur.getString(cur.getColumnIndex(KEY_CONSUME));
//            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);
//            array.add(uname3);
            array.add(uname4);


        }

        cur.close();
        db.close();
        return array;
    }

    public Cursor getSitesByClientname(String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args={serial_no};

        return db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE serial_no=?", args);
    }

    public void deleteUsers(String serial_no, String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, KEY_SERIAL_NO + "='" + serial_no +"'"+" AND "+ KEY_CID + " = '"+ cus_id +"'", null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }


    public void deleteUsers1(String cus_id, String serial_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER,  KEY_SERIAL_NO + " = ?" + " AND "+ KEY_CID + " = ?",  new String[] {serial_no, cus_id});
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
