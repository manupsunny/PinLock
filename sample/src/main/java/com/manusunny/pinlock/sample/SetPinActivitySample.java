package com.manusunny.pinlock.sample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.manusunny.pinlock.SetPinActivity;

public class SetPinActivitySample extends SetPinActivity {

    @Override
    public void onPinSet(String pin) {
        SharedPreferences.Editor edit = MainActivity.pinLockPrefs.edit();
        edit.putString("pin", pin);
        edit.commit();
        setResult(SUCCESS);
        finish();
    }
}
