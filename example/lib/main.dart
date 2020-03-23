import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:webviewbr/webviewbr.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const WebViewBrExample(),
    );
  }
}

class WebViewBrExample extends StatefulWidget {
  const WebViewBrExample({Key key}) : super(key: key);
  @override
  _WebViewBrExampleState createState() => _WebViewBrExampleState();
}

class _WebViewBrExampleState extends State<WebViewBrExample> {
  WebViewService _webViewService;
  double _progress = 0;

  void dispose() {
    _webViewService.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Flutter WebViewBr example'),
          actions: <Widget>[
            IconButton(
                icon:const Icon(Icons.arrow_back_ios),
                onPressed: () {
                  _webViewService.goBack();
                }),
            IconButton(
                icon:const Icon(Icons.arrow_forward_ios),
                onPressed: () {
                  _webViewService.goForward();
                })
          ],
        ),
        body: Column(
          children: <Widget>[
            Expanded(
              child: WebViewBr(
                onLoadResource: (url) {
                  print("LOADED");
                },
                onProgressChanged: (int progress) {
                  setState(() {
                    _progress = progress / 100;
                  });
                  print(" PROGRESS $progress");
                },
                onReceiveError: (errorCode, description, failingUrl) {
                  print("ERROR");
                },
                onPageFinished: (e) async {
                  print("FINISHED");
                },
                onPageStarted: (e) {
                  print("STARTED");
                },
                onWebViewCreated: (controller) async {
                  _webViewService = controller;
                  await controller.setOptions(const AndroidWebViewOptions(
                      javaScriptCanOpenWindowsAutomatically: true,
                      javaScriptEnabled: true,
                      supportMultipleWindows: true,
                      domStorageEnabled: true,
                      allowContentAccess: true,
                      geolocationEnabled: true,
                      allowFileAccess: true,
                      appCacheEnabled: true,
                      databaseEnabled: true,
                      allowFileAccessFromFileURLs: true,
                      allowUniversalAccessFromFileURLs: true));

                  await controller.loadUrl("https://www.google.com/maps");
                },
              ),
            ),
          ],
        ));
  }
}
