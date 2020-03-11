package com.virtuspay.webviewbr.handler;

import android.webkit.WebView;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class OnPageFinishHandler implements  WebViewHandler {
    final private WebView webView;
    private WebViewHandler next;

    public OnPageFinishHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
      this.next = webViewHandler;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if(call.method.equalsIgnoreCase("onPageFinish")){

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
