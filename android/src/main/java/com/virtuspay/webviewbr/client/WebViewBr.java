package com.virtuspay.webviewbr.client;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebView;

public class WebViewBr extends WebView {
    public WebViewBr(Context context) {
        super(context);
    }

    public WebViewBr(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebViewBr(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WebViewBr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
    }
}
