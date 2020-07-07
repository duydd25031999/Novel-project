package com.tutorial.novelproject.ui.download;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.repositories.DownloadedChapterRepository;

import java.util.List;

public class DownloadedChapterViewModel extends AndroidViewModel {
    private DownloadedChapterRepository repository;
    private LiveData<List<Chapter>> liveListDownloadedChapters;


    public DownloadedChapterViewModel(@NonNull Application application) {
        super(application);
        repository = new DownloadedChapterRepository(application);
        liveListDownloadedChapters = repository.getLiveListDownloadedChapters();
    }

    public LiveData<List<Chapter>> getLiveListDownloadedChapters() {
        return liveListDownloadedChapters;
    }
}
