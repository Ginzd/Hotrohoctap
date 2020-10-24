package com.example.hotrohoctap.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotrohoctap.ListAllStudentActivity;
import com.example.hotrohoctap.R;
import com.example.hotrohoctap.UpdateSinhVienActivity;
import com.example.hotrohoctap.dao.StudentDAO;
import com.example.hotrohoctap.model.Lop;
import com.example.hotrohoctap.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    List<Student> list_student;
    public Activity context;
    public LayoutInflater inflater;
    StudentDAO studentDAO;
    int m_index;

    public StudentAdapter(List<Student> list_student, Activity context) {
        super();
        this.list_student = list_student;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.studentDAO = new StudentDAO(context);
    }

    @Override
    public int getCount() {
        return list_student.size();
    }

    @Override
    public Object getItem(int i) {
        return list_student.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolderStudent{
        ImageView imgEdit;
        ImageView imgDelete;
        TextView txtIdClass;
        TextView txtMaSV;
        TextView txtTenSV;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolderStudent holder;
        if (view == null){
            holder = new ViewHolderStudent();
            view = inflater.inflate(R.layout.item_student,null);
            holder.imgEdit = view.findViewById(R.id.iv_edit_sv);
            holder.imgDelete = view.findViewById(R.id.iv_delete_sv);
            holder.txtIdClass = view.findViewById(R.id.tv_idclass);
            holder.txtMaSV = view.findViewById(R.id.tv_masv);
            holder.txtTenSV = view.findViewById(R.id.tv_tensv);
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateSinhVienActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MALOP",list_student.get(i).getMaLop());
                    bundle.putString("MASV",list_student.get(i).getMaSinhvien());
                    bundle.putString("TENSV",list_student.get(i).getTenSinhVien());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_index = i;
                    try{
                        showDialog("Bạn có chắc muốn xóa học sinh này ?");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            view.setTag(holder);
        }
        holder = (ViewHolderStudent)view.getTag();
        Student entry_student = list_student.get(i);
        holder.txtIdClass.setText("Ma Lop:"+entry_student.getMaLop());
        holder.txtMaSV.setText("Ma SV:"+entry_student.getMaSinhvien());
        holder.txtTenSV.setText("Ten SV:"+entry_student.getTenSinhVien());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<Student> list_student){
        this.list_student = list_student;
        notifyDataSetChanged();
    }
    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("" + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

                Student student = list_student.get(m_index);
                studentDAO.deleteStudent(list_student.get(m_index).getMaSinhvien());
                list_student.remove(student);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
