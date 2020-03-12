import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:webviewbr/webview_controller.dart';
export 'android_webview_options.dart';

typedef void WebViewCreatedCallback(WebViewService controller);

class WebViewBr extends StatefulWidget {
  final Function(String url) onPageStarted;
  final Function(String url) onPageFinished;
  final void Function(int progress) onProgressChanged;
  final void Function(String url) onLoadResource;
  final void Function(int errorCode, String description, String failingUrl)
      onReceiveError;

  const WebViewBr({
    Key key,
    this.onWebViewCreated,
    this.onPageStarted,
    this.onPageFinished,
    this.onProgressChanged,
    this.onLoadResource,
    this.onReceiveError,
  }) : super(key: key);

  final WebViewCreatedCallback onWebViewCreated;

  @override
  State<StatefulWidget> createState() => _WebViewBrState();
}

class _WebViewBrState extends State<WebViewBr> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'plugins.flutterplatform/webviewbr',
        onPlatformViewCreated: _onPlatformViewCreated,
      );
    }
    return Text(
        '$defaultTargetPlatform is not yet supported by the text_view plugin');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onWebViewCreated == null) {
      return;
    }
    widget.onWebViewCreated(new WebViewController(
        MethodChannel('plugins.flutterplatform/webviewbr_$id'),
        onPageStarted: widget.onPageStarted,
        onPageFinished: widget.onPageFinished,
        onLoadResource: widget.onLoadResource,
        onProgressChanged: widget.onProgressChanged,
        onReceiveError: widget.onReceiveError));
  }
}
