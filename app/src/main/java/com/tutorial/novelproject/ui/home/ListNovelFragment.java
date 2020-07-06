package com.tutorial.novelproject.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.Novel;

import java.util.ArrayList;


public class ListNovelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_novel, container, false);

        ViewPager novelViewPager = rootView.findViewById(R.id.hot_novel_pager);
        ArrayList<Novel> listHotNovel = new ArrayList<Novel>();
        for (int i = 0; i < 10; i++) {
            listHotNovel.add(new Novel(R.drawable.ic_launcher_background, "Novel  rat nhieu chu" + i));
        }
        HotNovelAdapter hotNovelAdapter = new HotNovelAdapter(getContext(), listHotNovel);
        novelViewPager.setAdapter(hotNovelAdapter);

        return rootView;
    }
}
