package com.example.hotrohoctap.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotrohoctap.R;
import com.example.hotrohoctap.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    Context context;
    int resourceID;
    ArrayList<Item> data = new ArrayList<>();

    public CustomAdapter(@NonNull Context context,int resourceID, ArrayList<Item> data) {
        super(context, resourceID,data);
        this.context = context;
        this.resourceID = resourceID;
        this.data = data;
    }
    static class RecordHolder{
        TextView txtTitle;
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resourceID,parent,false);
            holder = new RecordHolder();
            holder.txtTitle = row.findViewById(R.id.itemText);
            holder.imageView = row.findViewById(R.id.itemImage);
            row.setTag(holder);
        }else {
            holder = (RecordHolder)row.getTag();
        }
        Item item = data.get(position);
        holder.txtTitle.setText(item.getTitle());
        holder.imageView.setImageBitmap(item.getImage());
        return row;
    }
}
