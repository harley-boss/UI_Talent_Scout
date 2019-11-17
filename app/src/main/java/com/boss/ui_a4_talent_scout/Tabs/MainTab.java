package com.boss.ui_a4_talent_scout.Tabs;

import com.boss.ui_a4_talent_scout.R;

public enum MainTab implements Tab {
    MY_ARTISTS("My Artists", R.layout.tab_my_artists, MyArtistsTab.class),
    MY_STREAMS("My Streams", R.layout.tab_my_stream, MyStreamsTab.class),
    MY_SHOUTS("My Shouts", R.layout.tab_my_shouts, MyShoutsTab.class);


    private String title;
    private int layoutResId;
    private Class tabClass;

    MainTab(String title, int layoutResId, Class tabClass) {
        this.title = title;
        this.layoutResId = layoutResId;
        this.tabClass = tabClass;
    }

    @Override
    public String getTitleResId() {
        return title;
    }

    @Override
    public int getLayoutResId() {
        return layoutResId;
    }

    @Override
    public Class getTabClass() {
        return tabClass;
    }

    @Override
    public Enum getEnumValue() {
        return this;
    }
}
