package com.msp.sample;

import android.os.Bundle;
import android.widget.Toast;

import com.manusunny.pinlock.ConfirmPinActivity;

public class ConfirmPinActivitySample extends ConfirmPinActivity {

    private String currentPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPin = MainActivity.pinLockPrefs.getString("pin", "");
    }

    @Override
    public boolean isPinCorrect(String pin) {
        return pin.equals(currentPin);
    }

    @Override
    public void onForgotPin() {
        Toast.makeText(this, "Sorry. Not Implemented", Toast.LENGTH_SHORT).show();
    }
}
