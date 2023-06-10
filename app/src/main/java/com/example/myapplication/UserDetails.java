package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityUserDetailsBinding;

public class UserDetails extends AppCompatActivity {

    User user;

    ActivityUserDetailsBinding binding;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        setContentView(root);


        user =(User) getIntent().getSerializableExtra("user");

        binding.tvFnameDetails.setText(user.getFirstname());
        binding.tvLnameDetails.setText(user.getLasttname());
        binding.tvCityDetails.setText(user.getCity());

        if(user.getGender().equals("female")){
            binding.ivGenderDetails.setImageResource(R.drawable.ic_female);
        }else {
            binding.ivGenderDetails.setImageResource(R.drawable.ic_male);
        }

    }
}