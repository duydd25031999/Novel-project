package com.tutorial.novelproject.ui.novel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.ChapterRow;
import com.tutorial.novelproject.model.NovelDetail;
import com.tutorial.novelproject.model.Volume;
import com.tutorial.novelproject.ui.read.ReadChapterActivity;

public class VolumesViewList {
    private LinearLayout layout;
    private DetailNovelActivity activity;
    private NovelDetail novel;
    private String novelUrl;

    public VolumesViewList(LinearLayout layout, DetailNovelActivity activity) {
        this.layout = layout;
        this.activity = activity;
    }

    public void listVolumes(NovelDetail novel, String novelUrl) {
        this.novel = novel;
        this.novelUrl = novelUrl;
        layout.removeAllViews();

        for(Volume volume : novel.getVolumes()) {
            createVolumeView(volume);
        }
    }

    private void createVolumeView(Volume volume) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View convertView = inflater.inflate(R.layout.chapter_list, layout, false);
        ((TextView) convertView).setText(volume.getTitle());
        layout.addView(convertView);

        for (ChapterRow chapter : volume.getChapters()) {
            createChapterView(volume, chapter);
        }
    }

    private void createChapterView(Volume volume, ChapterRow chapter) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View convertView = inflater.inflate(R.layout.chapter_row, layout, false);

        TextView txtTitle = convertView.findViewById(R.id.chapter_title);
        txtTitle.setText(chapter.getTitle());
        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ReadChapterActivity.class);
                intent.putExtra(ReadChapterActivity.CHAPTER_URL, chapter.getUrl());
                intent.putExtra(ReadChapterActivity.CHAPTER_TITLE, chapter.getTitle());
                intent.putExtra(ReadChapterActivity.VOLUME_TITLE, volume.getTitle());
                intent.putExtra(ReadChapterActivity.NOVEL_URL, novelUrl);
                activity.startActivity(intent);
            }
        });

        ImageView btnDownload = convertView.findViewById(R.id.chapter_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailNovelViewModel viewModel = activity.getViewModel();
                viewModel.downloadChapter(chapter.getUrl(), chapter.getTitle(), volume.getTitle(), novelUrl);
            }
        });

        layout.addView(convertView);
    }
}
