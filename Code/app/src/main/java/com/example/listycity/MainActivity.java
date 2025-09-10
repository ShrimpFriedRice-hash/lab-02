package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText city;
    Button confirm;
    Button add;
    Button delete;
    String city_to_add;
    //String city_to_delete;
    LinearLayout text_button;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_button = findViewById(R.id.linearLayout1);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        city = findViewById(R.id.city_add);
        city.getText();
        confirm = findViewById(R.id.confirm_button);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_button.setVisibility(View.VISIBLE);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        city_to_add = city.getText().toString();
                        dataList.add(city_to_add);
                        cityAdapter.notifyDataSetChanged();
                        text_button.setVisibility(View.GONE);
                        city.getText().clear();
                    }
                });
            }
        });
        delete = findViewById(R.id.delete);
        //I got the code to initialize setOnItemClickListener from
        //https://stackoverflow.com/questions/4709870/setonitemclicklistener-on-custom-listview
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object city_to_delete = cityList.getItemAtPosition(position);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.remove(city_to_delete.toString());
                        cityAdapter.notifyDataSetChanged();
                    }
                });

            }
        });


    }
}