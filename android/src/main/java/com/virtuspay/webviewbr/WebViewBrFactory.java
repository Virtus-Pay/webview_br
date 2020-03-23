package com.virtuspay.webviewbr;
import android.content.Context;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class WebViewBrFactory extends PlatformViewFactory {
    private final Registrar registrar;

    public WebViewBrFactory(Registrar registrar) {
        super(StandardMessageCodec.INSTANCE);
        this.registrar = registrar;
    }

    @Override
    public PlatformView create(Context context, int id, Object o) {
        return new WebViewBrPlatformView(registrar, id);
    }
}

/*

 */