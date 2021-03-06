import 'dart:async';
import 'dart:io';
import 'package:flutter/services.dart';
import 'package:webviewbr/android_webview_options.dart';
import 'package:webviewbr/disposable.dart';
import 'package:webviewbr/handler/onfullyload_handler.dart';
import 'package:webviewbr/handler/onloadresource_handler.dart';
import 'package:webviewbr/handler/onpagefinished_handler.dart';
import 'package:webviewbr/handler/onpagestarted_handler.dart';
import 'package:webviewbr/handler/onprogress_changed_handler.dart';
import 'package:webviewbr/handler/onreceived_error_handler.dart';
import 'package:webviewbr/handler/webview_handler.dart';

abstract class WebViewService implements Disposable {
  Future<List<String>> getVisitedHistory();

  Future<void> loadUrl(String url, [Map<String, String> headers]);

  Future<void> setOptions(AndroidWebViewOptions options);

  Future<bool> canGoBack();

  Future<bool> canGoForward();

  Future<void> goBack();

  Future<void> goForward();

  Future<void> clearCache(bool includeDiskFiles);

  Future<dynamic> evaluteJavascript(String javascriptCode);

  Future<String> getUrl();

  Future<String> getHtmlContent();

  Future<void> reload();

  Future<bool> hasFocus();

  Future<void> clearFocus();

  Future<void> clearHistory();

  Future<void> goBackOrForward(int steps);

  Future<bool> canGoBackOrForward(int steps);

  Future<File> saveWebArchive(String basename, {bool autoname});

  Future<void> clearSslPreferences();

  //ver o resultado do findAllAsync
  Future<int> findAllAsync(String pattern);

  Future<void> clearView();

  // enable webview lazy loading
  Future<void> enableSlowWholeDocumentDraw();

  //get content height of html document
  Future<int> getContentHeight();

  Future<int> getProgress();

  Future<Uri> getSafeBrowsingPrivacyPolicyUrl();

  Future<String> getTile();

  // invoca picker de zoom
  Future<void> invokeZoomPicker();

  Future<bool> isPrivateBrowsingEnabled();

  //load url by html string
  Future<void> loadData(String data, {String mimeType, String encoding});

  Future<void> loadDataWithBaseURL(String baseUrl, String data,
      {String mimeType, String encoding, String historyUrl});

  Future<bool> pageDown(bool bottom);

  Future<bool> pageTop(bool top);

  Future<void> postUrl(String url);

  Future<void> setBackgroundColor(int color);

  Future<void> setNetworkAvailable(bool networkUp);

  Future<void> setScrollBarStyle(int style);

  Future<void> setWebContentsDebuggingEnabled(bool enabled);

  Future<void> stopLoading();

  Future<void> zoomBy(double zoomFactor);

  Future<bool> zoomIn();

  Future<bool> zoomOut();

  //ver depois
  //Future<SslCertificate> getCertificate();

}

class WebViewController implements WebViewService {
  MethodChannel _methodChannel;
  WebViewHandler _handler;
  final void Function(String url) onPageStarted;
  final void Function(String url) onPageFinished;
  final void Function(int progress) onProgressChanged;
  final void Function(String url) onLoadResource;
  final void Function(int errorCode, String description, String failingUrl)
      onReceiveError;
  final void Function(String readyState) onReadyStateChanged;

  WebViewController(this._methodChannel,
      {this.onProgressChanged,
      this.onLoadResource,
      this.onReceiveError,
      this.onPageStarted,
      this.onPageFinished,
      this.onReadyStateChanged}) {
    _initializeCallBacks();
  }

  void _addOnFullyLoadListener() {
    evaluteJavascript('''
       window.document.addEventListener("readystatechange", (event) => { Android.OnReadyStateChanged(window.document.readyState); }); ''');
  }

  void _addOnFocusOutListener() {
    evaluteJavascript('''
        window.addEventListener("focusout",(event) => {
 Android.OnUnFocusListener(String(!(document.activeElement instanceof HTMLInputElement)));
  }); ''');
  }

  _initializeCallBacks() {
    this._handler = new OnPageFinishedHandler(onPageFinished);
    final onPageStartedHandler = new OnPageStartedHandler((String url) async {
      if (onPageStarted != null) {
        onPageStarted(url);
      }
      _addOnFullyLoadListener();
      _addOnFocusOutListener();
    });
    final onProgressChangedHandler =
        new OnProgressChangedHandler(onProgressChanged);
    final onLoadResourceHandler = new OnLoadResourceHandler(onLoadResource);
    final onReceiveErrorHandler = new OnReceiveErrorHandler(onReceiveError);
    final onReadyStateChangedHandler =
        new OnReadyStateChangedHandler(onReadyStateChanged);

    _handler.next = onPageStartedHandler;
    onPageStartedHandler.next = onProgressChangedHandler;
    onProgressChangedHandler.next = onLoadResourceHandler;
    onLoadResourceHandler.next = onReceiveErrorHandler;
    onReceiveErrorHandler.next = onReadyStateChangedHandler;

    _methodChannel.setMethodCallHandler((call) async {
      print(call.method);
      _handler.execute(call);
    });
  }

  @override
  Future<void> loadUrl(String url, [Map<String, String> headers]) async =>
      _methodChannel.invokeMethod('loadUrl', {"url": url});

  @override
  Future<void> setOptions(AndroidWebViewOptions options) =>
      _methodChannel.invokeMethod('setOptions', {"options": options.toJson()});

  @override
  Future<bool> canGoBack() => _methodChannel.invokeMethod<bool>('canGoBack');

  //VER GO FORWARD COM STEPS
  @override
  Future<bool> canGoForward() =>
      _methodChannel.invokeMethod<bool>('canGoForward');

  @override
  Future<void> goBack() => _methodChannel.invokeMethod('goBack');

  @override
  Future<void> goForward() => _methodChannel.invokeMethod('goForward');

  @override
  Future<void> clearCache(bool includeDiskFiles) =>
      _methodChannel.invokeMethod('clearCache');

  @override
  Future<dynamic> evaluteJavascript(String javascriptCode) => _methodChannel
      .invokeMethod('evaluateJavascript', {"code": javascriptCode});

  @override
  Future<String> getUrl() => _methodChannel.invokeMethod<String>('getUrl');

  @override
  Future<void> reload() => _methodChannel.invokeMethod('reload');

  @override
  Future<bool> hasFocus() => _methodChannel.invokeMethod<bool>('hasFocus');

  @override
  Future<void> clearFocus() => _methodChannel.invokeMethod('clearFocus');

  @override
  Future<void> clearHistory() => _methodChannel.invokeMethod('clearHistory');

  @override
  Future<File> saveWebArchive(String basename, {bool autoname}) {}

  @override
  Future<void> clearSslPreferences() {
    // TODO: implement clearSslPreferences
    throw UnimplementedError();
  }

  @override
  Future<void> clearView() =>
      _methodChannel.invokeMethod('loadUrl', {'url': 'about: blank'});

  @override
  Future<int> findAllAsync(String pattern) async {
    return _methodChannel.invokeMethod('findAllAsync', {'query': pattern});
  }

  @override
  Future<void> enableSlowWholeDocumentDraw() {
    // TODO: implement enableSlowWholeDocumentDraw
    throw UnimplementedError();
  }

  @override
  Future<int> getContentHeight() {
    // TODO: implement getContentHeight
    throw UnimplementedError();
  }

  @override
  Future<int> getProgress() {
    // TODO: implement getProgress
    throw UnimplementedError();
  }

  @override
  Future<Uri> getSafeBrowsingPrivacyPolicyUrl() {
    // TODO: implement getSafeBrowsingPrivacyPolicyUrl
    throw UnimplementedError();
  }

  @override
  Future<String> getTile() {
    // TODO: implement getTile
    throw UnimplementedError();
  }

  @override
  Future<bool> goBackOrForward(int steps) async {
    return _methodChannel
        .invokeMethod<bool>('goBackOrForward', {'steps': steps});
  }

  @override
  Future<void> invokeZoomPicker() {
    // TODO: implement invokeZoomPicker
    throw UnimplementedError();
  }

  @override
  Future<bool> isPrivateBrowsingEnabled() {
    // TODO: implement isPrivateBrowsingEnabled
    throw UnimplementedError();
  }

  @override
  Future<void> loadData(String data, {String mimeType, String encoding}) {
    // TODO: implement loadData
    throw UnimplementedError();
  }

  @override
  Future<void> loadDataWithBaseURL(String baseUrl, String data,
      {String mimeType, String encoding, String historyUrl}) {
    // TODO: implement loadDataWithBaseURL
    throw UnimplementedError();
  }

  @override
  Future<bool> pageDown(bool bottom) {
    // TODO: implement pageDown
    throw UnimplementedError();
  }

  @override
  Future<bool> pageTop(bool top) {
    // TODO: implement pageTop
    throw UnimplementedError();
  }

  @override
  Future<void> postUrl(String url) {
    // TODO: implement postUrl
    throw UnimplementedError();
  }

  @override
  Future<void> setBackgroundColor(int color) {
    // TODO: implement setBackgroundColor
    throw UnimplementedError();
  }

  @override
  Future<void> setNetworkAvailable(bool networkUp) {
    // TODO: implement setNetworkAvailable
    throw UnimplementedError();
  }

  @override
  Future<void> setScrollBarStyle(int style) {
    // TODO: implement setScrollBarStyle
    throw UnimplementedError();
  }

  @override
  Future<void> setWebContentsDebuggingEnabled(bool enabled) {
    // TODO: implement setWebContentsDebuggingEnabled
    throw UnimplementedError();
  }

  @override
  Future<void> stopLoading() => _methodChannel.invokeMethod('stopLoading');

  @override
  Future<void> zoomBy(double zoomFactor) {
    // TODO: implement zoomBy
    throw UnimplementedError();
  }

  @override
  Future<bool> zoomIn() {
    // TODO: implement zoomIn
    throw UnimplementedError();
  }

  @override
  Future<bool> zoomOut() {
    // TODO: implement zoomOut
    throw UnimplementedError();
  }

  @override
  Future<bool> canGoBackOrForward(int steps) async {
    return _methodChannel
        .invokeMethod<bool>('canGoBackOrForward', {"steps": steps});
  }

  @override
  Future<String> getHtmlContent() async {
    final htmlContent = await evaluteJavascript(
        "window.document.getElementsByTagName('html')[0].outerHTML");
    return htmlContent as String;
  }

  @override
  Future<List<String>> getVisitedHistory() async {
    return _methodChannel.invokeListMethod<String>("getVisitedHistory");
  }

  @override
  void dispose() {
    _methodChannel = null;
  }
}
