package com.tutorial.novelproject.ui.novel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.ui.read.ReadChapterActivity;
import com.tutorial.novelproject.model.ChapterRow;
import com.tutorial.novelproject.model.Volume;

import java.util.ArrayList;

public class ChapterListAdapter extends BaseExpandableListAdapter {
    private DetailNovelActivity activity;
    private ArrayList<Volume> volumes;
    private String novelUrl;

    public ChapterListAdapter() {
    }

    public ChapterListAdapter(DetailNovelActivity activity, ArrayList<Volume> volumes, String novelUrl) {
        this.activity = activity;
        this.volumes = volumes;
        this.novelUrl = novelUrl;
    }

    @Override
    public int getGroupCount() {
        return volumes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Volume volume = volumes.get(groupPosition);
        ArrayList chapters = volume.getChapters();
        return chapters.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return volumes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Volume volume = volumes.get(groupPosition);
        ArrayList chapters = volume.getChapters();
        return chapters.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Volume volume = volumes.get(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.chapter_list, parent, false);
        }

        ((TextView) convertView).setText(volume.getTitle());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Volume volume = volumes.get(groupPosition);
        ChapterRow chapter = volume.getChapters().get(childPosition);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.chapter_row, parent, false);
        }

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
                viewModel.downloadChapter(chapter.getUrl());
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
