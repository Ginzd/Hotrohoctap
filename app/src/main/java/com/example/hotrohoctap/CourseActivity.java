package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }


    public void ThemLopScreen(View view) {
        Intent intent = new Intent(this,ThemLopActivity.class);
        startActivity(intent);
    }

    public void ListLopScreen(View view) {
        Intent intent = new Intent(this,ListLopActivity.class);
        startActivity(intent);
    }

    public void ManageStudent(View view) {
        Intent intent = new Intent(this,AddStudentActivity.class);
        startActivity(intent);
    }
}