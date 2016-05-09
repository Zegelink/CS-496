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

    public Cursor getInformation(SQLiteDatabase db){

        String[] projections = {classTable.Table.className,classTable.Table.schoolName};
        Cursor cursor = db.query(classTable.Table.table, projections, null, null, null, null, null);


         return cursor;

    }


    public Cursor displayTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor values = db.rawQuery("select * from "+ classTable.Table.table ,null );
        return values;
    }
}

