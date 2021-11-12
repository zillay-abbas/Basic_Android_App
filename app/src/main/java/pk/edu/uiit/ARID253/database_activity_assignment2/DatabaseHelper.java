package pk.edu.uiit.ARID253.database_activity_assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "MyDatabase";
    private static final String table_name = "UserInfo";
    private static final int db_version = 1;
    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, database_name, null, db_version);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + table_name +
                "( user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_name VARCHAR," +
                "user_email VARCHAR," +
                "user_phone VARCHAR," +
                "user_password VARCHAR," +
                "user_country VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public long insertInfo(ArrayList<String> userInfo) {

        //  SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put("user_name", name);
        contentValues.put("user_name", userInfo.get(0));
        contentValues.put("user_email", userInfo.get(1));
        contentValues.put("user_phone", userInfo.get(2));
        contentValues.put("user_password", userInfo.get(3));
        contentValues.put("user_country", userInfo.get(4));

        // contentValues.put("user_phone", phone);
        return sqLiteDatabase.insert(table_name, null, contentValues);

    }

    public Cursor login(String email, String password) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * from " + table_name + " WHERE user_email='" + email + "'" + "AND user_password ='" + password + "'", null);
    }

//    public Cursor getData(String email, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from " + table_name + "where user_email = ? AND user_pass = ?", new String[] {email,password});
//        return res;
//    }


}

