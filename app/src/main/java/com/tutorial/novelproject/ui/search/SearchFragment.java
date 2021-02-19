package com.tutorial.novelproject.ui.search;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.ui.home.NovelViewList;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private List<GerneView> gerneViewList;
    private TextView txtSearch;
    private NovelViewList novelViewList;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FlexboxLayout layout = view.findViewById(R.id.gerne_layout);
        txtSearch = view.findViewById(R.id.txt_search);
        gerneViewList = GerneView.getGerneList(layout, getContext());
        ScrollView scrollView = (ScrollView) view;

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        GridLayout novelGridView = view.findViewById(R.id.grid_search_novel);
        novelViewList = new NovelViewList(novelGridView, getContext());

        viewModel.getAllNovelCards().observe(getViewLifecycleOwner(), new Observer<List<NovelCard>>() {
            @Override
            public void onChanged(List<NovelCard> novelCards) {
                novelViewList.listView(novelCards);
                int y = 2000;
                scrollView.scrollTo(0, y);
            }
        });

        ImageView btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSearch.onEditorAction(EditorInfo.IME_ACTION_DONE);
                String searchStr = txtSearch.getText().toString();
                ArrayList<String> gerneChoose = new ArrayList<String>();
                for (GerneView gerneView : gerneViewList) {
                    String gerne = gerneView.isChoice();
                    if (gerne != null) {
                        gerneChoose.add(gerne);
                    }
                }

                viewModel.searchNovel(searchStr, gerneChoose);
                Toast.makeText(getContext(), "Search success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
