package com.example.hotrohoctap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hotrohoctap.database.DBHelper;
import com.example.hotrohoctap.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class LopDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public static final String TABLE_NAME = "Lop";
    public static final String SQL_CLASS = "create table Lop(maLop text, tenLop text)";

    public LopDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertClass(Lop lop){
        ContentValues values = new ContentValues();
        values.put("maLop",lop.getMaLop());
        values.put("tenLop",lop.getTenLop());
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        return 1;
    }
    public int updateClass(Lop lop){
        ContentValues values = new ContentValues();
        values.put("maLop",lop.getMaLop());
        values.put("tenLop",lop.getTenLop());
        int result = db.update(TABLE_NAME,values,"maLop=?",new String[]{lop.getMaLop()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public List<Lop> getAllLop(){
        List<Lop> list_TheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Lop lop = new Lop();
            lop.setMaLop(c.getString(0));
            lop.setTenLop(c.getString(1));
            list_TheLoai.add(lop);
            c.moveToNext();
        }
        c.close();
        return list_TheLoai;
    }
    public int deleteClass(String maLop){
        int result = db.delete(TABLE_NAME,"maLop=?",new String[]{maLop});
        if (result ==0){
            return -1;
        }else {
            return 1;
        }
    }
}
