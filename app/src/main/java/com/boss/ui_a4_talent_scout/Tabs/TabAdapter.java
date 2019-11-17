package com.boss.ui_a4_talent_scout.Tabs;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

public class TabAdapter<E extends Enum<E> & Tab> extends FragmentPagerAdapter {
    static final String SERIALIZABLE_TAB = "tab";
    private final ArrayList<TabFragment> tabFragments;
    private final Class<E> enumClass;

    public TabAdapter(Class<E> enumClass, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.enumClass = enumClass;
        tabFragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Tab tab = getTab(position);
        return getTabFragment(tab);
    }

    @Override
    public int getCount() {
        return getEnumValues().length;
    }

    public Tab getTab(int position) {
        if (position >= 0 && position < getCount()) {
            return getEnumValues()[position];
        } else {
            return null;
        }
    }

    public int getPostion(Tab tab) {
        int pos = 0;
        for (Tab t : getEnumValues()) {
            if (t == tab) {
                return pos;
            } else {
                pos++;
            }
        }
        return 0;
    }

    private E[] getEnumValues() {
        return enumClass.getEnumConstants();
    }

    public TabFragment getTabFragment(Tab tab) {
        TabFragment tabFragment = null;
        for (TabFragment tf : tabFragments) {
            if (tf.getTab() == tab) {
                tabFragment = tf;
                break;
            }
        }
        if (tabFragment == null) {
            try {
                tabFragment = (TabFragment) tab.getTabClass().newInstance();
                Bundle bundle = new Bundle();
                bundle.putSerializable(SERIALIZABLE_TAB, tab.getEnumValue());
                tabFragment.setArguments(bundle);
            } catch (InstantiationException | IllegalAccessException ex) {

            }
        }
        return tabFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabFragment tf = (TabFragment) super.instantiateItem(container, position);
        tabFragments.add(tf);
        tf.refresh();
        return tf;
    }
}
