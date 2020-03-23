
package com.virtuspay.webviewbr.client;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class CustomChromeClient extends WebChromeClient implements  PluginRegistry.RequestPermissionsResultListener {
    private final PluginRegistry.Registrar registrar;
    private final MethodChannel methodChannel;


    private ValueCallback<Uri[]> onReceiveValue;
    private GeolocationPermissions.Callback onLocationReceived;
    private String locationOrigin;
    private Intent uploadIntent;
    private Uri photoURI;

    public CustomChromeClient(PluginRegistry.Registrar registrar, MethodChannel methodChannel) {
        this.registrar = registrar;
        this.methodChannel = methodChannel;
    }

    public void setOnReceiveValue(ValueCallback<Uri[]> onReceiveValue) {
        this.onReceiveValue = onReceiveValue;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    public void receiveUploadUris(Uri[] uris) {
        Log.d("RECEIVEUPLOADS CHAMADO","SENDO CHAMADO");
        if(this.onReceiveValue == null || uris == null){
            Log.d("ONRECEIVEUPLOAD NULL","TA NULL");
          return;
        }
        Log.d("RECEIVEUPLOADS OK","TAAAAAAAAAAAAAAA OK ");
        this.onReceiveValue.onReceiveValue(uris);
    }

    public void receiveCameraUri(){
        if(photoURI != null ){
            Log.d("PHOTO URI", String.valueOf(photoURI));
            receiveUploadUris(new Uri[]{photoURI});
        }

    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public void receiveLocation(){
        if(this.onLocationReceived == null){
            Log.d("ONRECEIVELOCATION NULL","TA NULL");
            return;
        }
        this.onLocationReceived.invoke(locationOrigin, true, false);
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
    public void onPermissionRequest(PermissionRequest request) {
        request.grant(request.getResources());

    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        Toast.makeText(registrar.activity(),"Cancelado pelo usuário",Toast.LENGTH_LONG);
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
    }




    private boolean requestPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int MY_CAMERA_REQUEST_CODE = 100;
            boolean granted = registrar.activity().checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
            if (!granted) {
                registrar.activity().requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
                return false;
            }

        }
        return  true;
    }


    private Intent getPickIntent(boolean allowCamera,boolean allowGallery,String[] acceptedTypes,Uri fileUri) {
        final List<Intent> intents = new ArrayList<Intent>();


        if (allowGallery) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, acceptedTypes);
            intents.add(galleryIntent);
        }

        if (allowCamera) {
            setCameraIntents(intents,fileUri);
        }

        if (intents.isEmpty()) return null;
        Intent result = Intent.createChooser(intents.remove(0), null);
        if (!intents.isEmpty()) {
            result.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[] {}));
           // result.putExtra(Intent.EXTRA_MIME_TYPES, acceptedTypes);
        }


        result.putExtra(Intent.EXTRA_MIME_TYPES, acceptedTypes);

        return result;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private File createImageFile() throws IOException {
        final String timeStamp = String.valueOf(new Date().getTime());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",  /* suffix */
                registrar.activity().getExternalFilesDir(Environment.DIRECTORY_PICTURES) /* directory */
        );
      photoURI = Uri.fromFile(image);

        return image;
    }



    @TargetApi(Build.VERSION_CODES.DONUT)
    private void setCameraIntents(List<Intent> cameraIntents,Uri fileUri) {
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        final PackageManager packageManager = registrar.activity().getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            cameraIntents.add(intent);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Intent selectIntent(FileChooserParams fileChooserParams) {

        Log.d("TIPOS DE ARQUIVO",Arrays.toString(fileChooserParams.getAcceptTypes()));
        Log.d("CAPTURA HABILITADA",String.valueOf(fileChooserParams.isCaptureEnabled()));

       Intent intent;

       boolean any = false;

       for (String type: fileChooserParams.getAcceptTypes()){
           any = type.contains("image");
       }

        if(any){
             File image;

            try {
                image = createImageFile();

            }catch (IOException e){
                image = null;
            }

            Uri photoURI = FileProvider.getUriForFile(registrar.activity(),
                   registrar.activity().getPackageName() + ".webview_br.provider",
                    image);



          intent = getPickIntent(true,true,new String[]{"image/png"},photoURI);
        }else{
           intent = Intent.createChooser(fileChooserParams.createIntent(),null);
           intent.putExtra(Intent.EXTRA_MIME_TYPES, fileChooserParams.getAcceptTypes());
        }
        return intent;
    }

    public void startUpload(){

       this.registrar.activity()
               .startActivityForResult(uploadIntent,101);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        this.onReceiveValue = filePathCallback;
        this.uploadIntent = selectIntent(fileChooserParams);


        Log.d("TAMANHO",String.valueOf(fileChooserParams.getAcceptTypes().length));
        Log.d("TIPO DE ARQUIVO", Arrays.toString(fileChooserParams.getAcceptTypes()));

        boolean permissionGranted = requestPermission();
        if(permissionGranted){
            startUpload();
        }
        return true;
    }



    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        this.locationOrigin = origin;
        this.onLocationReceived = callback;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int MY_LOCATION_REQUEST_CODE = 105;
            final boolean granted = registrar.activity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && registrar.activity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            if (!granted) {
                registrar.activity().requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        },
                        MY_LOCATION_REQUEST_CODE);
            }
        }
    }


    @Override
    public boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e("PERMISSÃO DNV","PERMISSÃO AAAAAAAA");

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startUpload();
            }
        }else if(requestCode == 105){
            receiveLocation();
        }
        return true;
    }
}
