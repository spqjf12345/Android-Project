package com.example.webviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //자바 스크립트 허용
        webView.settings.javaScriptEnabled = true
        //웹 뷰에서 새 창이 뜨지 않도록 방지하는 구문
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        //링크 주소를 load 함
        webView.loadUrl("https://www.naver.com")

    }
    //백버튼 로직
    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }

    }
}