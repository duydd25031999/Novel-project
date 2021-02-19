package com.tutorial.novelproject.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.repositories.SearchNovelRepository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private SearchNovelRepository repository;
    private LiveData<List<NovelCard>> allNovelCards;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = new SearchNovelRepository(application);
        allNovelCards = repository.getAllNovelCards();
    }

    public LiveData<List<NovelCard>> getAllNovelCards() {
        return allNovelCards;
    }

    public void searchNovel(String keyword, List<String> genres) {
        repository.searchNovel(keyword, genres);
    }


    // TODO: Implement the ViewModel
}
