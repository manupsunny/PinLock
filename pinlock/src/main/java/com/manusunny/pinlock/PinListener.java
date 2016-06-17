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


/**
 * Interface for PinListener which handles all PIN events like
 * onComplete, onChange and onForgot
 * @since 1.0.0
 */
public interface PinListener {


    /**
     * Response code for operation success
     */
    int SUCCESS = 0;


    /**
     * Response code for operation cancelled
     */
    int CANCELLED = 1;


    /**
     * Response code for invalid PIN
     */
    int INVALID = 3;


    /**
     * Response code for forgot PIN
     */
    int FORGOT = 4;


    /**
     * Invokes when user completes entering PIN
     * @param pin PIN value entered by user
     */
    void onCompleted(String pin);


    /**
     * Invokes when user clicks on Keypad
     * @param length Current length of PIN
     */
    void onPinValueChange(int length);


    /**
     * Invokes when user clicks forgot button
     */
    void onForgotPin();
}
