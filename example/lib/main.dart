import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';
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
        floatingActionButton: FloatingActionButton(onPressed: () {}),
        appBar: AppBar(title: const Text('Flutter TextView example')),
        body: WebViewBr(
         onWebViewCreated: (web) async {
           _webViewService = web;
            await web.setOptions(const AndroidWebViewOptions(
                javaScriptCanOpenWindowsAutomatically: true,
                javascriptEnabled: true,
                supportMultipleWindows: true,
                domStorageEnabled: true
            ));
            await web.loadUrl(
                "https://refactoring.guru/",
              );
          //  await Future.delayed(Duration(seconds: 15), () async {
           //   await web.goBack();

              // await web.reload();
              // final x = await web.evaluteJavascript('window.document.getElementsByTagName("html")[0].outerHTML');
              // print(x);
              //  final cango = await web.canGoBack();
              //   print(cango);

              //  final canfo = await web.canGoForward();

//print(canfo);


              //  await web.goBack();

              //  await web.goForward();
          //  });
          },
        )
    ),
    );
  }
}
