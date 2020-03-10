package com.virtuspay.webviewbr.handler;

import io.flutter.plugin.common.MethodCall;

import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import static io.flutter.plugin.common.MethodChannel.Result;

public interface WebViewHandler extends MethodCallHandler {

    void setNext(WebViewHandler webViewHandler);
}





