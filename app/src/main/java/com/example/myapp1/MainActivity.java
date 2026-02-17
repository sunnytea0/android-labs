package com.example.myapp1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spPhoneType;
    RadioGroup rgBrand;
    Button btnOk;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spPhoneType = findViewById(R.id.spPhoneType);
        rgBrand = findViewById(R.id.rgBrand);
        btnOk = findViewById(R.id.btnOk);
        tvResult = findViewById(R.id.tvResult);

        // Дані для згорнутого списку (типи телефонів)
        String[] phoneTypes = {"Оберіть тип...", "Смартфон", "Кнопковий", "Розкладушка"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                phoneTypes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPhoneType.setAdapter(adapter);

        btnOk.setOnClickListener(v -> {
            String type = spPhoneType.getSelectedItem().toString();
            int brandId = rgBrand.getCheckedRadioButtonId();

            // Перевірка: чи вибрано тип (не "Оберіть тип...") і бренд
            if (spPhoneType.getSelectedItemPosition() == 0 || brandId == -1) {
                Toast.makeText(this, "Завершіть введення всіх даних", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton rbBrand = findViewById(brandId);
            String brand = rbBrand.getText().toString();

            tvResult.setText("Вибрано:\nТип телефону: " + type + "\nФірма: " + brand);
        });
    }
}
