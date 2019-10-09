package com.kaoyaya.tongkai.test;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.kaoyaya.tongkai.BR;

public class Good extends BaseObservable {


    private String name;

    private String detail;


    public Good(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        //只更新本字段
        notifyPropertyChanged(BR.name);
    }


    public String getDetail() {
        return detail;
    }

    @Bindable
    public void setDetail(String detail) {
        this.detail = detail;
        notifyPropertyChanged(BR.detail);
    }


    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

