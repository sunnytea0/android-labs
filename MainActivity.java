package com.example.myapp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements InputFragment.OnDataSendListener, ResultFragment.OnCancelListener {

    private InputFragment inputFragment;
    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            inputFragment = new InputFragment();
            resultFragment = new ResultFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.input_container, inputFragment)
                    .replace(R.id.result_container, resultFragment)
                    .commit();
        } else {
            inputFragment = (InputFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.input_container);
            resultFragment = (ResultFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.result_container);
        }
    }

    @Override
    public void onDataSend(String phoneType, String brand) {
        if (resultFragment == null) {
            resultFragment = new ResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.result_container, resultFragment)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }

        resultFragment.updateResult(phoneType, brand);
    }

    @Override
    public void onCancel() {
        if (inputFragment != null) {
            inputFragment.clearForm();
        }
        if (resultFragment != null) {
            resultFragment.clearResult();
        }
    }
}