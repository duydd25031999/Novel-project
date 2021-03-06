package com.tutorial.novelproject.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterWithContent {
    @Embedded
    public Chapter chapter;

    @Relation(parentColumn = "id", entityColumn = "chapter_id", entity = ChapterContent.class)
    public List<ChapterContent> contents;

    public static ChapterWithContent createFromJSON(
        JSONObject json,
        String chapterUrl,
        String chapterTitle,
        String volumeTitle,
        String novelUrl
    )
            throws JSONException {
        String prevUrl = json.getString("prev");
        String nextUrl = json.getString("next");

        ChapterWithContent chapterWithContent = new ChapterWithContent();

        chapterWithContent.chapter = new Chapter(
            chapterUrl,
            volumeTitle,
            chapterTitle,
            prevUrl,
            nextUrl,
            novelUrl
        );

        ArrayList<ChapterContent> listContent = new ArrayList<ChapterContent>();
        JSONArray jsonArray = json.getJSONArray("content");
        for(int i = 0; i < jsonArray.length(); i++) {
            ChapterContent content = new ChapterContent(jsonArray.getString(i));
            listContent.add(content);
        }

        chapterWithContent.contents = listContent;

        return  chapterWithContent;
    }
}
