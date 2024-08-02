package com.example.uemeritcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uemeritcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        binding.calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    calculateResult();
                }
            }
        });
    }

    private void calculateResult() {
        double totalResult = ((Double.parseDouble(binding.matriculationObtained.getText().toString()) / Double.parseDouble(binding.matriculationTotal.getText().toString())) * 30) + ((Double.parseDouble(binding.interObtained.getText().toString()) / Double.parseDouble(binding.interTotal.getText().toString())) * 70);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Merit Score").setMessage(String.format("Your merit score is: %.2f%%", totalResult)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private void clearFields() {
        binding.matriculationObtained.setText("");
        binding.matriculationTotal.setText("");
        binding.interObtained.setText("");
        binding.interTotal.setText("");
    }

    private boolean isValid() {
        boolean valid = true;

        if (binding.matriculationObtained.getText().length() < 1) {
            binding.matriculationObtained.setError("please enter obtained marks");
            valid = false;
        }
        if (binding.matriculationTotal.getText().length() < 1) {
            binding.matriculationTotal.setError("please enter total marks");
            valid = false;
        }
        if (binding.interObtained.getText().length() < 1) {
            binding.interObtained.setError("please enter obtained marks");
            valid = false;
        }
        if (binding.interTotal.getText().length() < 1) {
            binding.interTotal.setError("please enter total marks");
            valid = false;
        }
        return valid;
    }
}