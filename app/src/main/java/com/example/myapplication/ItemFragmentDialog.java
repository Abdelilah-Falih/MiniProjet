package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ItemFragmentDialog extends DialogFragment {
    User user;
    public ItemFragmentDialog(User user){
        this.user = user;
    }

    private static final String MALE_COLOR = "#ADD8E6";
    private static final String FEMALE_COLOR = "#ffb6c1";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_fname,tv_lname, tv_city;
        tv_fname = view.findViewById(R.id.tv_fname_itemdialog);
        tv_lname = view.findViewById(R.id.tv_lname_itemdialog);
        tv_city = view.findViewById(R.id.tv_city_itemdialog);
        tv_fname.setText(user.getFirstname());
        tv_lname.setText(user.getLasttname());
        tv_city.setText(user.getCity());
        if(user.getGender().equals("male")) view.setBackgroundColor(Color.parseColor(MALE_COLOR));
        else view.setBackgroundColor(Color.parseColor(FEMALE_COLOR));
    }
}
