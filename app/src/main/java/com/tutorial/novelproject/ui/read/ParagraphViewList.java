package com.tutorial.novelproject.ui.read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.ChapterContent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphViewList {
    private LinearLayout layout;
    private Context context;
    private LayoutInflater layoutInflater;
    private static final Pattern pattern = Pattern.compile("^--img--\\[(.*)\\]");

    public ParagraphViewList(LinearLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private View createView(String content) {
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String imageUrl = matcher.group(1);
            ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.paragragh_image, layout, false);
            Glide
                .with(context)
                .load(imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_baseline_sync_24)
                .dontTransform()
                .into(imageView);
            return imageView;
        }
        TextView textView = (TextView) layoutInflater.inflate(R.layout.paragraph_item, layout, false);
        textView.setText(content);

        return textView;
    }

    public void listView(List<ChapterContent> contents) {
        layout.removeAllViews();
        for (ChapterContent content : contents) {
            View view = createView(content.getContent());
            layout.addView(view);
        }
    }
}
