package com.virtuspay.webviewbr.handler.activity;

import android.app.Activity;
import android.content.Intent;

public class PickFileHandler implements  ActivityHandler{
    private final Activity activity;

    public PickFileHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void handle(int requestCode, int resultCode, Intent intent) {

    }

    @Override
    public void setNext(ActivityHandler next) {

    }
}
