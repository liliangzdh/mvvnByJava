package com.kaoyaya.tongkai.ui.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.TabEntity;
import com.kaoyaya.tongkai.ui.main.SimpleCardFragment;

import java.util.ArrayList;

public class FlyCoMainActivity extends AppCompatActivity {


    public final String[] titleArr = new String[]{"首页","学习","我的"};
    public final int[] selectIconArr = new int[]{R.mipmap.home_jy,R.mipmap.learn_jy,R.mipmap.my_jy};
    public final int[] unSelectIconArr = new int[]{R.mipmap.home_xx,R.mipmap.lean_xx,R.mipmap.my_xx};

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<CustomTabEntity> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__flyco_main);
        CommonTabLayout commonTabLayout = findViewById(R.id.commonTabLayout);


        for(int i=0;i<titleArr.length;i++){
            arrayList.add(new TabEntity(titleArr[i],selectIconArr[i],unSelectIconArr[i]));
            fragmentArrayList.add(SimpleCardFragment.getInstance(String.valueOf(i)));
        }
        commonTabLayout.setTabData(arrayList,this,R.id.flChange,fragmentArrayList);



    }
}
