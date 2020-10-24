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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotrohoctap.ListLopUpdateActivity;
import com.example.hotrohoctap.R;
import com.example.hotrohoctap.UpdateLopActivity;
import com.example.hotrohoctap.dao.LopDAO;
import com.example.hotrohoctap.model.Lop;

import java.util.List;

public class ClassAdapter extends BaseAdapter {
    List<Lop> list_lop;
    public Activity context;
    public LayoutInflater inflater;
    LopDAO lopDAO;
    int m_index;

    public ClassAdapter(List<Lop> list_lop, Activity context) {
        this.list_lop = list_lop;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lopDAO = new LopDAO(context);
    }

    @Override
    public int getCount() {
        return list_lop.size();
    }

    @Override
    public Object getItem(int i) {
        return list_lop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolderClass {
        ImageView imgViewSua;
        TextView tvMaLop;
        TextView tvTenLop;
        ImageView imgViewXoa;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolderClass holder;
        if (view == null) {
            holder = new ViewHolderClass();
            view = inflater.inflate(R.layout.item_lop, null);
            holder.imgViewSua = view.findViewById(R.id.imgViewEditClass);
            holder.imgViewXoa = view.findViewById(R.id.imgViewDeleteClass);
            holder.tvMaLop = view.findViewById(R.id.tvMaLop);
            holder.tvTenLop = view.findViewById(R.id.tvTenLop);
            holder.imgViewSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //                Intent intent = new Intent(ListLopActivity.this,ThemLopActivity.class);
                    Intent intent = new Intent(context, UpdateLopActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MALOP", list_lop.get(i).getMaLop());
                    bundle.putString("TENLOP", list_lop.get(i).getTenLop());
                    intent.putExtras(bundle);
//
//                startActivity(intent);

                    context.startActivity(intent);
                }
            });
            holder.imgViewXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_index = i;
                    try {
                        showDialog("Bạn có chắc muốn xóa lớp này ?");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            view.setTag(holder);
        }
        holder = (ViewHolderClass) view.getTag();
        Lop entry_lop = list_lop.get(i);
        holder.tvMaLop.setText(entry_lop.getMaLop());
        holder.tvTenLop.setText(entry_lop.getTenLop());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setChangeData(List<Lop> list_lop) {
        this.list_lop = list_lop;
        notifyDataSetChanged();
    }

    public void showDialog(final String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("" + text);

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Lop lop = list_lop.get(m_index);
                lopDAO.deleteClass(list_lop.get(m_index).getMaLop());
                list_lop.remove(lop);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
