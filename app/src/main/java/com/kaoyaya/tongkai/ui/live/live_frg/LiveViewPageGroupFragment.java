package com.kaoyaya.tongkai.ui.live.live_frg;

import androidx.fragment.app.Fragment;

import com.kaoyaya.tongkai.ui.base.fragment.BasePagerFragment;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.LivePageFragment;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.TabBar2Fragment;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.LiveBackPageFragment;

import java.util.ArrayList;
import java.util.List;

public class LiveViewPageGroupFragment extends BasePagerFragment {
    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new LivePageFragment());
        list.add(new TabBar2Fragment());
        list.add(new LiveBackPageFragment());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("page1");
        list.add("page2");
        list.add("page3");
        return list;
    }
}
