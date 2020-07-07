package com.tutorial.novelproject.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.database.ChapterDAO;
import com.tutorial.novelproject.database.ChapterDatabase;
import com.tutorial.novelproject.model.ChapterWithContent;
import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONException;
import org.json.JSONObject;

public class NovelDetailRepository implements Response.Listener<JSONObject>, Response.ErrorListener {
    private MutableLiveData<NovelDetail> liveNovel;
    private ChapterDAO chapterDAO;
    private Context context;

    public NovelDetailRepository(Context context) {
        this.context = context;
        liveNovel = new MutableLiveData<NovelDetail>();
        ChapterDatabase database = ChapterDatabase.getInstance(context);
        chapterDAO = database.chapterDAO();
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

    public void downloadChapter(String url) {
        new DownloadChapterTask(this).execute(url);
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

    private static class DownloadChapterTask extends AsyncTask<String, Void, Void>
        implements Response.Listener<JSONObject>, Response.ErrorListener
    {
        private NovelDetailRepository repository;
        private String chapterUrl;

        public DownloadChapterTask(NovelDetailRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(String... strings) {
            String url = strings[0];
            chapterUrl = url;
            ApiCaller apiCaller = new ApiCaller();
            apiCaller.getChapter(url, this, this, repository.context);
            return null;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(repository.context,"onErrorResponse fail ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(JSONObject response) {
            try {
                ChapterWithContent chapterWithContent = ChapterWithContent.createFromJSON(response, chapterUrl);
                repository.chapterDAO.insert(chapterWithContent);
                Toast.makeText(repository.context,"Download successful", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(repository.context,"onResponse fail ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
