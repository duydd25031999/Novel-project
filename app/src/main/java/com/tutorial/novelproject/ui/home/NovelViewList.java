package com.tutorial.novelproject.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutorial.novelproject.ui.novel.DetailNovelActivity;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.ui.read.ReadChapterActivity;
import com.tutorial.novelproject.model.NovelCard;

import java.util.List;

public class NovelViewList {
    private GridLayout layout;
    private Context context;
    private LayoutInflater layoutInflater;

    public NovelViewList() {

    }

    public NovelViewList(GridLayout layout, Context context) {
        this.layout = layout;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private View createView(final NovelCard novelCard) {
        View view  = layoutInflater.inflate(R.layout.novel_card_item, layout, false);

        LinearLayout novelCardLayout = view.findViewById(R.id.novel_card_layout);
        LinearLayout lastChapLayout = view.findViewById(R.id.last_chapter_layout);
        ImageView imageView = view.findViewById(R.id.novel_card_image);
        TextView chapterView = view.findViewById(R.id.novel_card_last_chapter);
        TextView volView = view.findViewById(R.id.novel_card_last_vol);
        TextView titleView = view.findViewById(R.id.novel_card_title);

        Glide
            .with(view)
            .load(novelCard.getImageUrl())
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_sync_24)
            .into(imageView);

        chapterView.setText(novelCard.getLastChapterTitle());
        volView.setText(novelCard.getLastVol());
        titleView.setText(novelCard.getTitle());

        novelCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNovelActivity.class);
                intent.putExtra(DetailNovelActivity.NOVEL_URL, novelCard.getUrl());
                context.startActivity(intent);
            }
        });

        lastChapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadChapterActivity.class);
                intent.putExtra(ReadChapterActivity.CHAPTER_URL, novelCard.getLastChapterUrl());
                intent.putExtra(ReadChapterActivity.CHAPTER_TITLE, novelCard.getLastChapterTitle());
                intent.putExtra(ReadChapterActivity.VOLUME_TITLE, novelCard.getLastVol());
                intent.putExtra(ReadChapterActivity.NOVEL_URL, novelCard.getUrl());
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void listView(List<NovelCard> novelCards) {
        layout.removeAllViews();
        for (NovelCard card : novelCards) {
            View view = createView(card);
            layout.addView(view);
        }
    }
}
