package com.lavazza.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    public static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "lavazza";

    // Login table name
    public static final String TABLE_USER = "product";

    // Login Table Columns names
    public static final String _ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_UOM = "uom";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_CID = "cus_id";
//    public static final String KEY_PASSWORD = "password";
//    public static final String KEY_DEVICE_NAME = "device_name";
//    public static final String KEY_DEVICE_NUMBER = "device_number";
//    public static final String KEY_DEVICE_ID = "device_id";
//    public static final String KEY_RELATION = "relationship";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + _ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
//                + KEY_DATE + " TEXT," + KEY_TIME + " TEXT,"
                //this is image as imteger
//                + KEY_QUANTITY + " TEXT," + KEY_IMAGE + " INTEGER," + KEY_UOM + " TEXT" + ")";
                //
        + KEY_QUANTITY + " TEXT," + KEY_IMAGE + " TEXT," + KEY_UOM + " TEXT,"
                + KEY_CID + " TEXT" + ")";

//                + KEY_DEVICE_NAME + " TEXT," + KEY_DEVICE_NUMBER + " TEXT," + KEY_DEVICE_ID + " TEXT,"
//                + KEY_RELATION + " TEXT" + ")";

        //+ KEY_ADDRESS + " TEXT," + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

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

    /**
     * Storing user details in database
     * */
//    public void addUser(String name, String organisation, String email, String phone, String address, String created_at) {
//    public void addUser(String name, String quantity, String uom, int image ) {
        public void addUser(String name, String quantity, String uom, String image, String cus_id ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
//        values.put(KEY_DATE, date);
//        values.put(KEY_TIME, time); // Email
        values.put(KEY_QUANTITY, quantity);
        values.put(KEY_UOM, uom); // Email
        values.put(KEY_IMAGE, image);
            values.put(KEY_CID, cus_id);
//        values.put(KEY_DEVICE_NAME, dname);
//        values.put(KEY_DEVICE_NUMBER, dnumber);
//        values.put(KEY_DEVICE_ID, did);
//        values.put(KEY_RELATION, relation);
//        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public ArrayList<String> getName(String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_CID + " = "+ cus_id , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_NAME));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

//    public ArrayList<Integer> getImage(String name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        //  SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER , new String[] {});
////        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
//        ArrayList<Integer> array = new ArrayList<Integer>();
//        while (cur.moveToNext()) {
//            int uname = cur.getInt(cur.getColumnIndex(KEY_IMAGE));
////            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
////            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
//            array.add(uname);
////            array.add(uname1);
////            array.add(uname2);
//
//
//        }
//
//        cur.close();
//        db.close();
//        return array;
//    }

    public ArrayList<String> getDate(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_DATE));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getTime(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER , new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_TIME));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getQuantity(String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_CID + " = "+ cus_id, new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_QUANTITY));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getUom(String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_CID + " = "+ cus_id, new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_UOM));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

    public ArrayList<String> getImage(String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_CID + " = "+ cus_id, new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {
            String uname = cur.getString(cur.getColumnIndex(KEY_IMAGE));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }


    //    public Cursor queryName() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] cols = { _ID, KEY_NAME, KEY_PHONE,
//                KEY_EMAIL, KEY_VEHICLENO, KEY_MODEL, KEY_COMMENT, KEY_RATE, KEY_RATECOMMENT };
////                , KEY_DATE, openHelper_ob.URL };
////                , openHelper_ob.EMAIL, openHelper_ob.VEHICLENO, openHelper_ob.MODEL, openHelper_ob.COMMENT };
////        opnToWrite();
//
//        Cursor c = db.query(TABLE_USER, cols, null,
//                null, null, null, null);
//
//        return c;
//
//    }
//
//    public Cursor queryAll(int nameId) {
//        String[] cols = { _ID, KEY_NAME,
//                KEY_PHONE, KEY_EMAIL, KEY_VEHICLENO, KEY_MODEL, KEY_COMMENT , KEY_RATE, KEY_RATECOMMENT};
////                , openHelper_ob.EMAIL, openHelper_ob.VEHICLENO,openHelper_ob.MODEL, openHelper_ob.COMMENT };
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c = db.query( TABLE_USER, cols,
//                _ID + "=" + nameId, null, null, null, null);
//
//        return c;
//
//    }
//
//    public long updateldetail(int rowId, String fname, String lname, String email, String vehicle, String model, String comment, String rate, String ratecomment) {
    public long updateldetail(String name, String date, String time, String quantity, String uom) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_TIME, time);
        contentValues.put(KEY_QUANTITY, quantity);
        contentValues.put(KEY_UOM, uom);

        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.update( TABLE_USER, contentValues,
                _ID + "=" + name, null);
        db.close();
        return val;
    }

    public void updateUom(String name, String quantity, String uom, String cus_id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_QUANTITY, quantity);
//        contentValues.put(KEY_UOM, uom);
//        contentValues.put(KEY_CID, cus_id);

//        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.update(TABLE_USER, contentValues, KEY_NAME +"='"+ name +"'"+" AND "+ KEY_CID +"='"+ cus_id +"'", null);
        db.close();
        Log.d(TAG, "Update the values in sqlite");
//        return val;
    }

    public String getUpdateQuantity(String name, String quantity, String uom, String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " where " + KEY_NAME + " = '"+ name +"'"+" AND "+ KEY_UOM +"='"+ uom +"'"+" AND "+ KEY_CID +"='"+ cus_id +"'", new String[] {});
//        Cursor cur = db.rawQuery("SELECT " + "* "+"from " + TABLE_USER + " WHERE "+ KEY_NAME +" = "+name , new String[] {});
//        ArrayList<String> array = new ArrayList<String>();
        String array = null;
        while (cur.moveToNext()) {
//            String uname = cur.getString(cur.getColumnIndex(KEY_UOM));
             array = cur.getString(cur.getColumnIndex(KEY_QUANTITY));
//            String uname1=cur.getString(cur.getColumnIndex("CONTACT2"));
//            String uname2=cur.getString(cur.getColumnIndex("CONTACT3"));
//            array.add(uname);
//            array.add(uname1);
//            array.add(uname2);


        }

        cur.close();
        db.close();
        return array;
    }

    public void updateQuantity(String name, String quantity, String uom, String cus_id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_QUANTITY, quantity);
//        contentValues.put(KEY_UOM, uom);
//        contentValues.put(KEY_CID, cus_id);

//        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.update(TABLE_USER, contentValues, KEY_NAME + " = '"+ name +"'"+" AND "+ KEY_UOM +"='"+ uom +"'"+" AND "+ KEY_CID +"='"+ cus_id +"'", null);
        db.close();
        Log.d(TAG, "Update the values in sqlite");
//        return val;
    }

    public int deletOneRecord(int rowId) {
        // TODO Auto-generated method stub
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(TABLE_USER,
                _ID + "=" + rowId, null);

//        db.delete(TABLE_USER, _ID + " = ?",
//                new String[] {String.valueOf(rowId)});

//        db.delete(TABLE_USER, _ID+"=? and name=?",new String[]{"1","jack"});
        db.close();
        Log.d(TAG, "deleted the value from sqlite");
        return val;
    }

//    public void deleteRule(int rowId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_USER, _ID + " = ?",
//                new int[] { rowId });
//        db.close();
//
//    }

    public void delete(int rowId) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
//        database.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + _ID + "= '" + rowId + "'");
//        database.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + _ID + " = " + rowId);
        database.delete(TABLE_USER, _ID + " = ?",
                new String[] {String.valueOf(rowId)});
//        database.rawQuery("DELETE FROM " + TABLE_USER + " WHERE " + _ID + " = "+ rowId, new String[] {});
        //Close the database
        database.close();
        Log.d(TAG, "deleted the value from sqlite");
    }

    public void delete(String name, String quantity, String uom, String cus_id ) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
//        database.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + _ID + "= '" + rowId + "'");
//        database.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + _ID + " = " + rowId);
//        database.delete(TABLE_USER, name + " = ?",
//                new String[] {String.valueOf(rowId)});


        database.delete(TABLE_USER, KEY_NAME + " = ?" + " AND "+ KEY_QUANTITY + " = ?" + " AND "+ KEY_UOM + " = ?" + " AND "+ KEY_CID + " = ?" ,
                new String[] {name, quantity, uom, cus_id});

//        database.rawQuery("DELETE FROM " + TABLE_USER + " WHERE " + _ID + " = "+ rowId, new String[] {});
        //Close the database
        database.close();
        Log.d(TAG, "deleted the value from sqlite");
    }

    public boolean hasObject(String name, String quantity, String uom, String cus_id ) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_NAME + " = ?" + " AND "+ KEY_QUANTITY + " = ?" + " AND "+ KEY_UOM + " = ?" + " AND "+ KEY_CID + " = ?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {name, quantity, uom, cus_id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }


    public boolean hasObject1(String name, String uom, String cus_id ) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_NAME + " = ?" + " AND "+ KEY_UOM + " = ?" + " AND "+ KEY_CID + " = ?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {name, uom, cus_id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }

    //
//    /**
//     * Getting user data from database
//     * */
//    public HashMap<String, String> getUserDetails() {
//        HashMap<String, String> user = new HashMap<String, String>();
//        String selectQuery = "SELECT  * FROM " + TABLE_USER;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // Move to first row
//        cursor.moveToFirst();
//        if (cursor.getCount() > 0) {
//            user.put("name", cursor.getString(1));
//            user.put("phone", cursor.getString(2));
//            user.put("email", cursor.getString(3));
//            user.put("vehicleno", cursor.getString(4));
//            user.put("model", cursor.getString(5));
//            user.put("comment", cursor.getString(6));
//            user.put("rate", cursor.getString(7));
//            user.put("ratecomment", cursor.getString(8));
//        }
//        cursor.close();
//        db.close();
//        // return user
//        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
//
//        return user;
//    }
//
//    /**
//     * Re crate database Delete all tables and create them again
//     * */
    public void deleteUsers(String cus_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, KEY_CID + "=" + cus_id, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public int cartcount(String cus_id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_CID + " = ?" ;

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {cus_id});

//        int hasObject;
        int count = 0;
        if(cursor.moveToFirst()){
//            hasObject = true;

            //region if you had multiple records to check for, use this region.


            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return count;
    }

    public int getCartCount(String cus_id) {
        String countQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + KEY_CID + " = ?" ;;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, new String[] {cus_id});
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}