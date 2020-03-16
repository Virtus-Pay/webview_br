package com.virtuspay.webviewbr.client;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class CustomChromeClient extends WebChromeClient {
    private final PluginRegistry.Registrar registrar;
    private final MethodChannel methodChannel;
    private ValueCallback<Uri[]> onReceiveValue;
    public CustomChromeClient(PluginRegistry.Registrar registrar, MethodChannel methodChannel) {
        this.registrar = registrar;
        this.methodChannel = methodChannel;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    public void receiveUploadUris(Uri[] uris) {
        onReceiveValue.onReceiveValue(uris);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        methodChannel.invokeMethod("onProgressChanged",newProgress);
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public void getVisitedHistory(ValueCallback<String[]> callback) {
        super.getVisitedHistory(callback);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        onReceiveValue = filePathCallback;

        Intent intent = fileChooserParams.createIntent();

       // Intent i = new Intent( Intent.ACTION_PICK,
       //         android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


        this.registrar.activity()
                .startActivityForResult(Intent.createChooser(intent,"Share file"),101);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }
}
