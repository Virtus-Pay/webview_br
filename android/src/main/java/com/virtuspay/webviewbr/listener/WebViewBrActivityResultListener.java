package com.virtuspay.webviewbr.listener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.virtuspay.webviewbr.client.CustomChromeClient;

import java.io.ByteArrayOutputStream;

import io.flutter.plugin.common.PluginRegistry;

public class WebViewBrActivityResultListener implements PluginRegistry.ActivityResultListener {
    private CustomChromeClient customChromeClient;

    public WebViewBrActivityResultListener(CustomChromeClient customChromeClient) {
        Log.d("ACTIVITYRESULT CRIADO","CR");
        this.customChromeClient = customChromeClient;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 101){
            Log.d("RESULT CODE",String.valueOf(resultCode));
            Log.d("REQUEST CODE",String.valueOf(requestCode));
            if(resultCode == Activity.RESULT_OK){

                if(intent != null){
                    Log.d("PEGO NA GALERIA","GALERIAAAAAAAAAAAAAAAAAAAAAAA");
                    customChromeClient.receiveUploadUris(new Uri[]{Uri.parse(intent.getDataString())});customChromeClient.setOnReceiveValue(null);
                }else{
                    Log.d("PEGOU NA CAMERA","CAMERAAAAAAAAAAAAAAA");
                    customChromeClient.receiveCameraUri();
              }

            }else if(resultCode == Activity.RESULT_CANCELED){
                Log.d("RESULTADO CANCELADO",String.valueOf(resultCode == Activity.RESULT_CANCELED));
              customChromeClient.receiveUploadUris(new Uri[]{});
            }

            customChromeClient.setOnReceiveValue(null);

            return true;
        } else{
            return false;
        }

    }
}
