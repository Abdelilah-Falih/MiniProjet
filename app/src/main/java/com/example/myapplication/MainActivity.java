package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
            UserAdapter adapter = new UserAdapter(this, getUsers());
            binding.lvUsersnames.setAdapter(adapter);
        });











    }

    ArrayList<User> getUsers(){
        try (InputStream inputStream = getAssets().open("users.json")){
            int c = inputStream.read();
            StringBuilder jsonString = new StringBuilder();
            ArrayList<User> users_obj = new ArrayList<>();

            while (c != -1){
                jsonString.append((char)c);
                c = inputStream.read();
            }

            JSONObject jsonObject = new JSONObject(jsonString.toString());
            JSONArray users = jsonObject.getJSONArray("users");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                JSONObject names = user.getJSONObject("name");

                users_obj.add(new User(
                        names.getString("first"),
                        names.getString("last"),
                        user.getString("gender"),
                        user.getString("city")
                ));

            }

            return users_obj;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

}