package com.tutorial.novelproject.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class NovelCardRepository implements Response.Listener<JSONObject>, Response.ErrorListener {
    private MutableLiveData<List<NovelCard>> allNovelCards;
    private MutableLiveData<Integer> liveCurrentPage;
    private MutableLiveData<Integer> liveMaxPage;
    private Context context;

    public NovelCardRepository(Context context) {
        allNovelCards = new MutableLiveData<List<NovelCard>>();
        liveCurrentPage = new MutableLiveData<Integer>();
        liveMaxPage = new MutableLiveData<Integer>();
        this.context = context;
    }

    private void setListNovelCards(List<NovelCard> list) {
        allNovelCards.postValue(list);
    }

    public MutableLiveData<Integer> getLiveCurrentPage() {
        return liveCurrentPage;
    }

    public MutableLiveData<Integer> getLiveMaxPage() {
        return liveMaxPage;
    }

    private void setCurrentPage(int currentPage) {
        liveCurrentPage.postValue(currentPage);
    }

    private void setMaxPage(int maxPage) {
        liveMaxPage.postValue(maxPage);
    }

    public MutableLiveData<List<NovelCard>> getAllNovelCards() {
        return allNovelCards;
    }

    public void changeNovelCardPage(int page) {
        new PagingNovelCardApiTask(this).execute(page);
    }

    public void firstNovelCardPage() {
        new PagingNovelCardApiTask(this).execute(1);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            int currentPage = response.getInt("current");
            int maxPage = response.getInt("lastPage");

            ArrayList<NovelCard> novelCardArrayList = new ArrayList<NovelCard>();
            JSONArray booksJson = response.getJSONArray("books");
            for (int i = 0; i < booksJson.length(); i++) {
                NovelCard novelCard = NovelCard.createFromJson(booksJson.getJSONObject(i));
                novelCardArrayList.add(novelCard);
            }

            setListNovelCards(novelCardArrayList);
            setCurrentPage(currentPage);
            setMaxPage(maxPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static class PagingNovelCardApiTask extends AsyncTask<Integer, Void, Void> {
        private NovelCardRepository repository;

        public PagingNovelCardApiTask(NovelCardRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int page = integers[0];
            ApiCaller apiCaller = new ApiCaller();
            apiCaller.getAllNovelCardWithPage(page, repository, repository, repository.context);
            return null;
        }
    }
}
