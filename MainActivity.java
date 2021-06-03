package com.example.fetchrewards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import org.json.*;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> listId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray=jsonObject.getJSONArray("hiring");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject userData=jsonArray.getJSONObject(i);
                name.add(userData.getString("name"));
                id.add(userData.getString("id"));
                listId.add(userData.getString("listId"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HelperAdapter helperAdapter=new HelperAdapter(name, id, listId, MainActivity.this);
        recyclerView.setAdapter(helperAdapter);
    }

    private String JsonDataFromAsset() {

        String json=null;
        try {
            InputStream inputStream = getAssets().open("hiring.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String (bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}