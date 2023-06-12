package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private final Context context;
    private final ArrayList<User> users;

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
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.user_item, null);
        User user = (User) getItem(position);

        TextView tv_fullname = (TextView) view.findViewById(R.id.tv_fullname);
        TextView tv_city= (TextView) view.findViewById(R.id.tv_city);
        tv_fullname.setText(user.getFullname());
        tv_city.setText(user.getCity());
        view.setOnLongClickListener(v->{
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("User Info");
            alert.setMessage(String.format("Full name : %s\ngender: %s\ncity: %s", user.getFullname(), user.getGender(), user.getCity()));
            alert.setPositiveButton("ok", null);
            alert.setCancelable(false);
            alert.create().show();
            return true;
        });

        View itemView = view;
        view.setOnTouchListener(new FlingManager(context){
            @Override
            public boolean swipLeft() {
                if(super.swipLeft()) {
                    itemView.setBackgroundColor(Color.RED);
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Attention !");
                    alert.setMessage("Do you want to remove the user ?");
                    alert.setPositiveButton("Yes", (dialog, which) -> {
                        users.remove(user);
                        notifyDataSetChanged();
                        Toast.makeText(context, "User deleted !", Toast.LENGTH_SHORT).show();
                    }).setNegativeButton("No", ((dialog, which) -> {
                        notifyDataSetChanged();
                    })).setCancelable(false);
                    alert.show();
                }
                return false;
            }


        });


        return view;
    }
}
