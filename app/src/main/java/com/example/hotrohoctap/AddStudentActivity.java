package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hotrohoctap.adapter.StudentAdapter;
import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Lop;
import com.example.hotrohoctap.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity{
    Spinner spinnerClass;
    LopDAO lopDAO;
    StudentDAO studentDAO;
    EditText edtMaSV,edtTenSV;
    String id_class = "";
    List<Lop> list_class = new ArrayList<>();
    List<Student> list_student = new ArrayList<>();
    ListView lv_Student;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        spinnerClass = findViewById(R.id.my_Spinner);
        edtMaSV = findViewById(R.id.edt_MaSV);
        edtTenSV = findViewById(R.id.edt_TenSV);
        getSpinnerClass();
        studentDAO = new StudentDAO(this);
//        list_student = studentDAO.getAllStudent();
//        studentAdapter = new StudentAdapter(list_student,this);
//        lv_Student.setAdapter(studentAdapter);
//        lv_Student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Student st = studentDAO.getAllStudent().get(i);
//                edtMaSV.setText(st.getMaSinhvien());
//                edtTenSV.setText(st.getTenSinhVien());
//                Toast.makeText(AddStudentActivity.this, "Sua theo ma sinh vien: "+st.getMaSinhvien(), Toast.LENGTH_SHORT).show();
//            }
//        });
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_class = list_class.get(spinnerClass.getSelectedItemPosition()).getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        load data into form:
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null){
//            String idClass1 = bundle.getString("IDCLASS");
//            edtMaSV.setText(bundle.getString("MASV"));
//            edtTenSV.setText(bundle.getString("TENSV"));
//            spinnerClass.setSelection(checkPositionClass(idClass1));
//        }
    }

    private int checkPositionClass(String id_Class1) {
        for (int i = 0; i<list_class.size();i++){
            if (id_Class1.equals(list_class.get(i).getMaLop())){
                return i;
            }
        }
        return 0;
    }
    public void getSpinnerClass(){
        lopDAO = new LopDAO(this);
        list_class = lopDAO.getAllLop();
        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,lopDAO.getAllLop());
        spinnerClass.setAdapter(lopArrayAdapter);
    }


    public void ThemStudent(View view) {
        studentDAO = new StudentDAO(this);
        Lop lop = (Lop) spinnerClass.getSelectedItem();
        String maLop = lop.getMaLop();
        Student st = new Student(String.valueOf(maLop),edtMaSV.getText().toString(),edtTenSV.getText().toString());
        if (studentDAO.insertStudent(st)>0){
            Toast.makeText(this, "Add Student Success", Toast.LENGTH_SHORT).show();
//            onResume();
            Intent intent = new Intent(AddStudentActivity.this,ListAllStudentActivity.class);
            startActivity(intent);
        }
    }
    public void GetStudent(View view) {
        studentDAO = new StudentDAO(this);
        list_student = studentDAO.studenInClass(id_class);
        Lop lop = (Lop) spinnerClass.getSelectedItem();
        studentAdapter = new StudentAdapter(list_student,this);
        lv_Student.setAdapter(studentAdapter);
    }

    public void huyThemStudent(View view) {
        finish();
    }

    public void danhSachSinhVien(View view) {
        Intent intent = new Intent(this,ListAllStudentActivity.class);
        startActivity(intent);
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        list_student.clear();
//        list_student = studentDAO.getAllStudent();
//        studentAdapter.changeDataSet(list_student);
//    }

}