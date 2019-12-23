package com.kaoyaya.tongkai.entity;

public class LiveCommand {

    public int type;

    public int action;

    public boolean hasMore = true;

    public LiveCommand(int type, int action) {
        this.type = type;
        this.action = action;
    }

    public LiveCommand(int type, int action, boolean hasMore) {
        this.type = type;
        this.action = action;
        this.hasMore = hasMore;
    }


    public int getType() {
        return type;
    }

    public int getAction() {
        return action;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}
