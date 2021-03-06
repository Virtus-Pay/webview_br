package com.virtuspay.webviewbr.handler;

import android.webkit.WebView;
import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;


public class ReloadHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public ReloadHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(call.method.equalsIgnoreCase("reload")){

            try {

                webView.reload();

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
