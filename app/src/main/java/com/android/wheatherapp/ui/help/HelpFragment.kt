package com.android.wheatherapp.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.android.wheatherapp.R
import kotlinx.android.synthetic.main.fragment_help.*


class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_help, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupwebview()
    }

    private fun setupwebview() {
        wv_tutorial.settings.javaScriptEnabled = true
        wv_tutorial.webViewClient = WebViewClient()
        wv_tutorial.loadUrl("https://youtu.be/xnAROo3wnag")
    }
}