package com.tutorial.novelproject.ui.read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.model.ChapterContent;
import com.tutorial.novelproject.model.ChapterWithContent;
import com.tutorial.novelproject.ui.novel.DetailNovelActivity;
import com.tutorial.novelproject.R;

import java.util.List;

public class ReadChapterActivity extends AppCompatActivity {
    public final static String CHAPTER_URL = "chapter_url";
    public final static String CHAPTER_TITLE = "chapter_title";
    public final static String VOLUME_TITLE = "volume_title";
    public final static String NOVEL_URL = "novel_url";

    private ParagraphViewList paragraphViewList;
    private ReadChapterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chapter);

        LinearLayout paragraphList = findViewById(R.id.paragraph_list);
        paragraphViewList = new ParagraphViewList(paragraphList, this);

        getInitData();
    }

    private void getInitData() {
        Intent intent = getIntent();
        String chapterUrl = intent.getStringExtra(CHAPTER_URL);
        String chapterTitle = intent.getStringExtra(CHAPTER_TITLE);
        String volumeTitle = intent.getStringExtra(VOLUME_TITLE);
        String novelUrl = intent.getStringExtra(NOVEL_URL);

        initViewModel(chapterUrl, chapterTitle, volumeTitle, novelUrl);
    }

    private void initViewModel(
        String chapterUrl,
        String chapterTitle,
        String volumeTitle,
        String novelUrl
    ) {
        viewModel = new ViewModelProvider(this).get(ReadChapterViewModel.class);
        viewModel.getLiveChapterWithContent().observe(this, new Observer<ChapterWithContent>() {
            @Override
            public void onChanged(ChapterWithContent chapterWithContent) {
                Chapter chapter = chapterWithContent.chapter;
                List<ChapterContent> contents = chapterWithContent.contents;
                setMoveChapEvent(R.id.previous_chapter, chapter.getPrevUrl(), chapterTitle, volumeTitle, chapter.getNovelUrl());
                setMoveChapEvent(R.id.next_chapter, chapter.getNextUrl(), chapterTitle, volumeTitle, chapter.getNovelUrl());
                setConstText(chapter.getTitle(), chapter.getVolume(), chapter.getNovelUrl());
                paragraphViewList.listView(contents);
            }
        });
        viewModel.getChapterFromUrl(chapterUrl, chapterTitle, volumeTitle, novelUrl);
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


    private void setMoveChapEvent(
        int btnId,
        String url,
        String chapterTitle,
        String volumeTitle,
        String novelUrl
    ) {
        LinearLayout btn = findViewById(btnId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("setMoveChapEvent", url);
                viewModel.getChapterFromUrl(url, chapterTitle, volumeTitle, novelUrl);
            }
        });
    }
}
