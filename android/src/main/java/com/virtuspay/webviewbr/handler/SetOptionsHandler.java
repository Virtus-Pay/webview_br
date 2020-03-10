package com.virtuspay.webviewbr.handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Map;
import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;


public class SetOptionsHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public SetOptionsHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(call.method.equalsIgnoreCase("setOptions")){

            try {
                final Map<String,Object> options =(Map<String,Object>) call.argument("options");

                final WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptCanOpenWindowsAutomatically((boolean) options.get("javaScriptCanOpenWindowsAutomatically"));
                webSettings.setJavaScriptEnabled((boolean) options.get("javascriptEnabled"));
                webSettings.setSupportMultipleWindows((boolean) options.get("supportMultipleWindows"));
                webSettings.setDomStorageEnabled((boolean) options.get("domStorageEnabled"));

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
