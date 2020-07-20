package com.tutorial.novelproject.ui.search;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.tutorial.novelproject.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private List<GerneView> gerneViewList;
    private TextView txtSearch;

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
        ImageView btnSearch= view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = txtSearch.getText().toString();
                ArrayList<String> gerneChoose = new ArrayList<String>();
                for (GerneView gerneView : gerneViewList) {
                    String gerne = gerneView.isChoice();
                    if (gerne != null) {
                        gerneChoose.add(gerne);
                    }
                }
                Toast.makeText(getContext(), "Search success", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }
}
