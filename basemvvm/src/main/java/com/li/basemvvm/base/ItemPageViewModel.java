package com.li.basemvvm.base;


import androidx.annotation.NonNull;

public class ItemPageViewModel<VM extends ItemViewModel> {
    protected VM itemViewModel;

    public ItemPageViewModel(@NonNull VM itemViewModel) {
        this.itemViewModel = itemViewModel;
    }
}

