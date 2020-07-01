package com.tutorial.novelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.model.ChapterDetail;
import com.tutorial.novelproject.ui.read.ParagraphViewList;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadChapterActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    public final static String CHAPTER_URL = "chapter_url";
    public final static String CHAPTER_TITLE = "chapter_title";
    public final static String VOLUME_TITLE = "volume_title";
    public final static String NOVEL_URL = "novel_url";

    private ParagraphViewList paragraphViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);
        getInitData();

        LinearLayout paragraphList = findViewById(R.id.paragraph_list);
        paragraphViewList = new ParagraphViewList(paragraphList, this);
    }

    private void getInitData() {
        Intent intent = getIntent();
        String chapterUrl = intent.getStringExtra(CHAPTER_URL);
        String chapterTitle = intent.getStringExtra(CHAPTER_TITLE);
        String volumeTitle = intent.getStringExtra(VOLUME_TITLE);
        String novelUrl = intent.getStringExtra(NOVEL_URL);

        setConstText(chapterTitle, volumeTitle, novelUrl);
        ApiCaller apiCaller = new ApiCaller();
        apiCaller.getChapter(chapterUrl, this, this, this);
    }

    private void setConstText(String chapterTitle, String volumeTitle, String novelUrl) {
        TextView txtChapter = findViewById(R.id.read_chapter);
        TextView txtVolume = findViewById(R.id.read_volume);
        LinearLayout btnNovel = findViewById(R.id.to_detail_novel);

        txtChapter.setText(chapterTitle);
        txtVolume.setText(volumeTitle);

        btnNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadChapterActivity.this, DetailNovelActivity.class);
                intent.putExtra(DetailNovelActivity.NOVEL_URL, novelUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        if (message == null) {
            Log.e("get response", "no message");
        } else {
            Log.e("get response", message);
        }
        onBackPressed();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ChapterDetail chapterDetail = ChapterDetail.createFromJson(response);

            setMoveChapEvent(R.id.previous_chapter, chapterDetail.getPrevUrl());
            setMoveChapEvent(R.id.next_chapter, chapterDetail.getNextUrl());
            paragraphViewList.listView(chapterDetail.getContent());
        } catch (JSONException e) {
            Log.e("json parser", e.getMessage());
            onBackPressed();
        }
    }

    private void setMoveChapEvent(int btnId, String url) {
        LinearLayout btn = findViewById(btnId);
        ReadChapterActivity that = this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("setMoveChapEvent", url);
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.getChapter(url, that, that, that);
            }
        });
    }
}
