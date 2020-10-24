package com.example.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hotrohoctap.adapter.CustomAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsActivity extends AppCompatActivity {
    ListView listView;
    NewsCustomAdapter customAdapter;
    ArrayList<DocBao> arrDocBao;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = findViewById(R.id.lvNews);
        arrDocBao = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute("https://vnexpress.net/rss/oto-xe-may.rss");
            }
        });
    }
    class ReadData extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDecription = document.getElementsByTagName("description");
            String hinhanh = "";
            String title = "";
            String link = "";
            for (int i = 0;i<nodeList.getLength();i++){
                String cdata = nodeListDecription.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if (matcher.find()){
                    hinhanh = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title += parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                arrDocBao.add(new DocBao(title,link,hinhanh));
            }
            customAdapter = new NewsCustomAdapter(NewsActivity.this,android.R.layout.simple_list_item_1,arrDocBao);
            listView.setAdapter(customAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    intent = new Intent(NewsActivity.this,WebViewActivity.class);
                    intent.putExtra("link",arrDocBao.get(i).link);
                    startActivity(intent);
                }
            });
            super.onPostExecute(s);
        }
    }
    private static String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}