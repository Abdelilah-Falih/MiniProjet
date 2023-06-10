package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);

        binding.btnQuit.setOnClickListener(v-> finish());

        binding.btnLoadUsers.setOnClickListener(v->{
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getFullNamesUsers());
            binding.lvUsersnames.setAdapter(adapter);
        });











    }

    ArrayList<String> getFullNamesUsers (){
        try (InputStream inputStream = getAssets().open("users.json")){
            int c = inputStream.read();
            StringBuilder jsonString = new StringBuilder();
            ArrayList<String> fullNames = new ArrayList<>();

            while (c != -1){
                jsonString.append((char)c);
                c = inputStream.read();
            }

            JSONObject jsonObject = new JSONObject(jsonString.toString());
            JSONArray users = jsonObject.getJSONArray("users");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                JSONObject names = user.getJSONObject("name");
                fullNames.add(String.format("%s %s", names.getString("first"), names.getString("last")));
            }

            return fullNames;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

}