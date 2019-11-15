package com.kaoyaya.tongkai.ui.home;

import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.utils.TimeUtils;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

public class HomeLiveAdapter extends BindingRecyclerViewAdapter<LiveItemViewModel> {


    public SparseArray<CountDownTimer> countDownMap = new SparseArray<>();


    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, final LiveItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);

        final LiveInfo info = item.entity.get();

        info.setFromStartTimeStr(TimeUtils.getInstance().getFormatTimeStr(info.getStart_time()));
        final int time = TimeUtils.getInstance().getTime(info.getStart_time());
        info.setStart(time <= 0);

        CountDownTimer countDownTimer = countDownMap.get(position);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (time > 0) {
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    info.setFromStartTimeStr(TimeUtils.getInstance().getFormatTimeStr(info.getStart_time()));
                    item.entity.notifyChange();
                }

                @Override
                public void onFinish() {
                    info.setStart(true);
                    item.entity.notifyChange();
                }
            }.start();
            countDownMap.append(position, countDownTimer);
        }
        item.entity.notifyChange();
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownMap.size());
        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }
}
