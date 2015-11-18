package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.LinearLayout;

import com.manusunny.pinlock.R;

public class Dot extends View {

    private final boolean filled;
    private TypedArray styledAttributes;

    public Dot(Context context, TypedArray styledAttributes, boolean filled) {
        super(context);
        this.styledAttributes = styledAttributes;
        this.filled = filled;
        init();
    }

    private void init() {
        setBackground(filled);
        setLayoutParameters();
    }

    private void setLayoutParameters() {

    }

    private void setBackground(boolean filled) {
        final int dotDiameter = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_dotDiameter, 50);
        final int margin = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_dotSpacing, 30);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotDiameter, dotDiameter);
        params.setMargins(margin, 0, margin, 0);
        setLayoutParams(params);

        if (filled) {
            final int background = styledAttributes
                    .getResourceId(R.styleable.PinLock_statusFilledBackground, R.drawable.dot_filled);
            setBackgroundResource(background);
        } else {
            final int background = styledAttributes
                    .getResourceId(R.styleable.PinLock_statusEmptyBackground, R.drawable.dot_empty);
            setBackgroundResource(background);
        }
    }
}
