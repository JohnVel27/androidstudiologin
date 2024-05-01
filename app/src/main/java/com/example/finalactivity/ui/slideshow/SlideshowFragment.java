package com.example.finalactivity.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalactivity.R;
import com.example.finalactivity.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private Button buttonSbm;
    private DigitalClock digital;
    private AnalogClock analog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        buttonSbm = root.findViewById(R.id.button23);
        digital = root.findViewById(R.id.digital_clock);
        analog = root.findViewById(R.id.analog_clock);

        // Initially hide the digital clock
        digital.setVisibility(View.GONE);

        // Set OnClickListener for the button
        buttonSbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the visibility of digital clock
                if (digital.getVisibility() == View.GONE) {
                    // If digital clock is not visible, make it visible and hide analog clock
                    digital.setVisibility(View.VISIBLE);
                    analog.setVisibility(View.GONE);
                } else {
                    // If digital clock is visible, make it invisible and show analog clock
                    digital.setVisibility(View.GONE);
                    analog.setVisibility(View.VISIBLE);
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
