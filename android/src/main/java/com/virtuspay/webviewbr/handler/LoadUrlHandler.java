package com.virtuspay.webviewbr.handler;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.virtuspay.webviewbr.WebViewBrPlatformView;

import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;

public class LoadUrlHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public LoadUrlHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(call.method.equalsIgnoreCase("loadUrl")){

            try {
                webView.loadUrl((String) call.argument("url"));

                result.success(null);
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
