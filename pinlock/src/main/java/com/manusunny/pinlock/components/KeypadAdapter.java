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
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.manusunny.pinlock.PinListener;
import com.manusunny.pinlock.R;


/**
 * ViewAdaptor for Keypad which will add buttons to Keypad
 * @see Keypad
 * @since 1.0.0
 */
public class KeypadAdapter extends BaseAdapter {


    /**
     * LayoutInflator which is used to inflate views to UI
     */
    private final LayoutInflater inflater;


    /**
     * TypedArray of styled attributes passed to the element
     */
    private final TypedArray styledAttributes;


    /**
     * Stores the context of current activity
     */
    private final Context context;


    /**
     * Implementation of PinListener interface. Used to handle PIN change events
     */
    private PinListener pinListener;


    /**
     * Constructor
     * @param context Current application context
     * @param styledAttributes TypedArray of styled attributes passed to the element
     * @param pinListener Implementation of PinListener interface
     */
    public KeypadAdapter(Context context, TypedArray styledAttributes, PinListener pinListener) {
        this.styledAttributes = styledAttributes;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.pinListener = pinListener;
    }

    @Override
    public int getCount() {
        return 11;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (position == 10)
            return 0;
        return ((position + 1) % 10);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button view;
        if (convertView == null) {
            view = (Button) inflater.inflate(R.layout.pin_input_button, null);
        } else {
            view = (Button) convertView;
        }

        setStyle(view);
        setValues(position, view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = styledAttributes.getInt(R.styleable.PinLock_keypadClickAnimationDuration, 100);
                TransitionDrawable transition = (TransitionDrawable) v.getBackground();
                transition.startTransition(duration);

                final int pinLength = styledAttributes.getInt(R.styleable.PinLock_pinLength, 4);
                Button key = (Button) v;
                final String keyText = key.getText().toString();
                Keypad.pin = Keypad.pin.concat(keyText);

                vibrateIfEnabled();
                pinListener.onPinValueChange(Keypad.pin.length());
                if (Keypad.pin.length() == pinLength) {
                    pinListener.onCompleted(Keypad.pin);
                    Keypad.pin = "";
                }
                transition.reverseTransition(duration);
            }
        });
        return view;
    }


    /**
     * Vibrate device on each key press if the feature is enabled
     */
    private void vibrateIfEnabled() {
        final boolean enabled = styledAttributes.getBoolean(R.styleable.PinLock_vibrateOnClick, false);
        if(enabled){
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            final int duration = styledAttributes.getInt(R.styleable.PinLock_vibrateDuration, 20);
            v.vibrate(duration);
        }
    }


    /**
     * Setting Button background styles such as background, size and shape
     * @param view Button itself
     */
    private void setStyle(Button view) {
        final int textSize = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_keypadTextSize, 20);
        view.setTextSize(textSize);

        final int color = styledAttributes.getColor(R.styleable.PinLock_keypadTextColor, Color.BLACK);
        view.setTextColor(color);

        final int background = styledAttributes
                .getResourceId(R.styleable.PinLock_keypadButtonShape, R.drawable.rectangle);
        view.setBackgroundResource(background);
    }


    /**
     * Setting Button text
     * @param position Position of Button in GridView
     * @param view Button itself
     */
    private void setValues(int position, Button view) {
        if (position == 10) {
            view.setText("0");
        } else if (position == 9) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setText(String.valueOf((position + 1) % 10));
        }
    }
}
