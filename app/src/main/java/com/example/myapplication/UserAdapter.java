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
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> users;

    private final long CLICK_DURATION = 200;
    LayoutInflater inflater;
    FragmentManager fragmentManager;

    boolean isVisible ;

    public UserAdapter(Context context, ArrayList<User> users, FragmentManager fragmentManager) {
        this.context = context;
        this.users = users;
        this.fragmentManager = fragmentManager;
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
        TextView tv_city= (TextView) view.findViewById(R.id.tv_city);
        ImageView iv_checked_item= (ImageView) view.findViewById(R.id.iv_checked_item);
        Button btn_details= (Button) view.findViewById(R.id.btnDetails);
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

        btn_details.setOnClickListener(v->{
            ItemFragmentDialog itemFragmentDialog = new ItemFragmentDialog(user);
            itemFragmentDialog.show(fragmentManager, null);
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            long first_click_time = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    long click_time = System.currentTimeMillis();
                    if((click_time - first_click_time <= CLICK_DURATION)){
                        isVisible = iv_checked_item.getVisibility() == View.VISIBLE;
                        if(isVisible){
                            iv_checked_item.setVisibility(View.INVISIBLE);
                        }else iv_checked_item.setVisibility(View.VISIBLE);
                    }else {
                        first_click_time = click_time;
                    }
                }
                return false;
            }
        });

        return view;
    }
}
