package com.virtuspay.webviewbr.handler;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class FindAllAsyncHandler implements  WebViewHandler{
    private WebViewHandler next;
    private final WebView webView;


    public FindAllAsyncHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {
        if(call.method.equalsIgnoreCase("findAllAsync")){

            try {
                final int matches = 0;
                webView.findAllAsync( (String) call.argument("query"));

                webView.setFindListener(new WebView.FindListener() {
                    @Override
                    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
                      if (isDoneCounting) {
                            result.success(numberOfMatches);
                            webView.setFindListener(null);
                      }
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
