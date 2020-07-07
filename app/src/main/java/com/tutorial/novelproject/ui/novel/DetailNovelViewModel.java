package com.tutorial.novelproject.ui.novel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.repositories.NovelDetailRepository;

public class DetailNovelViewModel extends AndroidViewModel {
    private NovelDetailRepository repository;
    private LiveData<NovelDetail> liveNovel;

    public DetailNovelViewModel(@NonNull Application application) {
        super(application);
        repository = new NovelDetailRepository(application);
        liveNovel = repository.getLiveNovel();
    }

    public void getNovelDetailFromUrl(String url) {
        repository.getNovelDetailFromUrl(url);
    }

    public LiveData<NovelDetail> getLiveNovel() {
        return liveNovel;
    }
}
