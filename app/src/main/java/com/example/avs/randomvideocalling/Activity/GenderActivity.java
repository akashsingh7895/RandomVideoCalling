package com.example.avs.randomvideocalling.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.avs.randomvideocalling.MainActivity;
import com.example.avs.randomvideocalling.R;
import com.example.avs.randomvideocalling.databinding.ActivityGenderBinding;

public class GenderActivity extends AppCompatActivity {

    ActivityGenderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.linearLayout.setEnabled(false);
        binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg_dark));
        binding.linearLayout.setEnabled(binding.maleButton.isChecked()|| binding.femaleButton.isChecked());
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.maleButton.isChecked()|| binding.femaleButton.isChecked()){
                    binding.linearLayout.setEnabled(true);
                    binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg));

                    binding.linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GenderActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }else {
                    binding.linearLayout.setBackground(getResources().getDrawable(R.drawable.white_rounded_bg_dark));
                    binding.linearLayout.setEnabled(false);
                }
            }
        });


    }
}