package Utils;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

public class WebViewClient extends android.webkit.WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
