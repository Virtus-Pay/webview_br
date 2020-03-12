
import 'package:flutter/src/services/message_codec.dart';
import 'package:webviewbr/handler/webview_handler.dart';

class OnPageStartedHandler implements WebViewHandler{
  final Function(String url) onPageStarted;
  WebViewHandler _next;

  OnPageStartedHandler(this.onPageStarted);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onPageStarted") {
      onPageStarted(methodCall.arguments as String);
    } else if (_next != null) {
      _next.execute(methodCall);
    } else {
      throw UnimplementedError("This call is not implemented");
    }
  }

  @override
  set next(WebViewHandler webViewHandler) => _next = webViewHandler;
}