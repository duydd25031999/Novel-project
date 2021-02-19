package com.tutorial.novelproject.ui.read;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.ChapterWithContent;
import com.tutorial.novelproject.repositories.ChapterDetailRepository;

public class ReadChapterViewModel extends AndroidViewModel {
    private ChapterDetailRepository repository;
    private LiveData<ChapterWithContent> liveChapterWithContent;

    public ReadChapterViewModel(@NonNull Application application) {
        super(application);
        repository = new ChapterDetailRepository(application);
        liveChapterWithContent = repository.getLiveChapterWithContent();
    }

    public LiveData<ChapterWithContent> getLiveChapterWithContent() {
        return liveChapterWithContent;
    }

    public void getChapterFromUrl(
        String url,
        String chapterTitle,
        String volumeTitle,
        String novelUrl
    ) {
        repository.getChapterFromUrl(url, chapterTitle, volumeTitle, novelUrl);
    }
}
