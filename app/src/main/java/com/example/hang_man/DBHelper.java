package com.example.hang_man;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "qm.db";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "QuestMan";
    private static final String ID = "_id";
    private static final String WORD = "word";
    private static final String HINT = "hint";

    private SQLiteDatabase QM;

    public DBHelper(Context context){
        super(context,DBName,null,VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getWORD() {
        return WORD;
    }

    public static String getHINT() {
        return HINT;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY, " + WORD + " TEXT NOT NULL, " + HINT + " TEXT NOT NULL" + ")";
        sqLiteDatabase.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void openDB() {
        QM = getWritableDatabase();
    }

    public void closeDB() {
        if (QM!=null && QM.isOpen()) {
            QM.close();
        }
    }

    public long Insert(int id,String word,String hint) {
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(WORD,word);
        values.put(HINT,hint);
        return QM.insert(TABLE_NAME,null,values);
    }

    public long Update(int id,String word, String hint) {
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(WORD,word);
        values.put(HINT,hint);
        String where = ID + " = " + id;
        return QM.update(TABLE_NAME,values,where,null);
    }

    public long Delete(int id) {
        String where = ID + " = " + id;
        return QM.delete(TABLE_NAME,where,null);
    }

    public Cursor getAllRecord() {
        String query = "SELECT * FROM " + TABLE_NAME;
        return QM.rawQuery(query,null);
    }
}
