package com.li.basemvvm.binding.viewadapter.springview;

import androidx.databinding.BindingAdapter;

import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.view.DefaultHeader;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {

    @BindingAdapter(value = {"enableHeader", "enableFooter", "refreshColorType","onRefreshCommand", "onLoadMoreCommand"}, requireAll = false)
    public static void onRefreshAndLoadMoreCommand(final SpringView springView, Boolean enableHeader, Boolean enableFooter,int refreshColorType, final BindingCommand onRefreshCommand, final BindingCommand onLoadMoreCommand) {

        // 默认是开启的
        springView.setEnableHeader(enableHeader!=null?enableHeader:true);
        springView.setEnableFooter(enableFooter!=null?enableFooter:true);


        springView.setHeader(new DefaultHeader(springView.getContext(),refreshColorType));
        springView.setFooter(new DefaultFooter(springView.getContext()));

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }

            }

            @Override
            public void onLoadmore() {
                if (onLoadMoreCommand != null) {
                    onLoadMoreCommand.execute();
                }
            }
        });
    }

}
