package com.tutorial.novelproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.RESTRICT;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Chapter.class,
        parentColumns = "id",
        childColumns = "chapter_id",
        onDelete = RESTRICT
    ),
    indices = @Index("chapter_id")
)
public class ChapterContent {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "chapter_id")
    private int chapterId;
    private String content;

    public ChapterContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = (int) chapterId;
    }

    public int getId() {
        return id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getContent() {
        return content;
    }
}
