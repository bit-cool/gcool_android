package com.example.lenovo.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.lenovo.myapplication.R;


public class SearchActivity extends AppCompatActivity {

    View back,search,clear;
    EditText editText;
    ListView listView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews(){
        back=findViewById(R.id.search_back);
        searchView=(SearchView)findViewById(R.id.search_text);
//        search=findViewById(R.id.search_iv_search);
//        clear=findViewById(R.id.search_iv_close);
//        editText=(EditText)findViewById(R.id.search_et);
        listView=(ListView)findViewById(R.id.search_listview);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
