package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

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
        ImageButton btn_details = (ImageButton) view.findViewById(R.id.ib_details);
        TextView tv_city= (TextView) view.findViewById(R.id.tv_city);
        tv_fullname.setText(user.getFullname());
        tv_city.setText(user.getCity());
        btn_details.setOnClickListener(v->{
            Intent intent = new Intent(context, UserDetails.class);
            intent.putExtra("user", user);
            context.startActivity(intent);
        });

        return view;
    }
}
