import 'package:flutter/services.dart';

abstract class WebViewHandler {
  void execute(MethodCall methodCall);
  set next(WebViewHandler webViewHandler);
}
