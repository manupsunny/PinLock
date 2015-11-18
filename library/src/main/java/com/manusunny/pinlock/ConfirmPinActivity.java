package com.manusunny.pinlock;

import android.os.Bundle;

public abstract class ConfirmPinActivity extends BasePinActivity implements PinLock {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLabel(TEXT_FIRST_TRY);
    }

    @Override
    public final void onCompleted(String pin) {
        resetStatus();
        if (isPinCorrect(pin)) {
            setResult(SUCCESS);
            finish();
        } else {
            setLabel(TEXT_PIN_INVALID);
        }
    }

    public abstract boolean isPinCorrect(String pin);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(CANCELLED);
        finish();
    }
}
