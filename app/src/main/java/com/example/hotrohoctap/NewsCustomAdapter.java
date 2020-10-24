package com.example.hotrohoctap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotrohoctap.model.Lop;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsCustomAdapter extends ArrayAdapter<DocBao> {
    public NewsCustomAdapter(@NonNull Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_news,null);
        }
        DocBao db = getItem(position);
        if (db != null ){
            TextView txtTitle = (TextView) view.findViewById(R.id.textViewTitle);
            txtTitle.setText(db.title);

            ImageView imageView = view.findViewById(R.id.imgView_News);
            Picasso.with(getContext()).load(db.image).into(imageView);
        }
        return view;
    }
}
