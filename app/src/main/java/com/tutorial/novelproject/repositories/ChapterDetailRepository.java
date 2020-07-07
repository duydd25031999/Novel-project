package com.tutorial.novelproject.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.database.ChapterDAO;
import com.tutorial.novelproject.database.ChapterDatabase;
import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.model.ChapterWithContent;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChapterDetailRepository implements Response.Listener<JSONObject>, Response.ErrorListener {
    private ChapterDAO chapterDAO;
    private MutableLiveData<ChapterWithContent> liveChapterWithContent;
    private String currentChapterUrl;
    private String novelUrl;
    private String volumeTitle;
    private String chapterTitle;
    private Context context;

    public ChapterDetailRepository(Context context) {
        ChapterDatabase database = ChapterDatabase.getInstance(context);
        chapterDAO = database.chapterDAO();
        liveChapterWithContent = new MutableLiveData<ChapterWithContent>();
        this.context = context;
    }

    public MutableLiveData<ChapterWithContent> getLiveChapterWithContent() {
        return liveChapterWithContent;
    }

    public void getChapterFromUrl(String url) {
        new GetChapterFromUrlTask(this).execute(url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ChapterWithContent chapterWithContent = ChapterWithContent.createFromJSON(response, currentChapterUrl);
            liveChapterWithContent.postValue(chapterWithContent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static class GetChapterFromUrlTask extends AsyncTask<String, Void, Void> {
        private ChapterDetailRepository repository;

        public GetChapterFromUrlTask(ChapterDetailRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(String... strings) {
            String url = strings[0];
            this.repository.currentChapterUrl = url;

            if (false) {
                // TO DO : get chapter from database
            } else {
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.getChapter(url, repository, repository, repository.context);
            }

            return null;
        }
    }
}
