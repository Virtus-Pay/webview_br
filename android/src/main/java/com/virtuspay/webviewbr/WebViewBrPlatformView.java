package com.virtuspay.webviewbr;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

import com.virtuspay.webviewbr.handler.CanGoBackHandler;
import com.virtuspay.webviewbr.handler.CanGoForwardHandler;
import com.virtuspay.webviewbr.handler.EvaluateJavascriptHandler;
import com.virtuspay.webviewbr.handler.FindAllAsyncHandler;
import com.virtuspay.webviewbr.handler.GetUrlHandler;
import com.virtuspay.webviewbr.handler.GoBackHandler;
import com.virtuspay.webviewbr.handler.GoForwardHandler;
import com.virtuspay.webviewbr.handler.LoadUrlHandler;
import com.virtuspay.webviewbr.handler.ReloadHandler;
import com.virtuspay.webviewbr.handler.SetOptionsHandler;
import com.virtuspay.webviewbr.handler.WebViewHandler;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.platform.PlatformView;

public class WebViewBrPlatformView implements PlatformView {

    private WebView webView;
    private MethodChannel methodChannel;
    private WebViewHandler webViewHandler;

    public WebViewBrPlatformView(Registrar registrar, int id) {
        initalizeWebView(registrar.activity());
        configureHandlers();
        initializeChannel(registrar.messenger(),id);
    }

    private void initalizeWebView(Context context){
        this.webView = new WebView(context);
        webView.addJavascriptInterface(new JavaScriptInterface(), "android");
    }

    private void initializeChannel(BinaryMessenger messenger,int id){
        this.methodChannel = new MethodChannel(messenger, "plugins.flutterplatform/webviewbr_" + id);
        methodChannel.setMethodCallHandler(webViewHandler);
    }

    private void configureHandlers(){

        WebViewHandler setOptionsWebViewHandler = new SetOptionsHandler(webView);
        WebViewHandler reloadWebViewHandler = new ReloadHandler(webView);
        WebViewHandler evaluateJavascriptHandler = new EvaluateJavascriptHandler(webView);
        WebViewHandler goForwardHandler = new GoForwardHandler(webView);
        WebViewHandler canGoForwardHandler = new CanGoForwardHandler(webView);
        WebViewHandler goBackHandler = new GoBackHandler(webView);
        WebViewHandler canGoBackHandler = new CanGoBackHandler(webView);
        WebViewHandler getUrlHandler = new GetUrlHandler(webView);
        WebViewHandler findAllAsyncHandler = new FindAllAsyncHandler(webView);



        this.webViewHandler = new LoadUrlHandler(webView);
        this.webViewHandler.setNext(setOptionsWebViewHandler);
        setOptionsWebViewHandler.setNext(reloadWebViewHandler);
        reloadWebViewHandler.setNext(evaluateJavascriptHandler);
        evaluateJavascriptHandler.setNext(goForwardHandler);
        goForwardHandler.setNext(canGoForwardHandler);
        canGoForwardHandler.setNext(goBackHandler);
        goBackHandler.setNext(canGoBackHandler);
        canGoBackHandler.setNext(getUrlHandler);
    }

    @Override
    public View getView() {
        return webView;
    }

    @Override
    public void onFlutterViewAttached(View flutterView) {

    }

    @Override
    public void onFlutterViewDetached() {

    }

    @Override
    public void dispose() {
        this.webView.destroy();
        this.webView = null;
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }
}
