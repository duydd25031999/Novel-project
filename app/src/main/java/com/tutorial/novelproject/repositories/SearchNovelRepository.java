package com.tutorial.novelproject.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchNovelRepository implements Response.Listener<JSONObject>, Response.ErrorListener  {
    private MutableLiveData<List<NovelCard>> allNovelCards;
    private Context context;

    public SearchNovelRepository(Context context) {
        this.context = context;
        allNovelCards = new MutableLiveData<List<NovelCard>>();
    }

    private void setListNovelCards(List<NovelCard> list) {
        allNovelCards.postValue(list);
    }
    public MutableLiveData<List<NovelCard>> getAllNovelCards() {
        return allNovelCards;
    }
    public void searchNovel(String keyword, List<String> genres) {
        SearchNoteApiTask task = new SearchNoteApiTask(this);
        task.keyword = keyword;
        task.genres = genres;
        task.execute();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<NovelCard> novelCardArrayList = new ArrayList<NovelCard>();
            JSONArray booksJson = response.getJSONArray("books");
            for (int i = 0; i < booksJson.length(); i++) {
                NovelCard novelCard = NovelCard.createFromJson(booksJson.getJSONObject(i));
                novelCardArrayList.add(novelCard);
            }

            setListNovelCards(novelCardArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static class SearchNoteApiTask extends AsyncTask<Void, Void, Void> {
        private SearchNovelRepository repository;
        private String keyword;
        private List<String> genres;

        public SearchNoteApiTask(SearchNovelRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ApiCaller apiCaller = new ApiCaller();
            try {
                apiCaller.searchNovel(keyword, genres, repository, repository, repository.context);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return null;
        }
    }
}
