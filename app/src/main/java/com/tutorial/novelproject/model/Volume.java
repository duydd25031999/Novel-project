package com.tutorial.novelproject.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volume {
    private String title;
    private ArrayList<ChapterRow> chapters;

    public Volume() {
        chapters = new ArrayList<ChapterRow>();
    }

    public Volume(String title, ArrayList<ChapterRow> chapters) {
        this.title = title;
        this.chapters = chapters;
    }

    public static Volume createFromJson(JSONObject json) throws JSONException {
        Volume volume = new Volume();
        volume.title = json.getString("title");
        JSONArray chaptersJson = json.getJSONArray("chapters");

        for (int i = 0; i < chaptersJson.length(); i++) {
            JSONObject jsonObject = chaptersJson.getJSONObject(i);
            ChapterRow chapterRow = ChapterRow.createFromJson(jsonObject);
            volume.chapters.add(chapterRow);
        }

        return volume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChapterRow> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ChapterRow> chapters) {
        this.chapters = chapters;
    }
}
