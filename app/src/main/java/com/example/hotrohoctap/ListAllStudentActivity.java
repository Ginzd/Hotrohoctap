package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hotrohoctap.adapter.StudentAdapter;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListAllStudentActivity extends AppCompatActivity {
    StudentDAO studentDAO;
    ListView lv_All_Student;
    StudentAdapter studentAdapter;
    List<Student> listStudent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_student);

        lv_All_Student = findViewById(R.id.lvAllStudent);
        studentDAO = new StudentDAO(this);
        listStudent = studentDAO.getAllStudent();
        studentAdapter = new StudentAdapter(listStudent,this);
        lv_All_Student.setAdapter(studentAdapter);
    }
}