package com.jesualex.pruebaAgence.Utils;

import android.webkit.WebView;

/**
 * Created by Jesualex.
 */
public class WebViewClient extends android.webkit.WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
