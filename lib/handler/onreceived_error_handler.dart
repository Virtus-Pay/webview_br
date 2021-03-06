import 'package:flutter/services.dart';
import 'package:webviewbr/handler/webview_handler.dart';

class OnReceiveErrorHandler implements WebViewHandler {
  final Function(int errorCode, String description, String failingUrl)
      onReceivedError;
  WebViewHandler _next;

  OnReceiveErrorHandler(this.onReceivedError);

  @override
  void execute(MethodCall methodCall) {
    if (methodCall.method == "onReceiveError") {
      if(onReceivedError == null) return;
      final arguments = methodCall.arguments as Map<String, dynamic>;
      onReceivedError(arguments["errorCode"], arguments["description"],
          arguments["failingUrl"]);
    } else if (_next != null) {
      _next.execute(methodCall);
    }
  }

  set next(WebViewHandler webViewHandler) => _next = webViewHandler;
}
