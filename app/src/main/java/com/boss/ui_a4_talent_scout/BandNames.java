package com.boss.ui_a4_talent_scout;

public enum BandNames {
    name0("Imagine Dragons"),
    name1("Daft Punk"),
    name2("Eminem"),
    name3("Classified"),
    name4("Macho Man Randy Savage"),
    name5("The Roots"),
    name6("Tina Turner"),
    name7("Pink"),
    name8("Whitney Houston"),
    name9("The Wiggles"),
    name10("Dr. Dre");

    private String bandName;

    BandNames(String bandName) {
        this.bandName = bandName;
    }

    public String getBandName() {
        return this.bandName;
    }

    public static String getBandName(int index) {
        return BandNames.values()[index].bandName;
    }
}
