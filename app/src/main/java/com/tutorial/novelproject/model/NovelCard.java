package com.tutorial.novelproject.model;

import org.json.JSONException;
import org.json.JSONObject;

public class NovelCard {
    private String title;
    private String url;
    private String lastChapterTitle;
    private String lastChapterUrl;
    private String lastVol;
    private String imageUrl;

    public NovelCard() {
    }

    public NovelCard(String title, String imageUrl, String lastChapterTitle, String lastChapterUrl, String lastVol, String url) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.lastChapterTitle = lastChapterTitle;
        this.lastChapterUrl = lastChapterUrl;
        this.lastVol = lastVol;
        this.url = url;
    }

    public static NovelCard createFromJson(JSONObject json) throws JSONException {
        NovelCard novelCard = new NovelCard();
        novelCard.title = json.getString("title");
        novelCard.url = json.getString("url");
        novelCard.lastChapterTitle = json.getString("latest_chapter");
        novelCard.lastChapterUrl = json.getString("latest_chapter_url");
        novelCard.lastVol = json.getString("latest_vol");
        novelCard.imageUrl = json.getString("img_url");
        return novelCard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastChapterTitle() {
        return lastChapterTitle;
    }

    public void setLastChapterTitle(String lastChapterTitle) {
        this.lastChapterTitle = lastChapterTitle;
    }

    public String getLastChapterUrl() {
        return lastChapterUrl;
    }

    public void setLastChapterUrl(String lastChapterUrl) {
        this.lastChapterUrl = lastChapterUrl;
    }

    public String getLastVol() {
        return lastVol;
    }

    public void setLastVol(String lastVol) {
        this.lastVol = lastVol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
