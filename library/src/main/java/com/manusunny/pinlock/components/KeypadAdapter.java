package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.manusunny.pinlock.PinLock;
import com.manusunny.pinlock.R;

public class KeypadAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final TypedArray styledAttributes;
    private PinLock pinLock;

    public KeypadAdapter(Context context, TypedArray styledAttributes, PinLock pinLock) {
        this.styledAttributes = styledAttributes;
        inflater = LayoutInflater.from(context);
        this.pinLock = pinLock;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Button view;
        if (convertView == null) {
            view = (Button) inflater.inflate(R.layout.pin_input_button, null);
        } else {
            view = (Button) convertView;
        }
        setValues(position, view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pinLength = styledAttributes.getInt(R.styleable.PinLock_pinLength, 4);
                Button key = (Button) v;
                final String keyText = key.getText().toString();
                Keypad.pin = Keypad.pin.concat(keyText);
                pinLock.onPinValueChange(Keypad.pin.length());
                if (Keypad.pin.length() == pinLength) {
                    pinLock.onCompleted(Keypad.pin);
                    Keypad.pin = "";
                }
            }
        });
        return view;
    }

    private void setValues(int position, Button view) {
        view.setTextSize(styledAttributes.getFloat(R.styleable.PinLock_buttonTextSize, 22));

        final int color = styledAttributes.getColor(R.styleable.PinLock_buttonTextColor, Color.BLACK);
        view.setTextColor(color);

        final int background = styledAttributes
                .getResourceId(R.styleable.PinLock_buttonBackground, R.drawable.rectangle);
        view.setBackgroundResource(background);

        if (position == 10) {
            view.setText("0");
        } else if (position == 9) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setText(String.valueOf((position + 1) % 10));
        }
    }
}
