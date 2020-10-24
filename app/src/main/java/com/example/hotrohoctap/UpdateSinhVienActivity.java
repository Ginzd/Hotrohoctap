package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hotrohoctap.adapter.StudentAdapter;
import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Lop;
import com.example.hotrohoctap.model.Student;

import java.util.ArrayList;
import java.util.List;

public class UpdateSinhVienActivity extends AppCompatActivity {
    Spinner spinnerClass1;
    EditText edtMaSV1,edtTenSV1;
    LopDAO lopDAO;
    StudentDAO studentDAO;
    String id_class = "";
    List<Lop> list_class = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);


        edtMaSV1 = findViewById(R.id.edt_MaSV1);
        edtTenSV1 = findViewById(R.id.edt_TenSV1);
        spinnerClass1 = findViewById(R.id.my_Spinner1);
        studentDAO = new StudentDAO(this);
        lopDAO = new LopDAO(this);
        list_class = lopDAO.getAllLop();
        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,lopDAO.getAllLop());
        spinnerClass1.setAdapter(lopArrayAdapter);
        spinnerClass1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id_class = list_class.get(spinnerClass1.getSelectedItemPosition()).getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String idClass1 = bundle.getString("MALOP");
            edtMaSV1.setText(bundle.getString("MASV"));
            edtTenSV1.setText(bundle.getString("TENSV"));
            spinnerClass1.setSelection(checkPositionClass(idClass1));
        }
    }
    private int checkPositionClass(String id_Class1) {
        for (int i = 0; i<list_class.size();i++){
            if (id_Class1.equals(list_class.get(i).getMaLop())){
                return i;
            }
        }
        return 0;
    }

    public void UpdateStudent(View view) {
        Lop lop = (Lop) spinnerClass1.getSelectedItem();
        String maLop = lop.getMaLop();
        studentDAO = new StudentDAO(this);
        Student st = new Student(String.valueOf(maLop),edtMaSV1.getText().toString(),edtTenSV1.getText().toString());
        if (studentDAO.updateStudent(st)>0){
//            onResume();
            Intent intent = new Intent(this,ListAllStudentActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Update Student Success", Toast.LENGTH_SHORT).show();
        }
    }


    public void huyCapNhatStudent(View view) {
        finish();
    }

}