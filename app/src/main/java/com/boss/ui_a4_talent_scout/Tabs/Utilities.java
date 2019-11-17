package com.boss.ui_a4_talent_scout.Tabs;

import com.boss.ui_a4_talent_scout.BandNames;
import com.boss.ui_a4_talent_scout.Musician;

import java.util.ArrayList;

public class Utilities {

    public static ArrayList<String> generateNews() {
        ArrayList<String> news = new ArrayList<>();
        news.add("Dr. Dre arrested for armed robbery!");
        news.add("Whitney Houston is back in rehab");
        news.add("Tina Turner's hair caught on fire!");
        return news;
    }

    public static ArrayList<String> generateSocialMedia() {
        ArrayList<String> facebook = new ArrayList<>();
        facebook.add("Follow Macho Man before he dies on Instagram!");
        facebook.add("1 like = 1 prayer for Tina Turner");
        return facebook;
    }

    public static ArrayList<String> generateSongs() {
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Eminem - Superman");
        songs.add("The Wiggles - Wheels on the Bus");
        songs.add("Dr. Dre - Forgot About Dre");
        return songs;
    }

    public static ArrayList<String> generateUpcomingConcerts() {
        ArrayList<String> concerts = new ArrayList<>();
        concerts.add("Dr. Dre performing at Scotiabank center Dec. 24th 2019");
        concerts.add("Whitney Houston performing at Scotiabank center June 24th 2030");
        concerts.add("Tina Turner performing at Scotiabank center May 9th 2050");
        return concerts;
    }

    public static ArrayList<Musician> generateMusicians() {
        ArrayList<Musician> musicians = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            musicians.add(new Musician(BandNames.getBandName(i), ""));
        }
        return musicians;
    }

    public static ArrayList<String> generateSocialMediaSites() {
        ArrayList<String> sites = new ArrayList<>();
        sites.add("Facebook");
        sites.add("Twitter");
        sites.add("MySpace");
        sites.add("Soundcloud");
        sites.add("Instagram");
        sites.add("TikTok");
        return sites;
    }
}
