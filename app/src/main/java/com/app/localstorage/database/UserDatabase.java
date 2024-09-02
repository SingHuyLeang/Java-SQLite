package com.app.localstorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.localstorage.model.UserModel;

public class UserDatabase extends SQLiteOpenHelper {

    private final String TABLE_NAME = "users";
    private final String COL_ID     = "id";
    private final String COL_EMAIL  = "email";
    private final String COL_PASS   = "password";

    public UserDatabase(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // write query create table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COL_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EMAIL + " TEXT, "
                + COL_PASS  + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    // your feature here
    public boolean signUp(UserModel user){
        long result = -1;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_EMAIL,user.getEmail());
            values.put(COL_PASS,user.getPassword());
            result = db.insert(TABLE_NAME,null,values);
        } catch (SQLiteException e) {
            Log.e("SQLiteException", "Sign Up : " + e.getMessage() );
        }
        return result != -1;
    }

    public boolean signIn(UserModel user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COL_ID},
                COL_EMAIL + " = ? AND " + COL_PASS + " = ?",
                new String[]{user.getEmail(), user.getPassword()},
                null,null,null
        );
        // SELECT id FROM user WHERE email = ?  password = ?
        return cursor != null && cursor.getCount() > 0;
    }
}
