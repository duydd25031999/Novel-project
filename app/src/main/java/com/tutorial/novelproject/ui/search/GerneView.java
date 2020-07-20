package com.tutorial.novelproject.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.tutorial.novelproject.R;

import java.util.ArrayList;
import java.util.List;

public class GerneView {
    private CheckBox checkBox;
    private String value;
    private String text;
    private Context context;

    public GerneView(String value, String text, Context context) {
        this.value = value;
        this.text = text;
        this.context = context;
    }

    public void createView(FlexboxLayout layout) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.gerne_item, layout, false);
        checkBox = (CheckBox) convertView;
        checkBox.setText(text);
        layout.addView(checkBox);
    }

    public String isChoice() {
        if (checkBox.isChecked()) {
            return value;
        }
        return null;
    }

    public static List<GerneView> getGerneList(FlexboxLayout layout, Context context) {
        layout.removeAllViews();
        ArrayList<GerneView> list = new ArrayList<GerneView>();

        for (int i = 0; i < gerneNames.length; i++) {
                GerneView gerneView = new GerneView(gerneValues[i], gerneNames[i], context);
                gerneView.createView(layout);
                list.add(gerneView);
        }

        return list;
    }

    private static final String[] gerneNames = {
        "Action",
        "Adapted to Anime",
        "Adapted to Drama CD",
        "Adapted to Manga",
        "Adult",
        "Adventure",
        "Chinese Novel",
        "Comedy",
        "Cooking",
        "Drama",
        "Ecchi",
        "English Novel",
        "Fantasy",
        "Game",
        "Gender Bender",
        "Harem",
        "Historical",
        "Horror",
        "Incest",
        "Isekai",
        "Josei",
        "Korean Novel",
        "Magic",
        "Martial Arts",
        "Mature",
        "Mecha",
        "Military",
        "Mystery",
        "Netorare",
        "One shot",
        "Otome Game",
        "Psychological",
        "Reverse Harem",
        "Romance",
        "School Life",
        "Science Fiction",
        "Seinen",
        "Shoujo",
        "Shoujo ai",
        "Shounen",
        "Shounen ai",
        "Slice of Life",
        "Sports",
        "Super Power",
        "Supernatural",
        "Suspense",
        "Tragedy",
        "Web Novel",
        "Yuri"
    };

    private static final String[] gerneValues = {
        "action",
        "adapted-to-anime",
        "adapted-to-drama-cd",
        "adapted-to-manga",
        "adult",
        "adventure",
        "chinese-novel",
        "comedy",
        "cooking",
        "drama",
        "ecchi",
        "english-novel",
        "fantasy",
        "game",
        "gender-bender",
        "harem",
        "historical",
        "horror",
        "incest",
        "isekai",
        "josei",
        "korean-novel",
        "magic",
        "martial-arts",
        "mature",
        "mecha",
        "military",
        "mystery",
        "netorare",
        "one-shot",
        "otome-game",
        "psychological",
        "reverse-harem",
        "romance",
        "school-life",
        "science-fiction",
        "seinen",
        "shoujo",
        "shoujo-ai",
        "shounen",
        "shounen-ai",
        "slice-of-life",
        "sports",
        "super-power",
        "supernatural",
        "suspense",
        "tragedy",
        "web-novel",
        "yuri"
    };
}
