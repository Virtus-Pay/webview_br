package com.virtuspay.webviewbr.client;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import  android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;


public class CustomWebViewClient extends WebViewClient {

    private final MethodChannel methodChannel;

    public CustomWebViewClient(MethodChannel methodChannel) {
        this.methodChannel = methodChannel;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        methodChannel.invokeMethod("onPageStarted",url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        methodChannel.invokeMethod("onPageFinished",url);
    };

    @Override
    public void onLoadResource(WebView view, String url) {
        methodChannel.invokeMethod("onPageLoadResource",url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        final Map<String,Object> map = new HashMap<>();
        map.put("errorCode",errorCode);
        map.put("description",description);
        map.put("failingUrl",failingUrl);
        methodChannel.invokeMethod("onReceivedError",map);
    }

}
