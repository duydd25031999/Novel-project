package com.tutorial.novelproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.model.ChapterContent;
import com.tutorial.novelproject.model.ChapterWithContent;

import java.util.List;

@Dao
public abstract class ChapterDAO {
    @Insert
    public abstract long insert(Chapter chapter);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(ChapterContent content);

    @Transaction
    public void insert(ChapterWithContent chapterWithContent) {
        long chapterId = insert(chapterWithContent.chapter);

        for (ChapterContent content : chapterWithContent.contents) {
            content.setChapterId(chapterId);
            insert(content);
        }
    }

    @Query("SELECT * FROM chapter")
    public abstract List<Chapter> getAllDownloadedChapters();

    @Query("SELECT * FROM chapter WHERE url LIKE :urlParam")
    public abstract ChapterWithContent getChapterFromUrl(String urlParam);
}
