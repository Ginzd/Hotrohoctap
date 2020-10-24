package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hotrohoctap.adapter.StudentAdapter;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Lop;
import com.example.hotrohoctap.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInClassActivity extends AppCompatActivity {
    ListView lvInClass;
    StudentDAO studentDAO;
    StudentAdapter studentAdapter;
    List<Student> list_student = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_class);

        lvInClass = findViewById(R.id.lvInClass);
        studentDAO = new StudentDAO(this);
        studentDAO = new StudentDAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String idLop = bundle.getString("MALOP");

        list_student = studentDAO.studenInClass(idLop);
        studentAdapter = new StudentAdapter(list_student, this);
        lvInClass.setAdapter(studentAdapter);
    }
}