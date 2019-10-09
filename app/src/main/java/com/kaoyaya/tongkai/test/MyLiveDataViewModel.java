package com.kaoyaya.tongkai.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyLiveDataViewModel extends ViewModel {

    private MutableLiveData<Integer> mValue;


    public MutableLiveData<Integer> getValue(){
        if (mValue == null){
            mValue = new MutableLiveData<>();
        }
        return mValue;
    }

    public Integer  getIndexValue(){
        return getValue().getValue();
    }

    public void setValue(Integer i){
        getValue().setValue(i);
    }


    public void postValue(Integer mValue){
        if(this.mValue == null){
            return ;
        }
        this.mValue.postValue(mValue);
    }


}
