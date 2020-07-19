package com.tutorial.novelproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity
public class Chapter {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String url;
    private String volume;
    private String title;
    private String prevUrl;
    private String nextUrl;
    private String novelUrl;

    public Chapter(String url, String volume, String title, String prevUrl, String nextUrl, String novelUrl) {
        this.url = url;
        this.volume = volume;
        this.title = title;
        this.prevUrl = prevUrl;
        this.nextUrl = nextUrl;
        this.novelUrl = novelUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getVolume() {
        return volume;
    }

    public String getTitle() {
        return title;
    }

    public String getPrevUrl() {
        return prevUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getNovelUrl() {
        return novelUrl;
    }
}
