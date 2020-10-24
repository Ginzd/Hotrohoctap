package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.model.Lop;

public class UpdateLopActivity extends AppCompatActivity {
    EditText edtMaLop1,edtTenLop1;
    LopDAO lopDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lop);
        edtMaLop1 = findViewById(R.id.edt_MaLop1);
        edtTenLop1 = findViewById(R.id.edt_TenLop1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            edtMaLop1.setText(bundle.getString("MALOP"));
            edtTenLop1.setText(bundle.getString("TENLOP"));
        }
    }

    public void clickUpdateLop(View view) {
        lopDAO = new LopDAO(this);
        Lop lop = new Lop(edtMaLop1.getText().toString(),edtTenLop1.getText().toString());
        if (lopDAO.updateClass(lop) ==1){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateLopActivity.this,ListLopUpdateActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
    public void clickHuyUpdateLop(View view) {
        finish();
    }

}