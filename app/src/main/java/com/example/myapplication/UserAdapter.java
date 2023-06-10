package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> users;

    LayoutInflater inflater;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.user_item, null);
        User user = (User) getItem(position);

        TextView tv_fullname = (TextView) view.findViewById(R.id.tv_fullname);
        TextView tv_position = (TextView) view.findViewById(R.id.tv_position);
        TextView tv_city= (TextView) view.findViewById(R.id.tv_city);
        tv_fullname.setText(user.getFullname());
        tv_position.setText(String.format("#%d", position+1));
        tv_city.setText(user.getCity());

        if(user.getGender().equals("male")){
            view.setBackgroundColor(Color.parseColor("#27B5F3"));
        }
        else view.setBackgroundColor(Color.parseColor("#F69EED"));

        return view;
    }
}
