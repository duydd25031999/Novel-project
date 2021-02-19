package com.tutorial.novelproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.model.ChapterContent;

@Database(entities = {Chapter.class, ChapterContent.class}, version = 1)
public abstract class ChapterDatabase extends RoomDatabase {
    private static ChapterDatabase instance;
    private static final String DATABASE_NAME = "db_chapter";

    public abstract ChapterDAO chapterDAO();

    public static synchronized ChapterDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                ChapterDatabase.class,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build();

        }
        return instance;
    }
}
