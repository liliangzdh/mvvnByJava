package com.kaoyaya.tongkai.ui.home;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ItemHomeLive1Binding;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.utils.TimeUtils;

import java.util.List;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {


    private Context context;
    private List<LiveInfo> liveInfoList;

    private SparseArray<CountDownTimer> countDownMap = new SparseArray<>();

    public LiveAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeLive1Binding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_live1, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final LiveInfo info = liveInfoList.get(position);
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
//                    info.notifyChange();
                }

                @Override
                public void onFinish() {
                    info.setStart(true);
//                    info.notifyChange();
                    Log.e("test", " 倒计时结束： " + info.getLessonTitle());
                }
            }.start();
            countDownMap.append(position, countDownTimer);
        }
        holder.binding.setItem(info);
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }


    @Override
    public int getItemCount() {
        return liveInfoList == null ? 0 : liveInfoList.size();
    }

    public void refresh(List<LiveInfo> liveInfos) {
        this.liveInfoList = liveInfos;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemHomeLive1Binding binding;

        public ViewHolder(ItemHomeLive1Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
