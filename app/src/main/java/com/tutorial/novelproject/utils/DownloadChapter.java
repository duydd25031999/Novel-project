package com.tutorial.novelproject.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.database.ChapterDatabase;
import com.tutorial.novelproject.model.ChapterDetail;

import org.json.JSONException;
import org.json.JSONObject;

public class DownloadChapter extends AsyncTask<String, Void, Void>
    implements Response.Listener<JSONObject>, Response.ErrorListener  {
    private Context context;
    private String url;

    public DownloadChapter() {
    }

    public DownloadChapter(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        url = strings[0];
        ApiCaller apiCaller = new ApiCaller();
        apiCaller.getChapter(url, this, this, context);

        return null;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ChapterDetail chapterDetail = ChapterDetail.createFromJson(response, url);
            ChapterDatabase chapterDatabase = new ChapterDatabase(context, null);
            chapterDatabase.insertChapter(chapterDetail);
            Toast.makeText(context, "Download successful", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Log.e("json parser", e.getMessage());
            Toast.makeText(context, "Download fail", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        if (message == null) {
            Log.e("get response", "no message");
        } else {
            Log.e("get response", message);
        }
        Toast.makeText(context, "Download fail", Toast.LENGTH_LONG).show();
    }
}
