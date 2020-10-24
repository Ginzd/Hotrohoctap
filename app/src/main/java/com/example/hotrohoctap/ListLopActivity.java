package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hotrohoctap.adapter.ClassAdapter;
import com.example.hotrohoctap.adapter.JoinClassAdapter;
import com.example.hotrohoctap.adapter.StudentAdapter;
import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class ListLopActivity extends AppCompatActivity {
    public static List<Lop> list_lop = new ArrayList<>();
    ListView lv;
    JoinClassAdapter adapter;
    LopDAO lopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);

        lv = findViewById(R.id.list_Lop);
        lopDAO = new LopDAO(this);
        list_lop = lopDAO.getAllLop();
        adapter = new JoinClassAdapter(list_lop,this);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list_lop.clear();
        list_lop = lopDAO.getAllLop();
        adapter.setChangeData(list_lop);
    }
}