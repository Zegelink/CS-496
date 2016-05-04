package zheng.studybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Zheng on 5/2/2016.
 */
public class classDatabase extends SQLiteOpenHelper {

    public static final int database_version =1;
    public String create_query = "CREATE TABLE"+ classeTable.Table.table+"("+classeTable.Table.classname+" TEXT ,"+classeTable.Table.schoolname+" Text );";

    public classDatabase(Context context){
        super(context, classeTable.Table.database, null, database_version);
        Log.d("Database operations", "Database created");

    }
    @Override
    public void onCreate(SQLiteDatabase arg0){

        arg0.execSQL(create_query);
        Log.d("Database operations", "Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){


    }

    public void fillTable(classDatabase data, String classes, String schools){

        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classeTable.Table.classname, classes);
        values.put(classeTable.Table.schoolname, schools);
        long k = db.insert(classeTable.Table.table, null, values );
        Log.d("Database operations", "Row inserted");
    }
}

