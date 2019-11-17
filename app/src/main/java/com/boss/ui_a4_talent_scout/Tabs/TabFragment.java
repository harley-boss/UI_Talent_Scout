package com.boss.ui_a4_talent_scout.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {
    protected Tab tab = null;
    protected View rootView;
    protected boolean visible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        tab = (Tab) bundle.getSerializable(TabAdapter.SERIALIZABLE_TAB);
        if (tab != null) {
            rootView = inflater.inflate(tab.getLayoutResId(), container, false);
            return rootView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    public void refresh() {

    }

    public void setTabVisible(boolean visible) {
        this.visible = visible;
        if (visible) {
            refresh();
        }
    }

    public boolean isTabVisible() {
        return visible;
    }

    public Tab getTab() {
        return tab;
    }
}
