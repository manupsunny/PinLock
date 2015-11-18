package com.manusunny.pinlock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.manusunny.pinlock.components.Keypad;
import com.manusunny.pinlock.components.StatusDots;

public abstract class BasePinActivity extends Activity implements PinLock {

    private TextView label;
    private StatusDots statusDots;
    private TextView forgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        Keypad keypad = (Keypad) findViewById(R.id.keypad);
        keypad.setPinListener(this);
        label = (TextView) findViewById(R.id.label);
        statusDots = (StatusDots) findViewById(R.id.statusDots);
        setupButtons();
    }

    private void setupButtons() {
        TextView cancelButton = (TextView) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(CANCELLED);
                finish();
            }
        });
        forgetButton = (TextView) findViewById(R.id.forgotPin);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onForgotPin();
                setResult(FORGOT);
                finish();
            }
        });
    }

    public void disableForgotButton(){
        forgetButton.setEnabled(false);
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void resetStatus() {
        statusDots.initialize();
    }

    @Override
    public abstract void onCompleted(String pin);

    @Override
    public void onPinValueChange(int length) {
        statusDots.updateStatusDots(length);
    }
}
