package com.example.myapp2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private Spinner spinnerPhoneType;
    private RadioGroup radioGroupBrand;
    private Button buttonOk;
    private OnDataSendListener listener;

    public interface OnDataSendListener {
        void onDataSend(String phoneType, String brand);
    }

    public InputFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDataSendListener) {
            listener = (OnDataSendListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDataSendListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        spinnerPhoneType = view.findViewById(R.id.spinnerPhoneType);
        radioGroupBrand = view.findViewById(R.id.radioGroupBrand);
        buttonOk = view.findViewById(R.id.buttonOk);

        String[] phoneTypes = {
                "Смартфон",
                "Кнопковий телефон",
                "Складаний телефон"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                phoneTypes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhoneType.setAdapter(adapter);

        buttonOk.setOnClickListener(v -> {
            int selectedId = radioGroupBrand.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(requireContext(), "Оберіть фірму", Toast.LENGTH_SHORT).show();
                return;
            }

            String phoneType = spinnerPhoneType.getSelectedItem().toString();
            RadioButton selectedRadioButton = view.findViewById(selectedId);
            String brand = selectedRadioButton.getText().toString();

            if (listener != null) {
                listener.onDataSend(phoneType, brand);
            }
        });

        return view;
    }

    public void clearForm() {
        if (spinnerPhoneType != null) {
            spinnerPhoneType.setSelection(0);
        }
        if (radioGroupBrand != null) {
            radioGroupBrand.clearCheck();
        }
    }
}