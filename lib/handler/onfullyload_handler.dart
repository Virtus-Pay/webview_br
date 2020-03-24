
import 'package:flutter/services.dart';
import 'package:webviewbr/handler/webview_handler.dart';

class OnReadyStateChangedHandler implements WebViewHandler {
  final Function(String url) onReadyStateChanged;
  WebViewHandler _next;

  OnReadyStateChangedHandler(this.onReadyStateChanged);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onReadyStateChanged") {
      if(onReadyStateChanged == null) return;
      onReadyStateChanged(methodCall.arguments as String);
    } else if (_next != null) {
      _next.execute(methodCall);
    }
  }

  set next (WebViewHandler webViewHandler) => _next = webViewHandler;
}
