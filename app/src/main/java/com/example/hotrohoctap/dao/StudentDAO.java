package com.example.hotrohoctap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;

import com.example.hotrohoctap.database.DBHelper;
import com.example.hotrohoctap.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public static final String TABLE_NAME = "Student";
    public static final String SQL_STUDENT = "Create Table Student (maLop text, maSV text, tenSV text);";

    public StudentDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertStudent(Student st){
        ContentValues values = new ContentValues();
        values.put("maLop",st.getMaLop());
        values.put("maSV",st.getMaSinhvien());
        values.put("tenSV",st.getTenSinhVien());
        if (db.insert(TABLE_NAME,null,values)==-1){
            return -1;
        }
        return 1;
    }
    public List<Student> getAllStudent(){
        List<Student> list_student = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,
                null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Student st = new Student();
            st.setMaLop(c.getString(0));
            st.setMaSinhvien(c.getString(1));
            st.setTenSinhVien(c.getString(2));

            list_student.add(st);
            c.moveToNext();
        }
        c.close();
        return list_student;
    }
    public List<Student> studenInClass(String maLop){
        List<Student> list_st = new ArrayList<>();
        String sSQL =  "SELECT maLop,maSV,tenSV from Student WHERE maLop = '"+maLop+"' ";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Student s = new Student();
            s.setMaLop(c.getString(0));
            s.setMaSinhvien(c.getString(1));
            s.setTenSinhVien(c.getString(2));
            list_st.add(s);
            c.moveToNext();
        }
        c.close();
        return list_st;
    }
    public int updateStudent(Student st){
        ContentValues values = new ContentValues();
        values.put("maLop",st.getMaLop());
        values.put("maSV",st.getMaSinhvien());
        values.put("tenSV",st.getTenSinhVien());
        if (db.update(TABLE_NAME,values,"maSV=?",new String[]{st.getMaSinhvien()})==0){
            return -1;
        }
        return 1;
    }
    public int deleteStudent(String maSV){
        if (db.delete(TABLE_NAME,"maSV=?",new String[]{maSV})==0){
            return -1;
        }
        return 1;
    }
    public boolean checkPrimaryKey(String key){
        String[] columns = {"maSinhVien"};
        String selection = "maSinhVien=?";
        String[] selectionArgs = {key};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
