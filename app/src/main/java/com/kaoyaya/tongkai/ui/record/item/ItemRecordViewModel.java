package com.kaoyaya.tongkai.ui.record.item;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.CourseInfo;
import com.kaoyaya.tongkai.ui.record.RecordCourseViewModel;
import com.li.basemvvm.base.ItemViewModel;

public class ItemRecordViewModel extends ItemViewModel<RecordCourseViewModel> {


    public ObservableField<CourseInfo> entity = new ObservableField<>();

    public ItemRecordViewModel(@NonNull RecordCourseViewModel viewModel, CourseInfo info ) {
        super(viewModel);
        entity.set(info);
    }
}
