package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.io.IOException;
import java.io.InputStream;

public class ItemFragmentDialog extends DialogFragment {
    User user;
    public ItemFragmentDialog(User user){
        this.user = user;
    }

    private static final String MALE_COLOR = "#ADD8E6";
    private static final String FEMALE_COLOR = "#ffb6c1";

    private ImageView userImage;
    ProgressBar progressBarLoadingImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView tv_fname = view.findViewById(R.id.tv_fname_itemdialog);
        TextView tv_lname = view.findViewById(R.id.tv_lname_itemdialog);
        TextView tv_city = view.findViewById(R.id.tv_city_itemdialog);
        progressBarLoadingImage = view.findViewById(R.id.progress_loading_image);
        userImage = view.findViewById(R.id.iv_robot_itemdialog);
        tv_fname.setText(user.getFirstname());
        tv_lname.setText(user.getLasttname());
        tv_city.setText(user.getCity());
        if(user.getGender().equals("male")) view.setBackgroundColor(Color.parseColor(MALE_COLOR));
        else view.setBackgroundColor(Color.parseColor(FEMALE_COLOR));
        progressBarLoadingImage.setVisibility(View.VISIBLE);
        new DoInBackground().execute();
    }

    class DoInBackground extends AsyncTask<Void, Bitmap, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try(InputStream inputStream = requireActivity().getAssets().open(user.getImage_path())) {
                Bitmap image_bitmap = BitmapFactory.decodeStream(inputStream);
                Thread.sleep(2000);
                publishProgress(image_bitmap);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            userImage.setImageBitmap(values[0]);

        }

        @Override
        protected void onPostExecute(Void unused) {
            progressBarLoadingImage.setVisibility(View.GONE);
        }
    }
}
