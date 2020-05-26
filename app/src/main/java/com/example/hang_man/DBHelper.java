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

    private static final String TABLE_NAME2 = "HighScore";
    private static final String ID2 = "_id";
    private static final String NAME = "Name";
    private static final String SCORE = "Score";

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

    public static String getID2() { return ID2; }

    public static String getNAME() { return NAME; }

    public static String getSCORE() { return SCORE; }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD + " TEXT NOT NULL, " + HINT + " TEXT NOT NULL" + ")";
        sqLiteDatabase.execSQL(queryTable);

        String queryTable2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + "( " + ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + SCORE + " INTEGER NOT NULL" + ")";
        sqLiteDatabase.execSQL(queryTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public void openDB() {
        QM = getWritableDatabase();
    }

    public void closeDB() {
        if (QM!=null && QM.isOpen()) {
            QM.close();
        }
    }

    public long Insert(String word,String hint) {
        ContentValues values = new ContentValues();
        values.put(WORD,word);
        values.put(HINT,hint);
        return QM.insert(TABLE_NAME,null,values);
    }

    public long Insert2(String name,int score) {
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(SCORE,score);
        return QM.insert(TABLE_NAME2,null,values);
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

    public Cursor getAllScores() {
        String query = "SELECT * FROM " + TABLE_NAME2 + " ORDER BY " + SCORE + " DESC ";
        return QM.rawQuery(query,null);
    }
    
}
