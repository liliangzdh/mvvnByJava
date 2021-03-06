package com.li.basemvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.li.basemvvm.R;
import com.liaoinstan.springview.container.BaseSimpleHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Administrator on 2016/3/21.
 */
public class DefaultHeader extends BaseSimpleHeader {
    private Context context;
    private int rotationSrc;
    private int arrowSrc;

    private long freshTime;

    private final int ROTATE_ANIM_DURATION = 180;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;

    private TextView headerTitle;
    private TextView headerTime;
    private ImageView headerArrow;
    private ProgressBar headerProgressbar;

    private int textColor = R.color.color_77;

    public DefaultHeader(Context context) {
        this(context, R.drawable.progress_small, R.drawable.arrow);
    }


    public DefaultHeader(Context context, int colorType) {
        this(context, R.drawable.progress_small, R.drawable.arrow);

        if (colorType == 1) {
            textColor = R.color.white;
        }
    }

    public DefaultHeader(Context context, int rotationSrc, int arrowSrc) {
        setType(SpringView.Type.FOLLOW);
        setMovePara(2.0f);
        this.context = context;
        this.rotationSrc = rotationSrc;
        this.arrowSrc = arrowSrc;

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.default_header, viewGroup, false);
        headerTitle = view.findViewById(R.id.default_header_title);
        headerTime = view.findViewById(R.id.default_header_time);
        headerArrow = view.findViewById(R.id.default_header_arrow);
        headerProgressbar = view.findViewById(R.id.default_header_progressbar);
        headerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        headerArrow.setImageResource(arrowSrc);

        TextView lastTimeText = view.findViewById(R.id.last_time_text);

        //
        headerTitle.setTextColor(context.getResources().getColor(textColor));
        headerTime.setTextColor(context.getResources().getColor(textColor));
        lastTimeText.setTextColor(context.getResources().getColor(textColor));

        if(textColor != R.color.color_77){
            headerArrow.setColorFilter(context.getResources().getColor(textColor));
        }
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
        if (freshTime == 0) {
            freshTime = System.currentTimeMillis();
        } else {
            int m = (int) ((System.currentTimeMillis() - freshTime) / 1000 / 60);
            if (m >= 1 && m < 60) {
                headerTime.setText(m + "分钟前");
            } else if (m >= 60 && m < 60 * 24) {
                int h = m / 60;
                headerTime.setText(h + "小时前");
            } else if (m >= 60 * 24) {
                int d = m / (60 * 24);
                headerTime.setText(d + "天前");
            } else if (m == 0) {
                headerTime.setText("刚刚");
            }
        }
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown) {
            headerTitle.setText("松开刷新");
            if (headerArrow.getVisibility() == View.VISIBLE)
                headerArrow.startAnimation(mRotateUpAnim);
        } else {
            headerTitle.setText("下拉刷新");
            if (headerArrow.getVisibility() == View.VISIBLE)
                headerArrow.startAnimation(mRotateDownAnim);
        }
    }

    @Override
    public void onStartAnim() {
        freshTime = System.currentTimeMillis();
        headerTitle.setText("正在刷新");
        headerArrow.setVisibility(View.INVISIBLE);
        headerArrow.clearAnimation();
        headerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        headerArrow.setVisibility(View.VISIBLE);
        headerProgressbar.setVisibility(View.INVISIBLE);
    }
}