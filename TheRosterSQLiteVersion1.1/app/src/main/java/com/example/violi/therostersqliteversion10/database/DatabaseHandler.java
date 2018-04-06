package com.example.violi.therostersqliteversion10.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class DatabaseHandler extends SQLiteOpenHelper {

    // declare variables
    public static final int database_verion = 1;
    public static final String DATABASE_NAME = "Roster.db";
    public static final String TABLE_NAME = "Roster_table";
    public static final String COL_ID = "ID"; //PK
    public static final String COL_FIRSTNAME = "FIRSTNAME";
    public static final String COL_LASTNAME = "LASTNAME";
    public static final String COL_POSITION = "POSITION";
    public static final String COL_BIRTHDAY = "BIRTHDAY";
    public static final String COL_TEAM = "TEAM";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME , null, database_verion);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //write the query here
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "  (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "FIRSTNAME TEXT," +
                "LASTNAME TEXT," +
                "POSITION TEXT," +
                "BIRTHDAY TEXT," +
                "TEAM TEXT)");
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //INSERT DATA TO DATABASE HERE
    public boolean insertData(String firstName, String lastName, String position,
                              String birthday, String team)
    {
        //To insert data
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FIRSTNAME, firstName);
        values.put(COL_LASTNAME, lastName);
        values.put(COL_BIRTHDAY, birthday);
        values.put(COL_TEAM, team);
        values.put(COL_POSITION, position);

        long result = db.insert(TABLE_NAME,null ,values);
        if (result == -1){
            return  false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(){
        //To view all data
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;

    }
    //INSERT DATA TO DATABASE HERE
    public boolean updateData(String id, String firstName, String lastName, String position,
                              String birthday, String team)
    {
        //To update data
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,id);
        values.put(COL_FIRSTNAME, firstName);
        values.put(COL_LASTNAME, lastName);
        values.put(COL_BIRTHDAY, birthday);
        values.put(COL_TEAM, team);
        values.put(COL_POSITION, position);
        //query to update into sqlite database
        db.update(TABLE_NAME, values,"id = ?", new String[] { id });
        return  true;
    }

    //To delete data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        //ID = ? means the ? will be replaced by the value from 3nd argument
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }


}