package com.example.hotrohoctap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.dao.StudentDAO;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NANE = "htht";
    public static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NANE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LopDAO.SQL_CLASS);
        sqLiteDatabase.execSQL(StudentDAO.SQL_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LopDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentDAO.TABLE_NAME);
    }
}
