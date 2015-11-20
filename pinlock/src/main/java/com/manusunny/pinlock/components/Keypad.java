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

package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

import com.manusunny.pinlock.PinListener;
import com.manusunny.pinlock.R;


/**
 * GridView of buttons for PIN input. It uses KeypadAdaptor for filling in the buttons
 * @see KeypadAdapter
 * @since 1.0.0
 */
public class Keypad extends GridView {


    /**
     * Stores the PIN value entered by user
     */
    static String pin;


    /**
     * TypedArray of styled attributes passed to the element
     */
    private final TypedArray styledAttributes;


    /**
     * Stores the context of current activity
     */
    private final Context context;

    public Keypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.PinLock);
        pin = "";
        setNumColumns(3);
    }


    /**
     * Setting listener for PIN lock which handles pinChange, forgotPIN etc
     * @param pinListener Implementation of Interface PinListener.
     * @see PinListener
     */
    public void setPinListener(PinListener pinListener) {
        setAdapter(new KeypadAdapter(context, styledAttributes, pinListener));
    }


    /**
     * Executed just before destroying Keypad object. Used to recycle StyledAttributes properly
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        styledAttributes.recycle();
    }
}
