package com.example.myapp2;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private TextView textViewResult;
    private Button buttonCancel;
    private OnCancelListener listener;

    public interface OnCancelListener {
        void onCancel();
    }

    public ResultFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCancelListener) {
            listener = (OnCancelListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCancelListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        textViewResult = view.findViewById(R.id.textViewResult);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCancel();
            }
        });

        return view;
    }

    public void updateResult(String phoneType, String brand) {
        if (textViewResult != null) {
            String result = "Обраний тип телефону: " + phoneType + "\n"
                    + "Обрана фірма: " + brand;
            textViewResult.setText(result);
        }
    }

    public void clearResult() {
        if (textViewResult != null) {
            textViewResult.setText("Тут буде результат");
        }
    }
}