/*
 * Copyright (C) 2015. Manu Sunny <manupsunny@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.manusunny.pinlock;

import android.os.Bundle;


/**
 * Abstract class for PIN set activity.
 * Subclass this activity to show SetPin screen.
 * All subclasses should implement isPinCorrect() method
 * @since 1.0.0
 */
public abstract class SetPinActivity extends BasePinActivity implements PinListener {


    /**
     * Stores the first PIN entered by user. Used for confirmation
     */
    String firstPin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLabel(TEXT_FIRST_TRY_NEW);
        disableForgotButton();
    }


    /**
     * Implementation of BasePinActivity method
     * @param pin PIN value entered by user
     */
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

    /**
     * Abstract method which gives the PIN entered by user
     * @param pin PIN value entered by user
     */
    public abstract void onPinSet(String pin);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(CANCELLED);
        finish();
    }
}
