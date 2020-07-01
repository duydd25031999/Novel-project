package com.tutorial.novelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.model.Volumne;
import com.tutorial.novelproject.ui.detail.ChapterListAdapter;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailNovelActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    public final static String NOVEL_URL = "novel_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_novel);

        Intent intent = getIntent();
        String novelUrl = intent.getStringExtra(NOVEL_URL);

        ApiCaller apiCaller = new ApiCaller();
        apiCaller.getNovelDetail(novelUrl, this, this, this);
    }

    private void setDetailNovel(NovelDetail novelDetail) {
        TextView txtName = findViewById(R.id.detail_novel_name);
        TextView txtAuthor = findViewById(R.id.author_name);
        TextView txtArtist = findViewById(R.id.artist_name);
        ImageView imageView = findViewById(R.id.detail_novel_image);

        txtName.setText(novelDetail.getTitle());
        txtAuthor.setText(novelDetail.getAuthor());
        txtArtist.setText(novelDetail.getArtist());
        Glide
            .with(findViewById(R.id.detail_novel_layout))
            .load(novelDetail.getImageUrl())
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_sync_24)
            .into(imageView);
    }

    private void setToobar(String name) {
        Toolbar toolbar = findViewById(R.id.detail_novel_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        TextView title = toolbar.findViewById(R.id.detail_novel_toolbar_title);
        title.setText(name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setChapterList(ArrayList<Volumne> volumnes) {
        ExpandableListView expandableListView = findViewById(R.id.vol_list);
        ChapterListAdapter chapterListAdapter = new ChapterListAdapter(this, volumnes);
        expandableListView.setAdapter(chapterListAdapter);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("get response", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            NovelDetail novelDetail = NovelDetail.createFromJson(response);

            setToobar(novelDetail.getTitle());
            setDetailNovel(novelDetail);
            setChapterList(novelDetail.getVolumnes());
        } catch (JSONException e) {
            Log.e("json parser", e.getMessage());
        }
    }
}
