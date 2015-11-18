package com.msp.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.manusunny.pinlock.PinLock;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE_SET_PIN = 0;
    public static final int REQUEST_CODE_CHANGE_PIN = 1;
    public static final int REQUEST_CODE_CONFIRM_PIN = 2;
    static SharedPreferences pinLockPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinLockPrefs = getSharedPreferences("PinLockPrefs", MODE_PRIVATE);
        init();
    }

    private void init() {
        TextView setPin = (TextView) findViewById(R.id.set_pin);
        TextView confirmPin = (TextView) findViewById(R.id.confirm_pin);

        String pin = pinLockPrefs.getString("pin", "");
        if (pin.equals("")) {
            confirmPin.setEnabled(false);
        } else {
            setPin.setText("Change PIN");
        }

        View.OnClickListener clickListener = getOnClickListener();
        setPin.setOnClickListener(clickListener);
        confirmPin.setOnClickListener(clickListener);
    }

    @NonNull
    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                String pin = pinLockPrefs.getString("pin", "");

                if (id == R.id.set_pin && pin.equals("")) {
                    Intent intent = new Intent(MainActivity.this, SetPinActivitySample.class);
                    startActivityForResult(intent, REQUEST_CODE_SET_PIN);
                } else if (id == R.id.set_pin) {
                    Intent intent = new Intent(MainActivity.this, ConfirmPinActivitySample.class);
                    startActivityForResult(intent, REQUEST_CODE_CHANGE_PIN);
                } else if (id == R.id.confirm_pin) {
                    Intent intent = new Intent(MainActivity.this, ConfirmPinActivitySample.class);
                    startActivityForResult(intent, REQUEST_CODE_CONFIRM_PIN);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_SET_PIN : {
                if(resultCode == PinLock.SUCCESS){
                    Toast.makeText(this, "Pin is set :)", Toast.LENGTH_SHORT).show();
                } else if(resultCode == PinLock.CANCELLED) {
                    Toast.makeText(this, "Pin set cancelled :|", Toast.LENGTH_SHORT).show();
                }
                refreshActivity();
                break;
            }
            case REQUEST_CODE_CHANGE_PIN : {
                if(resultCode == PinLock.SUCCESS){
                    Intent intent = new Intent(MainActivity.this, SetPinActivitySample.class);
                    startActivityForResult(intent, REQUEST_CODE_SET_PIN);
                } else if(resultCode == PinLock.CANCELLED){
                    Toast.makeText(this, "Pin change cancelled :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_CODE_CONFIRM_PIN : {
                if(resultCode == PinLock.SUCCESS){
                    Toast.makeText(this, "Pin is correct :)", Toast.LENGTH_SHORT).show();
                } else if(resultCode == PinLock.CANCELLED) {
                    Toast.makeText(this, "Pin confirm cancelled :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    private void refreshActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
