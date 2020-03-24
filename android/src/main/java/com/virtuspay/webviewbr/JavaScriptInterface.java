package com.virtuspay.webviewbr;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class JavaScriptInterface {
    private final PluginRegistry.Registrar registrar;
    private final MethodChannel methodChannel;

    JavaScriptInterface(PluginRegistry.Registrar registrar, MethodChannel methodChannel) {
        this.registrar = registrar;
        this.methodChannel = methodChannel;
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @android.webkit.JavascriptInterface
    public void OnUnFocusListener(final String focus) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(focus.equalsIgnoreCase("true")){
                    InputMethodManager imm = (InputMethodManager) registrar.context().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(registrar.activity().getWindow().getDecorView().getApplicationWindowToken(), 0);
                        registrar.activity().getWindow().setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    }
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @android.webkit.JavascriptInterface
    public void OnReadyStateChanged(final String fullyLoaded){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                 methodChannel.invokeMethod("onReadyStateChanged",fullyLoaded);
            }
        });

    }

}


