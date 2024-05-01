package com.example.finalactivity.ui.radiobutton;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalactivity.R;

public class RadiobuttonFragment extends Fragment {

    private RadioGroup radioGroup;

    private RadioButton man, jos, em, fern;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radiobutton, container, false);

        // Find radio group and radio buttons
        radioGroup = view.findViewById(R.id.group);
        man = view.findViewById(R.id.manuel);
        jos = view.findViewById(R.id.jose);
        em = view.findViewById(R.id.emilio);
        fern = view.findViewById(R.id.ferdinand);

        // Set listener for radio group change
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was clicked
                if (checkedId == R.id.manuel || checkedId == R.id.jose || checkedId == R.id.ferdinand) {
                    Toast.makeText(getContext(), "You are wrong", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.emilio) {
                    Toast.makeText(getContext(), "You are right", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
