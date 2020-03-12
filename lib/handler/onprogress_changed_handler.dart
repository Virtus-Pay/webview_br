import 'package:flutter/services.dart';
import 'package:webviewbr/handler/webview_handler.dart';

class OnProgressChangedHandler implements WebViewHandler {
  final Function(int progress) onProgressChanged;
  WebViewHandler _next;

  OnProgressChangedHandler(this.onProgressChanged);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onProgressChanged") {
      onProgressChanged(methodCall.arguments as int);
    } else if (_next != null) {
      _next.execute(methodCall);
    } else {
      throw UnimplementedError("This call is not implemented");
    }
  }

  set next (WebViewHandler webViewHandler) => _next = webViewHandler;
}
