import 'package:flutter/services.dart';
import 'package:webviewbr/handler/webview_handler.dart';


class OnPageFinishedHandler implements WebViewHandler {
  final Function(String url) onPageFinished;
  WebViewHandler _next;

  OnPageFinishedHandler(this.onPageFinished);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onPageFinished") {
      if(onPageFinished == null) return;
    
      onPageFinished(methodCall.arguments as String);
    } else if (_next != null) {
      _next.execute(methodCall);
    }
  }

  set next (WebViewHandler webViewHandler) => _next = webViewHandler;
}
