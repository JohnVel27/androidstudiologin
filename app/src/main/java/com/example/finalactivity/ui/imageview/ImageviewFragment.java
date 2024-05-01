package com.example.finalactivity.ui.imageview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.finalactivity.R;

public class ImageviewFragment extends Fragment {

    private View rootView;
    private ImageView imageView;
    private int[] imageResources;
    private int currentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_imageview, container, false);

        Button button = rootView.findViewById(R.id.button1);
        imageView = rootView.findViewById(R.id.picture1);

        // Initialize image resources array
        imageResources = new int[]{
                R.drawable.jv2,
                R.drawable.jv3,
                R.drawable.jv4,
                R.drawable.jv5,
                R.drawable.jv6,
                R.drawable.jv7,
                R.drawable.jv8,
                R.drawable.jv9,
                R.drawable.jv10
        };

        // Set initial image
        imageView.setImageResource(imageResources[currentIndex]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

        return rootView;
    }

    public void changeImage() {
        // Increment index
        currentIndex++;

        // Reset index if it exceeds the bounds
        if (currentIndex >= imageResources.length) {
            currentIndex = 0;
        }

        // Set the next image
        imageView.setImageResource(imageResources[currentIndex]);
    }
}
