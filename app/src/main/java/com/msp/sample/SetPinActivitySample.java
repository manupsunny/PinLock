package com.msp.sample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.manusunny.pinlock.SetPinActivity;

public class SetPinActivitySample extends SetPinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPinSet(String pin) {
        SharedPreferences.Editor edit = MainActivity.pinLockPrefs.edit();
        edit.putString("pin", pin);
        edit.commit();
        setResult(SUCCESS);
        finish();
    }

    @Override
    public void onForgotPin() {
        Toast.makeText(this, "Sorry. Not Implemented", Toast.LENGTH_SHORT).show();
    }
}
