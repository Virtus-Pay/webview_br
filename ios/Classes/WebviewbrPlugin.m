#import "WebviewbrPlugin.h"
#if __has_include(<webviewbr/webviewbr-Swift.h>)
#import <webviewbr/webviewbr-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "webviewbr-Swift.h"
#endif

@implementation WebviewbrPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftWebviewbrPlugin registerWithRegistrar:registrar];
}
@end
