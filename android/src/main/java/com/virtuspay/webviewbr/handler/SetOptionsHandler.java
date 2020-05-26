package com.virtuspay.webviewbr.handler;
import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Map;
import io.flutter.plugin.common.MethodCall;
import static io.flutter.plugin.common.MethodChannel.Result;


public class SetOptionsHandler implements WebViewHandler {
    private WebViewHandler next;
    final private WebView webView;

    public SetOptionsHandler(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void setNext(WebViewHandler webViewHandler) {
        this.next = webViewHandler;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(call.method.equalsIgnoreCase("setOptions")){

            try {
                final Map<String,Object> options =(Map<String,Object>) call.argument("options");

                final WebSettings webSettings = webView.getSettings();

                // default value is true
                webSettings.setSupportZoom((boolean) options.get("supportZoom"));
                // support api level 17
                // default value is true
                webSettings.setMediaPlaybackRequiresUserGesture((boolean) options.get("mediaPlaybackRequiresUserGesture"));
                // support api level 3
                // default value is false
                webSettings.setBuiltInZoomControls((boolean) options.get("builtInZoomControls"));
                // support api level 11
                // default value is true
                webSettings.setDisplayZoomControls((boolean) options.get("displayZoomControls"));
                // support api level 3
                // default value is true
                webSettings.setAllowFileAccess((boolean) options.get("allowFileAccess"));
                // support api level 11
                // default value is true
                webSettings.setAllowContentAccess((boolean) options.get("allowContentAccess"));
                // support api level 7
                // default value is false
                webSettings.setLoadWithOverviewMode((boolean) options.get("loadWithOverviewMode"));
                // support api level 11
                // default value is false
                webSettings.setEnableSmoothTransition((boolean) options.get("enableSmoothTransition"));
                // default value is true
                webSettings.setSaveFormData((boolean) options.get("saveFormData"));
                // default value is true
                webSettings.setSavePassword((boolean) options.get("savePassword"));
                // support api level 14
                // default value is 100
               // webSettings.setTextZoom((int) options.get("textZoom"));
                // support api level 7
                // default value is 0, max value is 72
                // complete zoom densitiy constants
               // webSettings.setDefaultZoom(new ZoomDensity.);

                //

                // default value is false
                webSettings.setLightTouchEnabled((boolean) options.get("lightTouchEnabled"));

                // default value is false
                webSettings.setUseWideViewPort((boolean) options.get("useWideViewPort"));

                // default value is false
                webSettings.setSupportMultipleWindows((boolean) options.get("supportMultipleWindows"));

                // complete layout algorithm with LayoutAlgorithm constants
                //webSettings.setLayoutAlgorithm(new LayoutAlgorithm.);

                // default value is"sans-serif"
                webSettings.setStandardFontFamily((String) options.get("standardFontFamily"));

                // default value is "monospace"
                webSettings.setFixedFontFamily((String) options.get("fixedFontFamily"));

                // default value is "sans-serif"
                webSettings.setSansSerifFontFamily((String) options.get("sansSerifFontFamily"));

                // default value is "sans-serif"
                webSettings.setSerifFontFamily((String) options.get("serifFontFamily"));

                // default value is "cursive"
                webSettings.setCursiveFontFamily((String) options.get("cursiveFontFamily"));

                // default value is "fantasy"
                webSettings.setFantasyFontFamily((String) options.get("fantasyFontFamily"));

                // default value is 8
                // values are beetwen 1 and 72
                //webSettings.setMinimumFontSize((int) options.get("minimumFontSize"));
                // default value is 8
                // values are beetwen 1 and 72
              //  webSettings.setMinimumLogicalFontSize((int) options.get("minimumLogicalFontSize"));
                // default value is 16
                // values are beetwen 1 and 72
               // webSettings.setDefaultFontSize((int) options.get("defaultFontSize"));
                // default value is 16
                // values are beetwen 1 and 72
               // webSettings.setDefaultFixedFontSize((int) options.get("defaultFixedFontSize"));
                // default value is true
                webSettings.setLoadsImagesAutomatically((boolean) options.get("loadsImagesAutomatically"));
                // default value is false
                webSettings.setBlockNetworkImage((boolean) options.get("blockNetworkImage"));

                /*

                // support api level 8
                 If the application does not have the
                * {@link android.Manifest.permission#INTERNET} permission, attempts to set
                * a value of false will cause a {@link java.lang.SecurityException}
                * to be thrown. The default value is false if the application has the
                * {@link android.Manifest.permission#INTERNET} permission, otherwise it is
                * true.
                 */
                webSettings.setBlockNetworkLoads((boolean) options.get("blockNetworkLoads"));

                // default value is false
                webSettings.setJavaScriptEnabled((boolean) options.get("javaScriptEnabled"));

                // support api level 16
                /*
                 * The default value is true for API level
                 * {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH_MR1} and below,
                 * and false for API level {@link android.os.Build.VERSION_CODES#JELLY_BEAN}
                 * and above.
                 */
                webSettings.setAllowUniversalAccessFromFileURLs((boolean) options.get("allowUniversalAccessFromFileURLs"));

                // support api level 16
                /*
                 * The default value is true for API level
                 * {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH_MR1} and below,
                 * and false for API level {@link android.os.Build.VERSION_CODES#JELLY_BEAN}
                 * and above.
                 */
                webSettings.setAllowFileAccessFromFileURLs((boolean) options.get("allowFileAccessFromFileURLs"));

                // support api level 8
                // complete with PluginState constants
                //webSettings.setPluginState(PluginS);

                // support api level 5
                // deprecated
                webSettings.setDatabasePath((String) options.get("databasePath"));
                // support api level 5
                // deprecated
                webSettings.setGeolocationDatabasePath((String) options.get("geolocationDatabasePath"));
                // support api level 7
                // default value is false
                webSettings.setAppCacheEnabled((boolean) options.get("appCacheEnabled"));
                // support api level 7

                webSettings.setAppCachePath((String) options.get("appCachePath"));
                // support api level 7
                // deprecated
                webSettings.setAppCacheMaxSize(0L);

                // support api level 5
                // default value is true if the WebView should use the database storage API
                webSettings.setDatabaseEnabled(false);

                // support api level 7
                // default value is false
                webSettings.setDomStorageEnabled((boolean) options.get("domStorageEnabled"));

                // support api level 5
                // default value is true
                webSettings.setGeolocationEnabled((boolean) options.get("geolocationEnabled"));

                // default value is false
                webSettings.setJavaScriptCanOpenWindowsAutomatically((boolean) options.get("javaScriptCanOpenWindowsAutomatically"));

                // default value is "UTF-8".
                webSettings.setDefaultTextEncodingName((String) options.get("defaultTextEncodingName"));

                // support api level 3
                webSettings.setUserAgentString((String) options.get("userAgentString"));

                // default value is true
                webSettings.setNeedInitialFocus((boolean) options.get("needInitialFocus"));

                // complete with RenderPriority constants
                // default value is RenderPriority.NORMAL
                // deprecated
                //webSettings.setRenderPriority(RenderPriority.HIGH);


                // default value is LOAD_DEFAULT
                // complete with cacheMode constants
                webSettings.setCacheMode((int) options.get("cacheMode"));

                // support api level 21
                // default value is
                /*
                default to {@link #MIXED_CONTENT_NEVER_ALLOW}.
                 */
                // complete with WebSettings constants
                webSettings.setMixedContentMode((int) options.get("mixedContentMode"));

                // support api level 23
                // default value is false
                webSettings.setOffscreenPreRaster((boolean) options.get("offscreenPreRaster"));

                // support api level 24
                // default value is WebSettings.MENU_ITEM_NONE
                // complete with WebSettings.MENU_ITEM_NONE constants
                webSettings.setDisabledActionModeMenuItems((int) options.get("disabledActionModeMenuItems"));

                result.success(null);
            }catch (Exception e){
                result.error(null,e.getMessage(),null);
            }


        }else if(next != null){
            next.onMethodCall(call,result);
        }else{
            result.notImplemented();
        }
    }
}
