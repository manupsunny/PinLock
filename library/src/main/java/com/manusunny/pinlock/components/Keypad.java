package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

import com.manusunny.pinlock.PinLock;
import com.manusunny.pinlock.R;

public class Keypad extends GridView {
    static String pin;
    private final TypedArray styledAttributes;
    private final Context context;

    public Keypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.PinLock);
    }

    public void setPinListener(PinLock pinLock) {
        pin = "";
        setNumColumns(3);
        setAdapter(new KeypadAdapter(context, styledAttributes, pinLock));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        styledAttributes.recycle();
    }
}
