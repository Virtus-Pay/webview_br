import 'package:json_annotation/json_annotation.dart';

part 'android_webview_options.g.dart';

@JsonSerializable(
  createFactory: false
)
class AndroidWebViewOptions {
  const AndroidWebViewOptions(
      {this.javascriptEnabled = false,
      this.javaScriptCanOpenWindowsAutomatically = false,
      this.supportMultipleWindows = false,
      this.allowFileAccess = false,
      this.allowContentAccess,
      this.allowFileAccessFromFileURLs,
      this.allowUniversalAccessFromFileURLs,
      this.appCacheEnabled = false,
      this.appCachePath,
      this.blockNetworkImage,
      this.blockNetworkLoads,
      this.buildInZoomControls,
      this.cacheMode,
      this.cursiveFontFamily,
      this.databaseEnabled,
      this.defaultFixedFontSize,
      this.defaultFontSize,
      this.defaultTextEncodingName,
      this.disabledActionModeMenuItems,
      this.displayZoomControls,
      this.domStorageEnabled = true,
      this.fantasyFontFamily,
      this.fixedFontFamily,
      this.geolocationEnabled = false,
      this.layoutAlgorithm,
      this.loadingImagesAutomatically,
      this.loadWithOverviewMode,
      this.mediaPlayBackRequireUserGesture,
      this.minimumFontSize,
      this.minimumLogicalFontSize,
      this.mixedContentMode,
      this.needInitialFocus,
      this.offscreenPreRaster,
      this.safeBrowsingEnabled,
      this.supportZoom,
      this.textZoom,
      this.userAgentString,
      this.useWideViewPort});

  final bool javascriptEnabled;
  final bool javaScriptCanOpenWindowsAutomatically;
  final bool supportMultipleWindows;
  final bool allowFileAccess;
  final bool allowContentAccess;
  final bool allowFileAccessFromFileURLs;
  final bool allowUniversalAccessFromFileURLs;
  final bool appCacheEnabled;
  final String appCachePath;
  final bool blockNetworkImage;
  final bool blockNetworkLoads;
  final bool buildInZoomControls;
  final int cacheMode;
  final String cursiveFontFamily;
  final bool databaseEnabled;
  final int defaultFixedFontSize;
  final int defaultFontSize;
  final String defaultTextEncodingName;
  final int disabledActionModeMenuItems;
  final bool displayZoomControls;
  final bool domStorageEnabled;
  final String fantasyFontFamily;
  final String fixedFontFamily;
  final bool geolocationEnabled;
  //VER
  final String layoutAlgorithm;
  final bool loadingImagesAutomatically;
  final bool loadWithOverviewMode;
  final bool mediaPlayBackRequireUserGesture;
  final int minimumFontSize;
  final int minimumLogicalFontSize;
  final int mixedContentMode;
  final bool needInitialFocus;
  final bool offscreenPreRaster;
  final bool safeBrowsingEnabled;
  final bool supportZoom;
  final bool textZoom;
  final String userAgentString;
  final bool useWideViewPort;
  
  Map<String, dynamic> toJson() => _$AndroidWebViewOptionsToJson(this);
}
