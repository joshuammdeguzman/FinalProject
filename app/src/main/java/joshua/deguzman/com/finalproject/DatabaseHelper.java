package joshua.deguzman.com.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Accounts.db";
    public static final String TABLE_NAME = "account_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SNUM";
    public static final String COL_3 = "LNAME";
    public static final String COL_4 = "FNAME";
    public static final String COL_5 = "CNUM";
    public static final String COL_6 = "EMAIL";
    public static final String COL_7 = "PASS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 +" INTEGER " + " , " + COL_3 + " TEXT " + " , " + COL_4 + " TEXT" + " , " + COL_5 +" INTEGER " + " , " + COL_6 + " TEXT " + " , " + COL_7 + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String item2, String item3, String item4, String item5, String item6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item);
        contentValues.put(COL_3, item2);
        contentValues.put(COL_4, item3);
        contentValues.put(COL_5, item4);
        contentValues.put(COL_6, item5);
        contentValues.put(COL_7, item6);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean getData1(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select * from  " + TABLE_NAME + " where " +
                COL_2 + " = " + "'"+user+"'" + " and " + COL_7 + " = " + "'"+pass+"'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
