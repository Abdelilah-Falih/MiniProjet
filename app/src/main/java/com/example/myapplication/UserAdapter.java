package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> users;

    private final long MIN_CLICK_DURATION = 1000;
    private final long MAX_CLICK_DURATION = 2000;
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
        TextView tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_fullname.setText(user.getFullname());
        tv_city.setText(user.getCity());

        view.setOnTouchListener(new View.OnTouchListener() {
            long click_down_time;
            long click_up_time;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    click_down_time = System.currentTimeMillis();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    click_up_time = System.currentTimeMillis();
                    if ((click_up_time - click_down_time) < MAX_CLICK_DURATION && (click_up_time - click_down_time) > MIN_CLICK_DURATION) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setTitle("User Info");
                        alert.setMessage(String.format("Full name : %s\ngender: %s\ncity: %s", user.getFullname(), user.getGender(), user.getCity()));
                        alert.setPositiveButton("ok", null);
                        alert.setCancelable(false);
                        alert.create().show();
                    }
                }
                return true;
            }
        });

        return view;
    }
}
