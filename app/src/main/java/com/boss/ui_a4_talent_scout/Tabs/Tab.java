package com.boss.ui_a4_talent_scout.Tabs;

public interface Tab {
    String getTitleResId();
    int getLayoutResId();
    Class getTabClass();
    Enum getEnumValue();
}
