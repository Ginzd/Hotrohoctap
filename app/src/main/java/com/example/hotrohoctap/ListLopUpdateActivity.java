package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hotrohoctap.adapter.ClassAdapter;
import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class ListLopUpdateActivity extends AppCompatActivity {
    public static List<Lop> list_lop = new ArrayList<>();
    ListView lvLopStart;
    ClassAdapter adapter;
    LopDAO lopDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop_update);
        lvLopStart = findViewById(R.id.lvLopStart);
        lopDAO = new LopDAO(this);
        list_lop = lopDAO.getAllLop();
        adapter = new ClassAdapter(list_lop,this);
        lvLopStart.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list_lop.clear();
        list_lop = lopDAO.getAllLop();
        adapter.setChangeData(list_lop);
    }
}