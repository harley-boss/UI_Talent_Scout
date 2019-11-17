package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public class MusicViewHolder implements View.OnClickListener {
    public Activity activity;
    public ImageView imageView;
    private int previous = 1;
    private int current = 1;

    public MusicViewHolder(Activity activity) {
        this.activity = activity;
    }

    public void update() {

    }

    @Override
    public void onClick(View v) {

    }
}
