package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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

    ProgressDialog dialog ;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);

        dialog = new ProgressDialog(this);
        dialog.setMessage("loading users...");
        dialog.setCancelable(false);

        binding.tvSwip.setOnTouchListener(new FlingManager(this) {
            @Override
            public boolean swipLeft() {
                if(super.swipLeft()){
                    finish();
                }
                return false;
            }
        });





        binding.btnLoadUsers.setOnClickListener(v->{
            new InBackground().execute();
            dialog.show();
        });











    }

    /*ArrayList<User> getUsers(){
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
    }*/

    class InBackground extends AsyncTask<Void, ArrayList<User>, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
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
                    Thread.sleep(200);

                }


                publishProgress(users_obj);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return  null;
            } catch (InterruptedException e) {
                Toast.makeText(MainActivity.this, "error sleep", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(ArrayList<User>... values) {
            UserAdapter adapter = new UserAdapter(MainActivity.this, values[0]);
            binding.lvUsersnames.setAdapter(adapter);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            dialog.dismiss();
        }
    }

}