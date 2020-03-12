import 'package:flutter/services.dart';
import 'package:webviewbr/handler/webview_handler.dart';

class OnLoadResourceHandler implements WebViewHandler {
  final Function(String url) onLoadResource;
  WebViewHandler _next;

  OnLoadResourceHandler(this.onLoadResource);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onLoadResource") {
      onLoadResource(methodCall.arguments as String);
    } else if (_next != null) {
      _next.execute(methodCall);
    } else {
      throw UnimplementedError("This call is not implemented");
    }
  }

  set next (WebViewHandler webViewHandler) => _next = webViewHandler;
}
