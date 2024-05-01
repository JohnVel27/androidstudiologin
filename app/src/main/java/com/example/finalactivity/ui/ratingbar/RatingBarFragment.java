package com.example.finalactivity.ui.ratingbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.finalactivity.R;

public class RatingBarFragment extends Fragment {
    private RatingBar rating_star;
    private TextView text_v;
    private Button button_sbm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_bar, container, false);

        // Initialize views
        rating_star = view.findViewById(R.id.ratingBar1);
        text_v = view.findViewById(R.id.textView1);
        button_sbm = view.findViewById(R.id.button);

        // Set listeners
        listenerForRatingBar();
        onButtonClickListener();

        return view;
    }

    private void listenerForRatingBar() {
        rating_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                text_v.setText(String.valueOf(rating));
            }
        });
    }

    private void onButtonClickListener() {
        button_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), String.valueOf(rating_star.getRating()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
