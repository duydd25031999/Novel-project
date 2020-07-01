package com.tutorial.novelproject.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapterRow {
    private String title;
    private String url;

    public ChapterRow() {
    }

    public ChapterRow(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public static ChapterRow createFromJson(JSONObject json) throws JSONException {
        ChapterRow chapterRow = new ChapterRow();
        chapterRow.title = json.getString("title");
        chapterRow.url = json.getString("url");
        return chapterRow;
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
}
