package com.tutorial.novelproject.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.ui.home.NovelViewList;

import java.util.List;

public class ListFragment extends Fragment {
    private ListNovelViewModel viewModel;
    private NovelViewList novelViewList;
    private NumberPicker pagePicker;
    private Integer maxPage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel(view);
        viewModel.firstNovelCardPage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        Spinner spinner = rootView.findViewById(R.id.list_novel_option);
        ArrayAdapter<CharSequence> optionAdapter = ArrayAdapter.createFromResource(getContext(), R.array.list_novel_options, android.R.layout.simple_spinner_item);
        optionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(optionAdapter);

        return rootView;
    }

    private void initViewModel(View rootView) {
        viewModel = new ViewModelProvider(requireActivity()).get(ListNovelViewModel.class);
        pagePicker = rootView.findViewById(R.id.page_picker);
        ImageView firstPage = rootView.findViewById(R.id.first_page);
        ImageView lastPage = rootView.findViewById(R.id.last_page);

        pagePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                viewModel.changeNovelCardPage(newVal);
            }
        });
        pagePicker.setMinValue(1);

        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.changeNovelCardPage(1);
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.changeNovelCardPage(maxPage);
            }
        });

        GridLayout novelGridView = rootView.findViewById(R.id.grid_novel_list);
        novelViewList = new NovelViewList(novelGridView, getContext());

        ListFragment that = this;

        viewModel.getAllNovelCards().observe(getViewLifecycleOwner(), new Observer<List<NovelCard>>() {
            @Override
            public void onChanged(List<NovelCard> novelCards) {
                novelViewList.listView(novelCards);
            }
        });

        viewModel.getLiveCurrentPage().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentPage) {
                pagePicker.setValue(currentPage);

            }
        });

        viewModel.getLiveMaxPage().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer maxPage) {
                pagePicker.setMaxValue(maxPage);
                that.maxPage = maxPage;
            }
        });
    }
}
