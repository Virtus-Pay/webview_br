package com.virtuspay.webviewbr.listener;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.virtuspay.webviewbr.client.CustomChromeClient;

import io.flutter.plugin.common.PluginRegistry;

public class WebViewBrActivityResultListener implements PluginRegistry.ActivityResultListener {
    private final CustomChromeClient customChromeClient;

    public WebViewBrActivityResultListener(CustomChromeClient customChromeClient) {
        this.customChromeClient = customChromeClient;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("OLHA ISSOOOOOOOOOOO",intent.getDataString());
        if(requestCode == 101){
            customChromeClient.receiveUploadUris(new Uri[]{Uri.parse(intent.getDataString())});
            return true;
        }
        return false;
    }
}
