package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private TextView convertedValue;
    private Button convertButton;

    private double[] lengthValues = {1.0, 1.0 / 3.28084, 1.0 / 39.3701};
    private String[] lengthUnits = {"Meters", "Feet", "Inches"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        convertedValue = findViewById(R.id.convertedValue);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lengthUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertButton.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                convertButton.setEnabled(false);
            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertButton.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                convertButton.setEnabled(false);
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double value = Double.parseDouble(inputValue.getText().toString());
                    int fromIndex = spinnerFrom.getSelectedItemPosition();
                    int toIndex = spinnerTo.getSelectedItemPosition();

                    double result = value * lengthValues[fromIndex] / lengthValues[toIndex];

                    convertedValue.setText(String.format("%.2f %s = %.2f %s", value, lengthUnits[fromIndex], result, lengthUnits[toIndex]));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}