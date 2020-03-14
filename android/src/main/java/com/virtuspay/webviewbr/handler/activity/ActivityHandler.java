package com.virtuspay.webviewbr.handler.activity;

import android.content.Intent;

public interface ActivityHandler {
    void handle(int requestCode, int resultCode, Intent intent);
    void setNext(ActivityHandler next);
}
