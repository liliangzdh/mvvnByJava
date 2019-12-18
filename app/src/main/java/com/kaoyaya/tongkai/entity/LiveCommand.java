package com.kaoyaya.tongkai.entity;

public class LiveCommand {

    public int type;

    public int action;

    public LiveCommand(int type, int action) {
        this.type = type;
        this.action = action;
    }


    public int getType() {
        return type;
    }

    public int getAction() {
        return action;
    }
}
