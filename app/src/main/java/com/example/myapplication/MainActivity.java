package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);

        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                if(e1.getX() - e2.getX() >=160) finish();
                return super.onFling(e1, e2, velocityX, velocityY);
            }


        });


        binding.tvSwip.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });



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