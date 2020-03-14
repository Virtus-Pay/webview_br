import 'package:json_annotation/json_annotation.dart';

part 'android_webview_options.g.dart';

@JsonSerializable(createFactory: false, includeIfNull: false)
class AndroidWebViewOptions {
  const AndroidWebViewOptions(
      {this.layoutAlgorithm,
      this.renderPriority,
      this.supportZoom = true,
      this.mediaPlaybackRequiresUserGesture = true,
      this.builtInZoomControls = false,
      this.displayZoomControls = true,
      this.allowFileAccess = true,
      this.allowContentAccess = true,
      this.loadWithOverviewMode = false,
      this.enableSmoothTransition = false,
      this.saveFormData = true,
      this.savePassword = true,
      this.textZoom = 40,
      this.lightTouchEnabled = false,
      this.useWideViewPort = false,
      this.supportMultipleWindows = false,
      this.standardFontFamily = "sans-serif",
      this.fixedFontFamily = "monospace",
      this.sansSerifFontFamily = "sans-serif",
      this.serifFontFamily = "sans-serif",
      this.cursiveFontFamily = "cursive",
      this.fantasyFontFamily = "fantasy",
      this.minimumFontSize = 16,
      this.minimumLogicalFontSize = 16,
      this.defaultFontSize = 25,
      this.defaultFixedFontSize = 25,
      this.loadsImagesAutomatically = true,
      this.blockNetworkImage = false,
      this.blockNetworkLoads = false,
      this.javaScriptEnabled = false,
      this.allowUniversalAccessFromFileURLs = true,
      this.allowFileAccessFromFileURLs = true,
      this.setDatabasePath = "",
      this.geolocationDatabasePath = "",
      this.appCacheEnabled = false,
      this.appCachePath = "",
      this.appCacheMaxSize = 100,
      this.databaseEnabled = false,
      this.domStorageEnabled = false,
      this.geolocationEnabled = true,
      this.javaScriptCanOpenWindowsAutomatically = false,
      this.defaultTextEncodingName = "UTF-8",
      this.userAgentString = "",
      this.needInitialFocus = true,
      this.cacheMode = AndroidWebViewOptions.LOAD_DEFAULT,
      this.mixedContentMode = AndroidWebViewOptions.MIXED_CONTENT_NEVER_ALLOW,
      this.offscreenPreRaster = false,
      this.disabledActionModeMenuItems = AndroidWebViewOptions.MENU_ITEM_NONE})
  // asset
  // appCachePath necessita de AppCacheEnabled

  ;

// CACHE MODE CONSTANTS

  /**
     * Default cache usage mode. If the navigation type doesn't impose any
     * specific behavior, use cached resources when they are available
     * and not expired, otherwise load resources from the network.
     * Use with {@link #setCacheMode}.
     */
  static const int LOAD_DEFAULT = -1;

  /**
     * Normal cache usage mode. Use with {@link #setCacheMode}.
     *
     * @deprecated This value is obsolete, as from API level
     * {@link android.os.Build.VERSION_CODES#HONEYCOMB} and onwards it has the
     * same effect as {@link #LOAD_DEFAULT}.
     */
  @deprecated
  static const int LOAD_NORMAL = 0;

  /**
     * Use cached resources when they are available, even if they have expired.
     * Otherwise load resources from the network.
     * Use with {@link #setCacheMode}.
     */
  static const int LOAD_CACHE_ELSE_NETWORK = 1;

  /**
     * Don't use the cache, load from the network.
     * Use with {@link #setCacheMode}.
     */
  static const int LOAD_NO_CACHE = 2;

  /**
     * Don't use the network, load from the cache.
     * Use with {@link #setCacheMode}.
     */
  static const int LOAD_CACHE_ONLY = 3;

// DISABLED ACTION MODE MENU ITEMS CONSTANTS

  static const int MENU_ITEM_NONE = 0;

  /**
     * Used with {@link #setDisabledActionModeMenuItems}.
     *
     * Disable menu item "Share".
     */
  static const int MENU_ITEM_SHARE = 1 << 0;

  /**
     * Used with {@link #setDisabledActionModeMenuItems}.
     *
     * Disable menu item "Web Search".
     */
  static const int MENU_ITEM_WEB_SEARCH = 1 << 1;

  /**
     * Used with {@link #setDisabledActionModeMenuItems}.
     *
     * Disable all the action mode menu items for text processing.
     * By default WebView searches for activities that are able to handle
     * {@link android.content.Intent#ACTION_PROCESS_TEXT} and show them in the
     * action mode menu. If this flag is set via {@link
     * #setDisabledActionModeMenuItems}, these menu items will be disabled.
     */
  static const int MENU_ITEM_PROCESS_TEXT = 1 << 2;

// MIXED CONTENT CONSTANTS

  /**
     * Used with {@link #setMixedContentMode}
     * In this mode, the WebView will allow a secure origin to load content from any other origin,
     * even if that origin is insecure. This is the least secure mode of operation for the WebView,
     * and where possible apps should not set this mode.
     */
  static const int MIXED_CONTENT_ALWAYS_ALLOW = 0;

  /**
     * Used with {@link #setMixedContentMode}
     *
     * In this mode, the WebView will not allow a secure origin to load content from an insecure
     * origin. This is the preferred and most secure mode of operation for the WebView and apps are
     * strongly advised to use this mode.
     */
  static const int MIXED_CONTENT_NEVER_ALLOW = 1;

  /**
     * Used with {@link #setMixedContentMode}
     *
     * In this mode, the WebView will attempt to be compatible with the approach of a modern web
     * browser with regard to mixed content. Some insecure content may be allowed to be loaded by
     * a secure origin and other types of content will be blocked. The types of content are allowed
     * or blocked may change release to release and are not explicitly defined.
     *
     * This mode is intended to be used by apps that are not in control of the content that they
     * render but desire to operate in a reasonably secure environment. For highest security, apps
     * are recommended to use {@link #MIXED_CONTENT_NEVER_ALLOW}.
     */
  static const int MIXED_CONTENT_COMPATIBILITY_MODE = 2;

  // default value is true
  final bool supportZoom;
  // support api level 17
  // default value is true
  final bool mediaPlaybackRequiresUserGesture;
  // support api level 3
  // default value is false
  final bool builtInZoomControls;
  // support api level 11
  // default value is true
  final bool displayZoomControls;
  // support api level 3
  // default value is true
  final bool allowFileAccess;
  // support api level 11
  // default value is true
  final bool allowContentAccess;
  // support api level 7
  // default value is false
  final bool loadWithOverviewMode;
  // support api level 11
  // default value is false
  final bool enableSmoothTransition;
  // default value is true
  final bool saveFormData;
  // default value is true

  @deprecated
  final bool savePassword;
  // support api level 14
  // default value is 100
  final int textZoom;
  // support api level 7
  // default value is 0, max value is 72
  // complete zoom densitiy constants
  //setDefaultZoom(new ZoomDensity.);

  //

  // default value is false
  @deprecated
  final bool lightTouchEnabled;

  // default value is false
  final bool useWideViewPort;

  // default value is false
  final bool supportMultipleWindows;

  // complete layout algorithm with LayoutAlgorithm constants
  //webSettings.setLayoutAlgorithm(new LayoutAlgorithm.);
  final LayoutAlgorithm layoutAlgorithm;

  // default value is"sans-serif"
  final String standardFontFamily;
  // default value is "monospace"
  final String fixedFontFamily;
  // default value is "sans-serif"
  final String sansSerifFontFamily;
  // default value is "sans-serif"
  final String serifFontFamily;
  // default value is "cursive"
  final String cursiveFontFamily;
  // default value is "fantasy"
  final String fantasyFontFamily;
  // default value is 8
  // values are beetwen 1 and 72
  final int minimumFontSize;
  // default value is 8
  // values are beetwen 1 and 72
  final int minimumLogicalFontSize;
  // default value is 16
  // values are beetwen 1 and 72
  final int defaultFontSize;
  // default value is 16
  // values are beetwen 1 and 72
  final int defaultFixedFontSize;
  // default value is true
  final bool loadsImagesAutomatically;
  // default value is false
  final bool blockNetworkImage;

  /*
                  // support api level 8
                   If the application does not have the
                  * {@link android.Manifest.permission#INTERNET} permission, attempts to set
                  * a value of false will cause a {@link java.lang.SecurityException}
                  * to be thrown. The default value is false if the application has the
                  * {@link android.Manifest.permission#INTERNET} permission, otherwise it is
                  * true.
                   */
  final bool blockNetworkLoads;

  // default value is false
  final bool javaScriptEnabled;

  // support api level 16
  /*
                   * The default value is true for API level
                   * {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH_MR1} and below,
                   * and false for API level {@link android.os.Build.VERSION_CODES#JELLY_BEAN}
                   * and above.
                   */
  final bool allowUniversalAccessFromFileURLs;

  // support api level 16
  /*
                   * The default value is true for API level
                   * {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH_MR1} and below,
                   * and false for API level {@link android.os.Build.VERSION_CODES#JELLY_BEAN}
                   * and above.
                   */
  final bool allowFileAccessFromFileURLs;

  // support api level 8
  // complete with PluginState constants
  //webSettings.setPluginState(PluginS);

  // support api level 5
  // deprecated
  @deprecated
  final String setDatabasePath;
  // support api level 5
  // deprecated

  @deprecated
  final String geolocationDatabasePath;
  // support api level 7
  // default value is false
  final bool appCacheEnabled;
  // support api level 7

  final String appCachePath;
  // support api level 7
  // deprecated

  @deprecated
  final int appCacheMaxSize;

  // support api level 5
  // default value is true if the WebView should use the database storage API
  final bool databaseEnabled;

  // support api level 7
  // default value is false
  final bool domStorageEnabled;

  // support api level 5
  // default value is true
  final bool geolocationEnabled;

  // default value is false
  final bool javaScriptCanOpenWindowsAutomatically;

  // default value is "UTF-8".
  final String defaultTextEncodingName;

  // support api level 3
  final String userAgentString;

  // default value is true
  final bool needInitialFocus;

  // complete with RenderPriority constants
  // default value is RenderPriority.NORMAL
  // deprecated

  @deprecated
  final RenderPriority renderPriority;

  // default value is LOAD_DEFAULT
  // complete with cacheMode constants
  final int cacheMode;

  // support api level 21
  // default value is
  /*
                  default to {@link #MIXED_CONTENT_NEVER_ALLOW}.
                   */
  // complete with WebSettings constants
  final int mixedContentMode;

  // support api level 23
  // default value is false
  final bool offscreenPreRaster;

  // support api level 24
  // default value isMENU_ITEM_NONE
  // complete withMENU_ITEM_NONE constants
  final int disabledActionModeMenuItems;

  Map<String, dynamic> toJson() => _$AndroidWebViewOptionsToJson(this);
}

enum LayoutAlgorithm {
  NORMAL,
  /**
         * @deprecated This algorithm is now obsolete.
         */
  @deprecated
  SINGLE_COLUMN,
  /**
         * @deprecated This algorithm is now obsolete.
         */
  @deprecated
  NARROW_COLUMNS,
  TEXT_AUTOSIZING
}
enum RenderPriority { NORMAL, HIGH, LOW }
