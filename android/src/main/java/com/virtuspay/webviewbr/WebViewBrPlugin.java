package com.virtuspay.webviewbr;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class WebViewBrPlugin {

  public static void registerWith(Registrar registrar) {

    registrar
            .platformViewRegistry()
            .registerViewFactory(
                    "plugins.flutterplatform/webviewbr", new WebViewBrFactory(registrar));
  }
}
