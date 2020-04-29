package com.kaoyaya.tongkai.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.kaoyaya.tongkai.R;

import java.util.ArrayList;
import java.util.List;

public class StarView extends LinearLayout {
    public StarView(Context context) {
        super(context);
        initView();
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    LinearLayout llView;

    private void initView() {
        View.inflate(getContext(), R.layout.view_star, this);

        llView = findViewById(R.id.llView);


        for (int i = 0; i < llView.getChildCount(); i++) {
            final ImageView image = (ImageView) llView.getChildAt(i);
            image.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = findIndex(v);


                    int selectCount = findSelectCount();
                    // 把0和 最后一个 置灰
                    if (( (index == 0 && selectCount == 1 ) || (index == llView.getChildCount() - 1 && selectCount == 5) )  && v.getTag() != null && (Boolean) v.getTag()) {
                        ((ImageView) v).setImageResource(R.mipmap.star_normal);
                        v.setTag(false);
                        return;
                    }


                    for (int j = 0; j < llView.getChildCount(); j++) {
                        ImageView child = (ImageView) llView.getChildAt(j);
                        child.setTag(j <= index);
                        child.setImageResource(j <= index ? R.mipmap.star_select : R.mipmap.star_normal);
                    }
                }
            });
        }
    }


    private int findIndex(View view) {
        for (int j = 0; j < llView.getChildCount(); j++) {
            if (view == llView.getChildAt(j)) {
                return j;
            }
        }

        return -1;
    }

    public int findSelectCount() {
        int total = 0;
        for (int j = 0; j < llView.getChildCount(); j++) {
            Object tag = llView.getChildAt(j).getTag();
            if (tag != null && (Boolean) tag) {
                total++;
            }
        }

        return total;
    }


}
