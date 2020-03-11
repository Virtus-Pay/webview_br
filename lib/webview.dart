import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:webviewbr/webview_controller.dart';
export  'android_webview_options.dart';

typedef void WebViewCreatedCallback(WebViewService controller);

class WebViewBr extends StatefulWidget {
  const WebViewBr({
    Key key,
    this.onWebViewCreated,
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
        MethodChannel('plugins.flutterplatform/webviewbr_$id')));
  }
}
