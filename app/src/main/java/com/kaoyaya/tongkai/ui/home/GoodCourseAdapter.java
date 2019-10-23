package com.kaoyaya.tongkai.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ItemGoodCourse2Binding;
import com.kaoyaya.tongkai.entity.HomeResource;

import java.util.List;

public class GoodCourseAdapter extends RecyclerView.Adapter<GoodCourseAdapter.ViewHolder> {


    private Context context;

    public GoodCourseAdapter(Context context){
        this.context = context;
    }

    private List<HomeResource> list;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemGoodCourse2Binding binding = DataBindingUtil.inflate(inflater, R.layout.item_good_course2, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeResource homeResource = list.get(position);
        Log.e("tag",list.size()+"----->"+position + ";;;pic:"+homeResource.getPictureURL() + "  name:"+homeResource.getName() + " make: "+ homeResource.getRemark());


        Log.e("test","---------start---------"+position);


        for(HomeResource resource:list){
            Log.e("tag",  "  name:"+resource.getName() + " make: "+ resource.getRemark());

        }

        Log.e("test","---------end  ---------"+position);

        ItemGoodCourse2Binding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setItem(homeResource);
    }


    public void refresh(List<HomeResource> homeResourceList){
        this.list = homeResourceList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.e("test","getItemCount"+(list == null?0:list.size()));
        return list == null?0:list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
