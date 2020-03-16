package com.virtuspay.webviewbr;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import io.flutter.plugin.common.PluginRegistry;

class JavaScriptInterface {
    private final PluginRegistry.Registrar registrar;

    JavaScriptInterface(PluginRegistry.Registrar registrar) {
        this.registrar = registrar;
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
    }


