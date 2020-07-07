package com.tutorial.novelproject.ui.novel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.model.Volume;

import java.util.ArrayList;

public class DetailNovelActivity extends AppCompatActivity {
    public final static String NOVEL_URL = "novel_url";
    private String novelUrl;
    private DetailNovelViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_novel);

        Intent intent = getIntent();
        novelUrl = intent.getStringExtra(NOVEL_URL);

        viewModel = new ViewModelProvider(this).get(DetailNovelViewModel.class);
        viewModel.getLiveNovel().observe(this, new Observer<NovelDetail>() {
            @Override
            public void onChanged(NovelDetail novelDetail) {
                setToobar(novelDetail.getTitle());
                setDetailNovel(novelDetail);
                setChapterList(novelDetail.getVolumes());
            }
        });
        viewModel.getNovelDetailFromUrl(novelUrl);
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

    private void setChapterList(ArrayList<Volume> volumnes) {
        ExpandableListView expandableListView = findViewById(R.id.vol_list);
        ChapterListAdapter chapterListAdapter = new ChapterListAdapter(this, volumnes, novelUrl);
        expandableListView.setAdapter(chapterListAdapter);
    }
}
