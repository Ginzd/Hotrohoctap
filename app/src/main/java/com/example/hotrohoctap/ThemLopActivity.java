package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.model.Lop;

public class ThemLopActivity extends AppCompatActivity {
    Button btnThemLop,btnUpdate;
    EditText edtMaLop, edtTenLop;
    LopDAO lopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);

        btnThemLop = findViewById(R.id.btnThemLop);
        btnUpdate = findViewById(R.id.btnUpdateClass);
        edtMaLop = findViewById(R.id.edt_MaLop);
        edtTenLop = findViewById(R.id.edt_TenLop);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaLop.setText(bundle.getString("MALOP"));
            edtTenLop.setText(bundle.getString("TENLOP"));
        }

    }

    public void ThemLop(View view) {
        lopDAO = new LopDAO(this);
        Lop lop = new Lop(edtMaLop.getText().toString(),edtTenLop.getText().toString());
        if (lopDAO.insertClass(lop)>0){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(ThemLopActivity.this,ListLopUpdateActivity.class);
        startActivity(intent);
    }
    public void huyThemClass(View view) {
        finish();
    }

    public void danhSachLop(View view) {
        Intent intent = new Intent(this,ListLopUpdateActivity.class);
        startActivity(intent);
    }
}