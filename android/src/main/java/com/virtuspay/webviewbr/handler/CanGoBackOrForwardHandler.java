package com.virtuspay.webviewbr.handler;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebView;
import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;


public class CanGoBackOrForwardHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public CanGoBackOrForwardHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onMethodCall(MethodCall call, final Result result) {
        if(call.method.equalsIgnoreCase("canGoBackOrForward")){

            try {
                result.success( webView.canGoBackOrForward((int) call.argument("steps")));
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
