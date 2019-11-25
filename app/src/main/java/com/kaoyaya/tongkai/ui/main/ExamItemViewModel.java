package com.kaoyaya.tongkai.ui.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.utils.SPUtils;
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

            ExamInfo temp = null;

            for (ExamItemViewModel examItemViewModel : examList) {
                ExamInfo examInfo = examItemViewModel.entity.get();
                if (examInfo != null) {
                    examInfo.setSelect(item.getId() == examInfo.getId());
                    // 通知刷新。（ 改变数据源，感觉相当于 adapter 的 notifyDataSetChange)
                    examItemViewModel.entity.notifyChange();
                    if (item.getId() == examInfo.getId()) {
                        temp = examInfo;
                        SPUtils.getInstance().saveExamInfo(examInfo);
                    }
                }
            }
            if (temp != null) {
                Boolean aBoolean = viewModel.isExamInfoEmpty.get();
                //只有第一次选择栏目的时候。才需要发送。
                if (aBoolean != null && aBoolean) {
                    viewModel.isExamInfoEmpty.set(false);
                }
                viewModel.uc.examChange.setValue(true);
            }
        }
    });

    public ExamItemViewModel(@NonNull MainViewModel viewModel, ExamInfo info) {
        super(viewModel);
        entity.set(info);
    }
}
