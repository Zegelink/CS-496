package zheng.studybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheng on 5/2/2016.
 */
public class classDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Class.db";
    public static final String TABLE_NAME = "ClassInfo";
    public static final String TABLE_NAME2 = "TimeStudied";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Class";
    public static final String COL_3 = "School";
    public static final String COL_4 = "Time";


    public classDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Time Text)");
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Class TEXT,School TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }
    public boolean insertData(String className, String school){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, className);
        contentValues.put(COL_3, school);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insertTime(Long time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, time.toString());
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor displayTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor values = db.rawQuery("select * from "+ TABLE_NAME,null );
        return values;
    }

    public long getTotalTime(){
        long totalTime = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME2,null );

        if (cursor.moveToFirst()) {
            do {

                totalTime+=(Long.parseLong(cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return totalTime;
    }

    public List<Classes> getAllClass() {
        List<Classes> classList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                Classes classes = new Classes();
                classes.setId(Integer.parseInt(cursor.getString(0)));
                classes.setClassTaking(cursor.getString(1));
                classes.setSchool(cursor.getString(2));

                // Adding friend to list
                classList.add(classes);
            } while (cursor.moveToNext());
        }

        return classList;
    }

    public int updateFriend(Classes friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, friend.getClassTaking());
        values.put(COL_3, friend.getSchool());

        // updating row
        return db.update(TABLE_NAME, values, COL_1 + " = ?", new String[]{String.valueOf(friend.getId())});
    }

    // Deleting a record in database table
    public void deleteFriend(Classes friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(friend.getId())});
        db.close();
    }


}

