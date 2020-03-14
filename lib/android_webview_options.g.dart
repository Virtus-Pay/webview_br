// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'android_webview_options.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Map<String, dynamic> _$AndroidWebViewOptionsToJson(
    AndroidWebViewOptions instance) {
  final val = <String, dynamic>{};

  void writeNotNull(String key, dynamic value) {
    if (value != null) {
      val[key] = value;
    }
  }

  writeNotNull('supportZoom', instance.supportZoom);
  writeNotNull('mediaPlaybackRequiresUserGesture',
      instance.mediaPlaybackRequiresUserGesture);
  writeNotNull('builtInZoomControls', instance.builtInZoomControls);
  writeNotNull('displayZoomControls', instance.displayZoomControls);
  writeNotNull('allowFileAccess', instance.allowFileAccess);
  writeNotNull('allowContentAccess', instance.allowContentAccess);
  writeNotNull('loadWithOverviewMode', instance.loadWithOverviewMode);
  writeNotNull('enableSmoothTransition', instance.enableSmoothTransition);
  writeNotNull('saveFormData', instance.saveFormData);
  writeNotNull('savePassword', instance.savePassword);
  writeNotNull('textZoom', instance.textZoom);
  writeNotNull('lightTouchEnabled', instance.lightTouchEnabled);
  writeNotNull('useWideViewPort', instance.useWideViewPort);
  writeNotNull('supportMultipleWindows', instance.supportMultipleWindows);
  writeNotNull(
      'layoutAlgorithm', _$LayoutAlgorithmEnumMap[instance.layoutAlgorithm]);
  writeNotNull('standardFontFamily', instance.standardFontFamily);
  writeNotNull('fixedFontFamily', instance.fixedFontFamily);
  writeNotNull('sansSerifFontFamily', instance.sansSerifFontFamily);
  writeNotNull('serifFontFamily', instance.serifFontFamily);
  writeNotNull('cursiveFontFamily', instance.cursiveFontFamily);
  writeNotNull('fantasyFontFamily', instance.fantasyFontFamily);
  writeNotNull('minimumFontSize', instance.minimumFontSize);
  writeNotNull('minimumLogicalFontSize', instance.minimumLogicalFontSize);
  writeNotNull('defaultFontSize', instance.defaultFontSize);
  writeNotNull('defaultFixedFontSize', instance.defaultFixedFontSize);
  writeNotNull('loadsImagesAutomatically', instance.loadsImagesAutomatically);
  writeNotNull('blockNetworkImage', instance.blockNetworkImage);
  writeNotNull('blockNetworkLoads', instance.blockNetworkLoads);
  writeNotNull('javaScriptEnabled', instance.javaScriptEnabled);
  writeNotNull('allowUniversalAccessFromFileURLs',
      instance.allowUniversalAccessFromFileURLs);
  writeNotNull(
      'allowFileAccessFromFileURLs', instance.allowFileAccessFromFileURLs);
  writeNotNull('setDatabasePath', instance.setDatabasePath);
  writeNotNull('geolocationDatabasePath', instance.geolocationDatabasePath);
  writeNotNull('appCacheEnabled', instance.appCacheEnabled);
  writeNotNull('appCachePath', instance.appCachePath);
  writeNotNull('appCacheMaxSize', instance.appCacheMaxSize);
  writeNotNull('databaseEnabled', instance.databaseEnabled);
  writeNotNull('domStorageEnabled', instance.domStorageEnabled);
  writeNotNull('geolocationEnabled', instance.geolocationEnabled);
  writeNotNull('javaScriptCanOpenWindowsAutomatically',
      instance.javaScriptCanOpenWindowsAutomatically);
  writeNotNull('defaultTextEncodingName', instance.defaultTextEncodingName);
  writeNotNull('userAgentString', instance.userAgentString);
  writeNotNull('needInitialFocus', instance.needInitialFocus);
  writeNotNull(
      'renderPriority', _$RenderPriorityEnumMap[instance.renderPriority]);
  writeNotNull('cacheMode', instance.cacheMode);
  writeNotNull('mixedContentMode', instance.mixedContentMode);
  writeNotNull('offscreenPreRaster', instance.offscreenPreRaster);
  writeNotNull(
      'disabledActionModeMenuItems', instance.disabledActionModeMenuItems);
  return val;
}

const _$LayoutAlgorithmEnumMap = {
  LayoutAlgorithm.NORMAL: 'NORMAL',
  LayoutAlgorithm.SINGLE_COLUMN: 'SINGLE_COLUMN',
  LayoutAlgorithm.NARROW_COLUMNS: 'NARROW_COLUMNS',
  LayoutAlgorithm.TEXT_AUTOSIZING: 'TEXT_AUTOSIZING',
};

const _$RenderPriorityEnumMap = {
  RenderPriority.NORMAL: 'NORMAL',
  RenderPriority.HIGH: 'HIGH',
  RenderPriority.LOW: 'LOW',
};
