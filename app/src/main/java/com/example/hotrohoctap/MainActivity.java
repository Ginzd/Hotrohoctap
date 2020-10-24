package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.hotrohoctap.adapter.CustomAdapter;
import com.example.hotrohoctap.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent intent;
    GridView gridView;
    ArrayList<Item> list_itemGrid = new ArrayList<>();
    CustomAdapter customView_Adapter;
    Bitmap student_icon,map_icon,news_icon,fb_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student_icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.online_class);
        map_icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.map);
        news_icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.news);
        fb_icon = BitmapFactory.decodeResource(this.getResources(),R.drawable.social);

        list_itemGrid.add(new Item(student_icon,"Student"));
        list_itemGrid.add(new Item(map_icon,"Maps"));
        list_itemGrid.add(new Item(news_icon,"News"));
        list_itemGrid.add(new Item(fb_icon,"Social"));

        gridView =findViewById(R.id.my_gridview);
        customView_Adapter = new CustomAdapter(this,R.layout.row_img,list_itemGrid);
        gridView.setAdapter(customView_Adapter);
        gridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                intent = new Intent(MainActivity.this,CourseActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(MainActivity.this,SocialActivity.class);
                startActivity(intent);
                break;
        }
    }
}