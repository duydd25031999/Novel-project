package com.tutorial.novelproject.ui.read;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutorial.novelproject.R;

import java.util.List;

public class ParagraphViewList {
    private LinearLayout layout;
    private Context context;
    private LayoutInflater layoutInflater;

    public ParagraphViewList(LinearLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private TextView createView(String content) {
        TextView view = (TextView) layoutInflater.inflate(R.layout.paragraph_item, layout, false);
        view.setText(content);

        return view;
    }

    public void listView(List<String> listParagraph) {
        layout.removeAllViews();
        for (String paragragh : listParagraph) {
            TextView view = createView(paragragh);
            layout.addView(view);
        }
    }
}
