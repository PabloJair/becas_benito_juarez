package com.s10plus.feature_home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.s10plus.core_application.base_ui.BaseFragment
import com.s10plus.core_application.ui.dialog.TypeDialog
import com.s10plus.feature_home.databinding.FragmentWebviewBinding


class FragmentWebView:BaseFragment<FragmentWebviewBinding>(R.layout.fragment_webview){

    lateinit var url:String

    override fun setupView() {

        url = requireArguments().getString(URL, "")

        binding.webview.settings.javaScriptEnabled = true


        activity.showLoader()
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onLoadResource(view: WebView, url: String) {
            }

            override fun onPageFinished(view: WebView, url: String) {
                activity.hideLoader()

            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                binding.webview.visibility = View.GONE
                activity.showDialog(TypeDialog.ERROR,
                    "Error de conexión",
                    "Conectate a una red para cargar esta sección",
                    cancel = null,ok = {
                        activity.onBackPressed()
                        it.dismiss()
                    }
                )
            }
        }
        binding.webview.loadUrl(url)

    }

    override fun setupObserver() {
    }

    override fun setupViewModel() {
    }

    override fun init() {
    }
    companion object{
    const val URL ="URL"
    fun newInstance(url: String): FragmentWebView {
        val fragment = FragmentWebView()
        val args = Bundle().also {

            it.putString(URL, url)
        }

        fragment.arguments = args
        return fragment
    }

}
}