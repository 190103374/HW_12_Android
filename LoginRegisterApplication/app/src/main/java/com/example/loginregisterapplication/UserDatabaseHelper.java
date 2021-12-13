package com.example.loginregisterapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user";
    private static final int DB_VERSION = 2;
//    private String email;
//    private String first_name;
//    private String last_name;
//    private String password;
//    private String reg_date;

    UserDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0 , DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE USER (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "EMAIL TEXT," +
                    "FIRST_NAME TEXT," +
                    "LAST_NAME TEXT," +
                    "PASSWORD TEXT," +
                    "REG_DATE TEXT );");
            insertUser(db, "nurzh", "Nurzhakhan", "Galymova", "nurzh", "12/11/2021");
        }
    }

    private static void insertUser(SQLiteDatabase db, String email, String first_name, String last_name, String password, String reg_date) {
        ContentValues userValues = new ContentValues();
        userValues.put("EMAIL", email);
        userValues.put("FIRST_NAME", first_name);
        userValues.put("LAST_NAME", last_name);
        userValues.put("PASSWORD", password);
        userValues.put("REG_DATE", reg_date);
        db.insert("USER", null, userValues);
    }
}
