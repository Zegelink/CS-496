package zheng.studybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        Cursor values = db.rawQuery("select * from "+ classTable.Table.table ,null );
        return values;
    }


}

/*
public static final int database_version =1;
    public String create_query = "CREATE TABLE"+ classTable.Table.table+"("+classTable.Table.className+" TEXT ,"+classTable.Table.schoolName+" TEXT );";

    public classDatabase(Context context){
        super(context, classTable.Table.database, null, database_version);
        Log.d("Database operations", "Database created");

    }
    @Override
    public void onCreate(SQLiteDatabase arg0){

        arg0.execSQL(create_query);
        Log.d("Database operations", "Table created");

    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion){

        arg0.execSQL("DROP TABLE IF EXISTS "+ classTable.Table.table);
        onCreate(arg0);
        Log.d("Database Operations", "Table updated");
    }

    public void fillTable(classDatabase data, String classes, String schools){

        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classTable.Table.className, classes);
        values.put(classTable.Table.schoolName, schools);
        long k = db.insert(classTable.Table.table, null, values );
        Log.d("Database operations", "Row inserted");
    }


 */