package com.boss.ui_a4_talent_scout;

import com.boss.ui_a4_talent_scout.Tabs.Utilities;

import java.util.HashMap;
import java.util.List;

public class ExpandableListData {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> news = Utilities.generateNews();
        List<String> facebook = Utilities.generateSocialMedia();
        List<String> songs = Utilities.generateSongs();
        List<String> concerts = Utilities.generateUpcomingConcerts();

        expandableListDetail.put("News", news);
        expandableListDetail.put("Facebook", facebook);
        expandableListDetail.put("Songs", songs);
        expandableListDetail.put("Concerts", concerts);
        return expandableListDetail;
    }
}
