package com.example.finalactivity.ui.newactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalactivity.R;
import com.example.finalactivity.SecondActivityJohnVel;


public class NewactivityFragment extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newactivity, container, false);

    }
    public void onStart(){
       super.onStart();
        Button btn = (Button) context.findViewById(R.id.activity2nd);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SecondActivityJohnVel.class);
                        startActivity(intent);
                    }
                }
        );
    }
}