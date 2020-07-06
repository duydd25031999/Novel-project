package com.tutorial.novelproject.ui.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.repositories.NovelCardRepository;

import java.util.List;

public class ListNovelViewModel extends AndroidViewModel {
    private NovelCardRepository repository;
    private LiveData<List<NovelCard>> allNovelCards;
    private LiveData<Integer> liveCurrentPage;
    private LiveData<Integer> liveMaxPage;

    public ListNovelViewModel(@NonNull Application application) {
        super(application);
        repository = new NovelCardRepository(application);
        allNovelCards = repository.getAllNovelCards();
        liveCurrentPage = repository.getLiveCurrentPage();
        liveMaxPage = repository.getLiveMaxPage();
    }

    public void changeNovelCardPage(int page) {
        repository.changeNovelCardPage(page);
    }

    public void firstNovelCardPage() {
        repository.firstNovelCardPage();
    }

    public LiveData<List<NovelCard>> getAllNovelCards() {
        return allNovelCards;
    }

    public LiveData<Integer> getLiveCurrentPage() {
        return liveCurrentPage;
    }

    public LiveData<Integer> getLiveMaxPage() {
        return liveMaxPage;
    }
}
