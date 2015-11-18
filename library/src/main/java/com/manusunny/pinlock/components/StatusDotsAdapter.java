package com.manusunny.pinlock.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.manusunny.pinlock.R;

public class StatusDotsAdapter extends BaseAdapter {

    private final TypedArray styledAttributes;
    private Context context;
    private int pinLength;

    public StatusDotsAdapter(Context context, TypedArray styledAttributes, int pinLength) {
        this.context = context;
        this.styledAttributes = styledAttributes;
        this.pinLength = pinLength;
    }

    @Override
    public int getCount() {
        return styledAttributes.getInt(R.styleable.PinLock_pinLength, 4);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button view;
        if (convertView == null) {
            view = new Button(context);
        } else {
            view = (Button) convertView;
        }
        setBackground(position, view);
        return view;
    }

    private void setBackground(int position, Button view) {
        final int dotDiameter = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_dotDiameter, 50);
        view.setLayoutParams(new AbsListView.LayoutParams(dotDiameter, dotDiameter));

        if (position < pinLength) {
            final int background = styledAttributes
                    .getResourceId(R.styleable.PinLock_statusFilledBackground, R.drawable.dot_filled);
            view.setBackgroundResource(background);
        } else {
            final int background = styledAttributes
                    .getResourceId(R.styleable.PinLock_statusEmptyBackground, R.drawable.dot_empty);
            view.setBackgroundResource(background);
        }
    }
}
