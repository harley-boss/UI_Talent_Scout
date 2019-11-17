package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.widget.TextView;

public class ExpandableViewHolder {
    private final Activity activity;
    public TextView title;

    public ExpandableViewHolder(Activity activity) {
        this.activity = activity;
    }

    public void update(String string) {
        if (title == null) {
            return;
        }
        title.setText(string);
    }
}
