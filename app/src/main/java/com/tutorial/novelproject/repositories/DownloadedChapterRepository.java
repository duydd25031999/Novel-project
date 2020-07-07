package com.tutorial.novelproject.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.database.ChapterDAO;
import com.tutorial.novelproject.database.ChapterDatabase;
import com.tutorial.novelproject.model.Chapter;

import java.util.List;

public class DownloadedChapterRepository {
    private ChapterDAO chapterDAO;
    private LiveData<List<Chapter>> liveListDownloadedChapters;

    public DownloadedChapterRepository(Context context) {
        ChapterDatabase database = ChapterDatabase.getInstance(context);
        chapterDAO = database.chapterDAO();
        liveListDownloadedChapters = chapterDAO.getAllDownloadedChapters();
    }

    public LiveData<List<Chapter>> getLiveListDownloadedChapters() {
        return liveListDownloadedChapters;
    }
}
