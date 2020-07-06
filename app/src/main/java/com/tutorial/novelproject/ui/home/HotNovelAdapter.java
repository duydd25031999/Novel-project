package com.tutorial.novelproject.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.tutorial.novelproject.ui.novel.DetailNovelActivity;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.model.Novel;

import java.util.List;

public class HotNovelAdapter extends PagerAdapter {
    private Context context;
    private List<Novel> novelList;

    public HotNovelAdapter() {
    }

    public HotNovelAdapter(Context context, List<Novel> novelList) {
        this.context = context;
        this.novelList = novelList;
    }

    @Override
    public int getCount() {
        return novelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.hot_novel_item, container, false);
        ImageView imageView = view.findViewById(R.id.hot_novel_image);
        TextView textView = view.findViewById(R.id.hot_novel_name);
        RelativeLayout relativeLayout = view.findViewById(R.id.hot_novel_layout);

        final Novel novel = novelList.get(position);
        imageView.setImageResource(novel.getImageId());
        textView.setText(novel.getName());
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNovelActivity.class);
                intent.putExtra("imageId", novel.getImageId());
                intent.putExtra("name", novel.getName());
                context.startActivity(intent);
            }
        });


        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
