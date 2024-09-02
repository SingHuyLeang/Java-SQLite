package com.app.localstorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.localstorage.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private final String TABLE_NAME  = "note";
    private final String COL_ID      = "id";
    private final String COL_TITLE   = "title";
    private final String COL_CONTENT = "content";
    private final String COL_DATE    = "date";
    private final String COL_TIME    = "time";

    public NoteDatabase(@Nullable Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITLE + " TEXT NOT NULL," +
                COL_CONTENT + " TEXT NOT NULL," +
                COL_DATE + " TEXT NOT NULL," +
                COL_TIME + " TEXT NOT NULl )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.close();
    }

    // feature
    public boolean insert(NoteModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,note.getTitle());
        values.put(COL_CONTENT,note.getContent());
        values.put(COL_DATE,note.getDate());
        values.put(COL_TIME,note.getTime());

        long result = db.insert(TABLE_NAME,null,values);

        return result != -1;
    }
    public List<NoteModel> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NoteModel> notes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                notes.add(new NoteModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
                cursor.moveToNext();
            }
        } else {
            Log.w("DATABASE", "getAll: empty");
        }
        return notes;
    }
    public boolean update(int id,NoteModel note){
        // ---
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,note.getTitle());
        values.put(COL_CONTENT,note.getContent());
        values.put(COL_DATE,note.getDate());
        values.put(COL_TIME,note.getTime());
        int result = db.update(TABLE_NAME,values,COL_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    public boolean delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,COL_ID + " = ?",new String[]{String.valueOf(id)});
        return result > 0;
    }
}
