package com.kaoyaya.tongkai.utils;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class IndicatorLineUtil {


    /**
     * 调节tablayout指示线宽度
     *
     * @param tabLayout
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(TabLayout tabLayout, int leftDip, int rightDip) {
        try {
            Field slidingTabIndicatorField = tabLayout.getClass().getDeclaredField("slidingTabIndicator");
            slidingTabIndicatorField.setAccessible(true);
            LinearLayout mTabStrip = (LinearLayout) slidingTabIndicatorField.get(tabLayout);
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);
                Field textViewField = tabView.getClass().getDeclaredField("textView");
                textViewField.setAccessible(true);
                TextView mTextView = (TextView) textViewField.get(tabView);
                tabView.setPadding(0, 0, 0, 0);
                int width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width;

                int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
                int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

                params.leftMargin = left;
                params.rightMargin = right;
                tabView.setLayoutParams(params);
                tabView.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
