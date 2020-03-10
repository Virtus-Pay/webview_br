package com.virtuspay.webviewbr.client;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import  android.webkit.WebViewClient;


public class CustomWebViewClient extends WebViewClient {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d("webview start loading",url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
    };



    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Log.d("webview error",description);
        Log.d("webview error code",String.valueOf(errorCode));
    }

}
