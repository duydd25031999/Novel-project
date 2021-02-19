package com.tutorial.novelproject.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.repositories.NovelCardRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private NovelCardRepository repository;
    private LiveData<List<NovelCard>> allNovelCards;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new NovelCardRepository(application);
        allNovelCards = repository.getAllNovelCards();
    }

    public LiveData<List<NovelCard>> getAllNovelCards() {
        return allNovelCards;
    }

    public void firstNovelCardPage() {
        repository.firstNovelCardPage();
    }
}
