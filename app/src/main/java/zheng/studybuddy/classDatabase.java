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
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Class";
    public static final String COL_3 = "School";

    public classDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Class TEXT,School TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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
    public Cursor displayTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor values = db.rawQuery("select * from "+ TABLE_NAME,null );
        return values;
    }

    public List<String> getAllClass() {
        List<String> classList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String classN;
        String schoolN;
        String finalN;
        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                classN = cursor.getString(1)+"\n";
                schoolN = cursor.getString(2)+"\n\n";
                finalN = classN+schoolN;

                // Adding friend to list
                classList.add(finalN);
            } while (cursor.moveToNext());
        }

        return classList;
    }


}

