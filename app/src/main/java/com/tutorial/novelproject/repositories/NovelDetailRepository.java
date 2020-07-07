package com.tutorial.novelproject.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONException;
import org.json.JSONObject;

public class NovelDetailRepository implements Response.Listener<JSONObject>, Response.ErrorListener {
    private MutableLiveData<NovelDetail> liveNovel;
    private Context context;

    public NovelDetailRepository(Context context) {
        this.context = context;
        liveNovel = new MutableLiveData<NovelDetail>();
    }

    public MutableLiveData<NovelDetail> getLiveNovel() {
        return liveNovel;
    }

    public void setNovel(NovelDetail novel) {
        liveNovel.postValue(novel);
    }

    public void getNovelDetailFromUrl(String url) {
        new NovelDetailApiTask(this).execute(url);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            NovelDetail novelDetail = NovelDetail.createFromJson(response);
            setNovel(novelDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static class NovelDetailApiTask extends AsyncTask<String, Void, Void> {
        private NovelDetailRepository repository;

        public NovelDetailApiTask(NovelDetailRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(String... strings) {
            String url = strings[0];
            ApiCaller apiCaller = new ApiCaller();
            apiCaller.getNovelDetail(url, repository, repository, repository.context);
            return null;
        }
    }


}
