package com.kaoyaya.tongkai.ui.live.live_frg.vm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class LiveFilterItemViewModel extends ItemViewModel<LiveFragViewModel> {

    public ObservableField<CourseSampleInfo> entity = new ObservableField<>();

    public LiveFilterItemViewModel(@NonNull LiveFragViewModel viewModel, CourseSampleInfo info) {
        super(viewModel);
        entity.set(info);
    }


    public BindingCommand command = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            CourseSampleInfo courseSampleInfo = entity.get();
            viewModel.showDialogEvent.setValue(false);
            if (courseSampleInfo != null) {
                if (courseSampleInfo.isSelect()) {
                    return;
                }
                viewModel.changeClassId(courseSampleInfo);
            }
        }
    });
}
