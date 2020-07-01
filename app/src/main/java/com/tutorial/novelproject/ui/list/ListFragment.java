package com.tutorial.novelproject.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.NovelCard;
import com.tutorial.novelproject.ui.listnovel.NovelViewList;
import com.tutorial.novelproject.utils.ApiCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    private NovelViewList novelViewList;
    private NumberPicker pagePicker;
    private Integer maxPage;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GridLayout novelGridView = view.findViewById(R.id.grid_novel_list);
        novelViewList = new NovelViewList(novelGridView, getContext());
        pagePicker = view.findViewById(R.id.page_picker);
        maxPage = 0;

        ApiCaller apiCaller = new ApiCaller();
        apiCaller.getAllNovelCard(this, this, getContext());
        super.onViewCreated(view, savedInstanceState);
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

        NumberPicker pagePicker = rootView.findViewById(R.id.page_picker);
        ImageView firstPage = rootView.findViewById(R.id.first_page);
        ImageView lastPage = rootView.findViewById(R.id.last_page);

        final ListFragment that = this;
        pagePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.getAllNovelCardWithPage(newVal, that, that, getContext());

            }
        });
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.getAllNovelCardWithPage(1, that, that, getContext());
            }
        });
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCaller apiCaller = new ApiCaller();
                apiCaller.getAllNovelCardWithPage(maxPage, that, that, getContext());
            }
        });

        return rootView;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            int currentPage = response.getInt("currentPage");
            int maxPage = response.getInt("maxPage");
            pagePicker.setMaxValue(maxPage);
            pagePicker.setMinValue(1);
            pagePicker.setValue(currentPage);
            this.maxPage = maxPage;

            ArrayList<NovelCard> novelCardArrayList = new ArrayList<NovelCard>();
            JSONArray booksJson = response.getJSONArray("books");
            for (int i = 0; i < booksJson.length(); i++) {
                NovelCard novelCard = NovelCard.createFromJson(booksJson.getJSONObject(i));
                novelCardArrayList.add(novelCard);
            }
            novelViewList.listView(novelCardArrayList);

        } catch (JSONException e) {
            Log.e("json parser", e.getMessage());
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("get response", error.getMessage());
    }
}
