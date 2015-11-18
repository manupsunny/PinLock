package com.manusunny.pinlock;

import android.os.Bundle;

public abstract class SetPinActivity extends BasePinActivity implements PinLock {

    String firstPin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLabel(TEXT_FIRST_TRY_NEW);
        disableForgotButton();
    }

    @Override
    public final void onCompleted(String pin) {
        resetStatus();
        if ("".equals(firstPin)) {
            firstPin = pin;
            setLabel(TEXT_CONFIRM_PIN);
        } else {
            if (pin.equals(firstPin)) {
                onPinSet(pin);
                setResult(SUCCESS);
                finish();
            } else {
                setLabel(TEXT_PIN_MISMATCH);
                firstPin = "";
            }
        }
        resetStatus();
    }

    public abstract void onPinSet(String pin);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(CANCELLED);
        finish();
    }
}
