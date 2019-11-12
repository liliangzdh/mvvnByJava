package com.kaoyaya.tongkai.ui.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kaoyaya.tongkai.entity.ExamInfo;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class ExamItemViewModel extends ItemViewModel<MainViewModel> {


    public ObservableField<ExamInfo> entity = new ObservableField<>();


    public BindingCommand click = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ExamInfo item = entity.get();
            if (item == null) {
                return;
            }
            if (item.isSelect()) {
                return;
            }
            ObservableList<ExamItemViewModel> examList = viewModel.examList;
            for (ExamItemViewModel examItemViewModel : examList) {
                ExamInfo examInfo = examItemViewModel.entity.get();
                if (examInfo != null) {
                    examInfo.setSelect(item.getId() == examInfo.getId());
                    // 通知刷新。（ 改变数据源，感觉相当于 adapter 的 notifyDataSetChange)
                    examItemViewModel.entity.notifyChange();
                }

            }

        }
    });

    public ExamItemViewModel(@NonNull MainViewModel viewModel, ExamInfo info) {
        super(viewModel);
        entity.set(info);
    }
}
