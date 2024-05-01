package com.example.finalactivity.ui.home;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.finalactivity.R;

public class HomeFragment extends Fragment {

    private float previousX, previousY;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageView = root.findViewById(R.id.imageView);
        imageView.setOnTouchListener((v, event) -> {
            float x = event.getX();
            float y = event.getY();
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                float dx = Math.abs(x - previousX);
                float dy = Math.abs(y - previousY);

                if (dx > 10 && dy > 10) {
                    getActivity().setRequestedOrientation(getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
            previousX = x;
            previousY = y;
            return true;
        });

        return root;
    }
}
