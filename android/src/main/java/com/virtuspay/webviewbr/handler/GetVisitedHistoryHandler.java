package com.virtuspay.webviewbr.handler;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

import java.util.Arrays;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class GetVisitedHistoryHandler implements  WebViewHandler{

    private WebViewHandler next;
    final private WebChromeClient webChromeClient;

    public GetVisitedHistoryHandler(WebChromeClient webChromeClient) {
        this.webChromeClient = webChromeClient;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }


    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {
        if(call.method.equalsIgnoreCase("getVisitedHistory")){
            try {
                webChromeClient.getVisitedHistory(new ValueCallback<String[]>() {
                    @Override
                    public void onReceiveValue(String[] value) {
                        Log.d("WEBVIEW",value.toString());
                        result.success(Arrays.asList(value));
                    }
                });
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
