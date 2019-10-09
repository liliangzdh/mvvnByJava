package com.kaoyaya.tongkai.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("kaoyaya","123456");
        activityMainBinding.setUserInfo(user);




        activityMainBinding.tvUserName.setText("一定牛");
        User userInfo = activityMainBinding.getUserInfo();
        Log.e("test",activityMainBinding.tvUserName.getText().toString() +"   "+userInfo.toString());
        //单向绑定。变量修改，就会自动修改 TextView的text值。
//        activityMainBinding.getUserInfo().setUsername("南昌打游戏");
//        user.setUsername("考试大小");


        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();


        transaction.commit();

    }
}
