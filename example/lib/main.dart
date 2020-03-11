import 'package:flutter/material.dart';
import 'package:webviewbr/webviewbr.dart';

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({Key key}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  WebViewService _webViewService;

  @override
  void dispose() {
    _webViewService.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          resizeToAvoidBottomInset: false,
          floatingActionButton: FloatingActionButton(onPressed: () async {
            //_webViewService.onPageFinished();
            //  final list = await _webViewService.getVisitedHistory();
            // print(list);
          }),
          appBar: AppBar(
            title: const Text('Flutter TextView example'),
            actions: <Widget>[
              IconButton(
                  icon: Icon(Icons.arrow_back_ios),
                  onPressed: () {
                    _webViewService.goBack();
                  }),
              IconButton(
                  icon: Icon(Icons.arrow_forward_ios),
                  onPressed: () {
                    _webViewService.goForward();
                  })
            ],
          ),
          body: WebViewBr(
            onWebViewCreated: (controller) async {
              _webViewService = controller;
              await controller.setOptions(const AndroidWebViewOptions(
                  javaScriptCanOpenWindowsAutomatically: true,
                  javascriptEnabled: true,
                  supportMultipleWindows: true,
                  domStorageEnabled: true));
              await controller.loadUrl(
                "https://hml.usevirtus.com.br/",
              );

              //  await Future.delayed(Duration(seconds: 15), () async {
              //   await controller.goBack();

              // await controller.reload();
              // final x = await controller.evaluteJavascript('window.document.getElementsByTagName("html")[0].outerHTML');
              // print(x);
              //  final cango = await controller.canGoBack();
              //   print(cango);

              //  final canfo = await controller.canGoForward();

//print(canfo);

              //  await controller.goBack();

              //  await controller.goForward();
              //  });
            },
          )),
    );
  }
}
