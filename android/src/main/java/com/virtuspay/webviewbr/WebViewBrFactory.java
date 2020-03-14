package com.virtuspay.webviewbr;
import android.view.View.OnTouchListener;
import android.graphics.Rect;
import android.view.View.OnScrollChangeListener;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnHoverListener;
import android.webkit.DownloadListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.webkit.WebView.PictureListener;
import android.view.ViewOutlineProvider;
import android.view.TouchDelegate;
import android.graphics.drawable.Drawable;
import android.view.View.OnDragListener;
import android.content.res.ColorStateList;
import android.animation.LayoutTransition;
import android.view.View.OnFocusChangeListener;
import android.view.PointerIcon;
import android.animation.StateListAnimator;
import android.webkit.WebView.FindListener;
import android.webkit.WebViewClient;
import android.view.animation.Animation;
import android.webkit.WebChromeClient;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation.AnimationListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnKeyListener;
import android.view.View.OnGenericMotionListener;
import android.view.animation.LayoutAnimationController;
import android.graphics.PorterDuff.Mode;
import android.graphics.Paint;
import android.net.http.SslCertificate;
import android.view.View.OnLongClickListener;
import android.view.View.OnClickListener;

import android.content.Context;
import android.webkit.WebView;

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
