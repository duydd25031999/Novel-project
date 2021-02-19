package com.tutorial.novelproject.ui.download;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.Chapter;
import com.tutorial.novelproject.ui.read.ReadChapterActivity;
import java.util.List;

public class DownloadFragment extends Fragment {
    private DownloadedChapterViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = view.findViewById(R.id.download_list);

        viewModel = new ViewModelProvider(requireActivity()).get(DownloadedChapterViewModel.class);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        viewModel.getLiveListDownloadedChapters().observe(getViewLifecycleOwner(), new Observer<List<Chapter>>() {
            @Override
            public void onChanged(List<Chapter> chapters) {
                for (Chapter chapter : chapters) {
                    View newRow = createRow(chapter, layoutInflater, linearLayout);
                    linearLayout.addView(newRow);
                }
            }
        });
    }

    public View createRow(Chapter chapter, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        View view  = layoutInflater.inflate(R.layout.download_row, linearLayout, false);
        ((TextView) view).setText(chapter.getVolume() + "\n" + chapter.getTitle() );
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReadChapterActivity.class);
                intent.putExtra(ReadChapterActivity.CHAPTER_URL, chapter.getUrl());
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
