package com.kaoyaya.tongkai.test;

import androidx.lifecycle.ViewModel;




public class TestViewModel extends ViewModel {
    boolean loading = false;


    private int num = 0;


    public void setNum(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }

}



