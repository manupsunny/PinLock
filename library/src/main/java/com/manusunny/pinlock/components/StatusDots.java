package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.manusunny.pinlock.R;

public class StatusDots extends LinearLayout {
    private final TypedArray styledAttributes;
    private final Context context;

    public StatusDots(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.PinLock);
        initialize();
    }

    private void addDots(int length) {
        removeAllViews();
        final int pinLength = styledAttributes.getInt(R.styleable.PinLock_pinLength, 4);
        for (int i = 0; i < pinLength; i++) {
            Dot dot = new Dot(context, styledAttributes, i < length);
            addView(dot);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        styledAttributes.recycle();
    }

    public void initialize() {
        addDots(0);
    }

    public void updateStatusDots(int pinLength) {
        addDots(pinLength);
    }
}
