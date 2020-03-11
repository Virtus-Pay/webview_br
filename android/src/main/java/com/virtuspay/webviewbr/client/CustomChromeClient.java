package com.virtuspay.webviewbr.client;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import io.flutter.plugin.common.MethodChannel;

public class CustomChromeClient extends WebChromeClient {
    private final MethodChannel methodChannel;

    public CustomChromeClient(MethodChannel methodChannel) {
        this.methodChannel = methodChannel;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        methodChannel.invokeMethod("onProgressChanged",newProgress);
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public void getVisitedHistory(ValueCallback<String[]> callback) {
        super.getVisitedHistory(callback);
    }
}
