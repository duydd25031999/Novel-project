package com.tutorial.novelproject.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChapterDetail {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String url;
    private ArrayList<String> content;
    private String prevUrl;
    private String nextUrl;

    public ChapterDetail() {
        content = new ArrayList<String>();
    }

    public ChapterDetail(String url, ArrayList<String> content, String prevUrl, String nextUrl) {
        this.url = url;
        this.content = content;
        this.prevUrl = prevUrl;
        this.nextUrl = nextUrl;
    }

    public static ChapterDetail createFromJson(JSONObject json) throws JSONException {
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.setDataFromJson(json);

        return chapterDetail;
    }

    public static ChapterDetail createFromJson(JSONObject json, String url) throws JSONException {
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.url = url;
        chapterDetail.setDataFromJson(json);

        return chapterDetail;
    }

    private void setDataFromJson(JSONObject json) throws JSONException {
        this.prevUrl = json.getString("prev");
        this.nextUrl = json.getString("next");

        JSONArray jsonArray = json.getJSONArray("content");
        for(int i = 0; i < jsonArray.length(); i++) {
            this.content.add(jsonArray.getString(i));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String getPrevUrl() {
        return prevUrl;
    }

    public void setPrevUrl(String prevUrl) {
        this.prevUrl = prevUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
