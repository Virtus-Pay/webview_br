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
    @Override //for APIs 24 and later
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
        if(request.getUrl().toString().contains("faceboook")){
            return false;
        }
        view.loadUrl(request.getUrl().toString());
        return true;
    }
    @Override //for APIs earlier than 24
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        if(url.contains("faceboook")){
            return true;
        }
        view.loadUrl(view.getUrl());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        methodChannel.invokeMethod("onPageStarted",url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
       super.onPageFinished(view,url);
        methodChannel.invokeMethod("onPageFinished",url);
    };

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view,url);
        methodChannel.invokeMethod("onLoadResource",url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        final Map<String,Object> map = new HashMap<>();
        map.put("errorCode",errorCode);
        map.put("description",description);
        map.put("failingUrl",failingUrl);
        methodChannel.invokeMethod("onReceivedError",map);
    }

}
