package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;
    View root;

    JSONArray users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding  = ActivityMainBinding.inflate(getLayoutInflater());

        root = binding.getRoot();
        setContentView(root);

        binding.btnLoadUsers.setOnClickListener(v->{
            readJsonFile();
            String gender;
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Female Users");
            if(binding.rbMale.isChecked()) {
                gender = "male";
                alert.setTitle("Male Users");
            }
            else gender = "female";
            String userInfo = getUserInfoByGender(gender);

            alert.setMessage(userInfo);
            alert.setCancelable(false);
            alert.setPositiveButton("ok", null);
            alert.create().show();


        });





    }

    void readJsonFile(){
        try (InputStream inputStream = getAssets().open("users.json")){
            StringBuilder jsonString = new StringBuilder();

            int ch = inputStream.read();

            while (ch != -1){
                jsonString.append((char)ch);
                ch = inputStream.read();
            }


            JSONObject jsonObject = new JSONObject(jsonString.toString());
            users= jsonObject.getJSONArray("users");



        } catch (IOException | JSONException e) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    String getUserInfoByGender(String gender){
        StringBuilder userInfo = new StringBuilder();

        for (int i = 0; i < users.length(); i++) {
            try {
                JSONObject user = users.getJSONObject(i);
                if(user.getString("gender").equals(gender)){
                    JSONObject userName = user.getJSONObject("name");
                    userInfo.append(String.format("%s %s | %s\n", userName.getString("first"),userName.getString("last"), user.getString("city")));
                }
            } catch (JSONException e) {
                return null;
            }

        }
        return  userInfo.toString();
    }
}