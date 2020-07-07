package com.tutorial.novelproject.ui.download;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.ui.read.ReadChapterActivity;
import com.tutorial.novelproject.database.ChapterDatabase2;
import com.tutorial.novelproject.model.ChapterDetail;

import java.util.ArrayList;

public class DownloadFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = view.findViewById(R.id.download_list);

        ChapterDatabase2 chapterDatabase2 = new ChapterDatabase2(getContext(), null);
        ArrayList<ChapterDetail> chapterDetails = chapterDatabase2.getAllDowloaded();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (ChapterDetail chapterDetail : chapterDetails) {
            View newRow = createRow(chapterDetail, layoutInflater, linearLayout);
            linearLayout.addView(newRow);
        }
    }

    public View createRow(ChapterDetail chapterDetail, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        View view  = layoutInflater.inflate(R.layout.download_row, linearLayout, false);
        ((TextView) view).setText(chapterDetail.getUrl());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReadChapterActivity.class);
                intent.putExtra(ReadChapterActivity.CHAPTER_URL, chapterDetail.getUrl());
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_download, container, false);
        return rootView;
    }
}
