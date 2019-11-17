package com.boss.ui_a4_talent_scout;

import com.boss.ui_a4_talent_scout.Tabs.Utilities;

import java.util.ArrayList;

public class Musician {
    private String name;
    private String genre;
    private boolean followingConcerts;
    private boolean followingNews;
    private boolean followingFacebook;
    private boolean followingTwitter;
    private ArrayList<String> concerts;
    private ArrayList<String> news;
    private ArrayList<String> socialMedia;
    private ArrayList<String> songs;

    public Musician(String name, String genre) {
        this.name = name;
        this.genre = genre;
        news = Utilities.generateNews();
        socialMedia = Utilities.generateSocialMedia();
        songs = Utilities.generateSongs();
        concerts = Utilities.generateUpcomingConcerts();
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<String> getNews() {
        return news;
    }

    public ArrayList<String> getSocialMedia() {
        return socialMedia;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public ArrayList<String> getConcerts() {
        return concerts;
    }

    public void setFollowingFacebook(boolean isFollowing) {
        followingFacebook = isFollowing;
    }

    public void setFollowingTwitter(boolean isFollowing) {
        followingTwitter = isFollowing;
    }

    public void setFollowingConcerts(boolean isFollowing) {
        followingConcerts = isFollowing;
    }

    public void setFollowingNews(boolean isFollowing) {
        followingNews = isFollowing;
    }

    public boolean isFollowingConcerts() {
        return followingConcerts;
    }

    public boolean isFollowingNews() {
        return followingNews;
    }

    public boolean isFollowingFacebook() {
        return followingFacebook;
    }

    public boolean isFollowingTwitter() {
        return followingTwitter;
    }
}