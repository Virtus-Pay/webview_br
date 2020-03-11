package com.virtuspay.webviewbr;

<<<<<<< HEAD
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.virtuspay.webviewbr.client.CustomChromeClient;
import com.virtuspay.webviewbr.client.CustomWebViewClient;
import com.virtuspay.webviewbr.handler.CanGoBackHandler;
import com.virtuspay.webviewbr.handler.CanGoBackOrForwardHandler;
import com.virtuspay.webviewbr.handler.CanGoForwardHandler;
import com.virtuspay.webviewbr.handler.ClearCacheHandler;
import com.virtuspay.webviewbr.handler.ClearFocusHandler;
import com.virtuspay.webviewbr.handler.ClearHistoryHandler;
import com.virtuspay.webviewbr.handler.EvaluateJavascriptHandler;
import com.virtuspay.webviewbr.handler.FindAllAsyncHandler;
import com.virtuspay.webviewbr.handler.GetUrlHandler;
import com.virtuspay.webviewbr.handler.GetVisitedHistoryHandler;
import com.virtuspay.webviewbr.handler.GoBackHandler;
import com.virtuspay.webviewbr.handler.GoForwardHandler;
import com.virtuspay.webviewbr.handler.HasFocusHandler;
=======
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
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
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
<<<<<<< HEAD
    private WebChromeClient webChromeClient;
    private WebViewClient webViewClient;
=======
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
    private MethodChannel methodChannel;
    private WebViewHandler webViewHandler;

    public WebViewBrPlatformView(Registrar registrar, int id) {
        initalizeWebView(registrar.activity());
        configureHandlers();
        initializeChannel(registrar.messenger(),id);
<<<<<<< HEAD
        configureClients();
    }

    @SuppressLint("AddJavascriptInterface")
    private void initalizeWebView(Context context){
        this.webView = new WebView(context);
        webView.addJavascriptInterface(new JavaScriptInterface(), "android");

    }

    private void configureClients(){
        this.webViewClient = new CustomWebViewClient(methodChannel);
        this.webChromeClient = new CustomChromeClient(methodChannel);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
=======
    }

    private void initalizeWebView(Context context){
        this.webView = new WebView(context);
        webView.addJavascriptInterface(new JavaScriptInterface(), "android");
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
    }

    private void initializeChannel(BinaryMessenger messenger,int id){
        this.methodChannel = new MethodChannel(messenger, "plugins.flutterplatform/webviewbr_" + id);
        methodChannel.setMethodCallHandler(webViewHandler);
    }

    private void configureHandlers(){

<<<<<<< HEAD
        this.webViewHandler = new LoadUrlHandler(webView);
=======
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
        WebViewHandler setOptionsWebViewHandler = new SetOptionsHandler(webView);
        WebViewHandler reloadWebViewHandler = new ReloadHandler(webView);
        WebViewHandler evaluateJavascriptHandler = new EvaluateJavascriptHandler(webView);
        WebViewHandler goForwardHandler = new GoForwardHandler(webView);
        WebViewHandler canGoForwardHandler = new CanGoForwardHandler(webView);
        WebViewHandler goBackHandler = new GoBackHandler(webView);
        WebViewHandler canGoBackHandler = new CanGoBackHandler(webView);
        WebViewHandler getUrlHandler = new GetUrlHandler(webView);
        WebViewHandler findAllAsyncHandler = new FindAllAsyncHandler(webView);
<<<<<<< HEAD
        WebViewHandler canGoBackOrForwardHandler = new CanGoBackOrForwardHandler(webView);
        WebViewHandler clearCacheHandler = new ClearCacheHandler(webView);
        WebViewHandler clearFocusHandler = new ClearFocusHandler(webView);
        WebViewHandler clearHistoryHandler = new ClearHistoryHandler(webView);
        WebViewHandler hasFocusHandler = new HasFocusHandler(webView);
        WebViewHandler reloadHandler = new ReloadHandler(webView);
        WebViewHandler getVisitedHistoryHandler = new GetVisitedHistoryHandler(webChromeClient);


=======



        this.webViewHandler = new LoadUrlHandler(webView);
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
        this.webViewHandler.setNext(setOptionsWebViewHandler);
        setOptionsWebViewHandler.setNext(reloadWebViewHandler);
        reloadWebViewHandler.setNext(evaluateJavascriptHandler);
        evaluateJavascriptHandler.setNext(goForwardHandler);
        goForwardHandler.setNext(canGoForwardHandler);
        canGoForwardHandler.setNext(goBackHandler);
        goBackHandler.setNext(canGoBackHandler);
        canGoBackHandler.setNext(getUrlHandler);
<<<<<<< HEAD
        getUrlHandler.setNext(findAllAsyncHandler);
        findAllAsyncHandler.setNext(canGoBackOrForwardHandler);
        canGoBackOrForwardHandler.setNext(clearCacheHandler);
        clearCacheHandler.setNext(clearFocusHandler);
        clearFocusHandler.setNext(clearHistoryHandler);
        clearHistoryHandler.setNext(hasFocusHandler);
        hasFocusHandler.setNext(reloadHandler);
        reloadHandler.setNext(getVisitedHistoryHandler);

=======
>>>>>>> 6a7086f57887abf047d4d63feb81232deb6d4619
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
