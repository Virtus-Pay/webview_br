package com.virtuspay.webviewbr.handler;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebView;
import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;


public class GetUrlHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public GetUrlHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onMethodCall(MethodCall call, final Result result) {
        if(call.method.equalsIgnoreCase("getUrl")){

            try {
                result.success( webView.getUrl());

            }catch (Exception e){
                result.error(null,e.getMessage(),null);
            }

        }else if(next != null){
            next.onMethodCall(call,result);
        }else{
            result.notImplemented();
        }
    }
}
