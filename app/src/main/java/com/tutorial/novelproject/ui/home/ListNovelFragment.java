package com.tutorial.novelproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.NovelCard;

import java.util.List;


public class ListNovelFragment extends Fragment {
    private HomeViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel(view);
        viewModel.firstNovelCardPage();
    }

    private void initViewModel(View rootView) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        GridLayout gridNovelList = rootView.findViewById(R.id.grid_novel_list);
        NovelViewList novelViewList = new NovelViewList(gridNovelList, getContext());

        ViewPager viewPager = rootView.findViewById(R.id.hot_novel_pager);

        viewModel.getAllNovelCards().observe(getViewLifecycleOwner(), new Observer<List<NovelCard>>() {
            @Override
            public void onChanged(List<NovelCard> novelCards) {
                novelViewList.listView(novelCards);
                List<NovelCard> hotNovels = novelCards.subList(0, 3);
                HotNovelAdapter hotNovelAdapter = new HotNovelAdapter(getContext(), hotNovels);
                viewPager.setAdapter(hotNovelAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_novel, container, false);
        return rootView;
    }
}
