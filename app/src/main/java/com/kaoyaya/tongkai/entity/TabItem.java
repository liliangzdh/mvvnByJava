package com.kaoyaya.tongkai.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kaoyaya.tongkai.R;

import me.majiajie.pagerbottomtabstrip.internal.RoundMessageView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class TabItem extends BaseTabItem {


    private int mDefaultDrawable;
    private int mCheckedDrawable;
    private String title;

    private ImageView mIcon;
    private TextView mTitle;
    private RoundMessageView mMessages;
    View linearLayout;


    private int mDefaultTextColor = 0x56000000;
    private int mCheckedTextColor = 0x56000000;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    /**
     * 方便初始化的方法
     *
     * @param drawableRes        默认状态的图标
     * @param checkedDrawableRes 选中状态的图标
     * @param title              标题
     */
    public void initialize(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, String title) {
        mDefaultDrawable = drawableRes;
        mCheckedDrawable = checkedDrawableRes;
        mTitle.setText(title);
        this.title = title;
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_normal, this, true);

        mIcon = findViewById(R.id.icon);
        mTitle = findViewById(R.id.title);
        mMessages = findViewById(R.id.messages);
        linearLayout = findViewById(R.id.linearLayout);
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
            mIcon.setImageResource(mCheckedDrawable);
            mTitle.setTextColor(mCheckedTextColor);
        } else {
            mIcon.setImageResource(mDefaultDrawable);
            mTitle.setTextColor(mDefaultTextColor);
        }
    }

    @Override
    public void setMessageNumber(int number) {

    }

    @Override
    public void setHasMessage(boolean hasMessage) {

    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);


    }

    @Override
    public void setDefaultDrawable(Drawable drawable) {

    }

    @Override
    public void setSelectedDrawable(Drawable drawable) {

    }

    public void setTextDefaultColor(@ColorInt int color){
        mDefaultTextColor = color;
    }

    public void setTextCheckedColor(@ColorInt int color){
        mCheckedTextColor = color;
    }


    @Override
    public String getTitle() {
        return title;
    }
}
