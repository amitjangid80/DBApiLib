package com.amit.sample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    private static final String TAG = WebViewActivity.class.getSimpleName();

    private WebView webViewAmul;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    private OkHttpClient client;

    private String url = "http://agdisk.com/SupAppNew/#/login", currentUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_web_view);

        webViewAmul = findViewById(R.id.webViewAmul);
        progressBar = findViewById(R.id.progressBar);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);

        WebSettings settings = webViewAmul.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webViewAmul.loadUrl(url);
        settings.setPluginState(WebSettings.PluginState.ON);
        webViewAmul.setWebViewClient(new AmulWebViewClient());

        client = new OkHttpClient.Builder()
                .cookieJar(new WebViewCookieHandler())
                .build();
    }

    class AmulWebViewClient extends WebViewClient
    {
        AmulWebViewClient()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            final Uri uri = Uri.parse(url);
            currentUrl = url;
            Log.e(TAG, "shouldOverrideUrlLoading: deprecated method current url is: " + currentUrl);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
        {
            webViewAmul.loadUrl(request.getUrl().toString());
            currentUrl = request.getUrl().toString();
            Log.e(TAG, "shouldOverrideUrlLoading: API 24 method current url is: " + currentUrl);
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            try
            {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);

                currentUrl = url;
                Log.e(TAG, "onPageFinished: current url is: " + currentUrl);

                HttpUrl httpUrl = HttpUrl.parse(url);
                List<Cookie> cookieJarsList = client.cookieJar().loadForRequest(httpUrl);
                client.cookieJar().saveFromResponse(httpUrl, cookieJarsList);
                Log.e(TAG, "onPageFinished: cookie jar returned is: " + cookieJarsList);

                if (cookieJarsList != null && cookieJarsList.size() != 0)
                {
                    for (int i = 0; i < cookieJarsList.size(); i++)
                    {
                        String loginStatus = cookieJarsList.get(i).value();
                        Log.e(TAG, "onPageFinished: cookie value from url is: " + loginStatus);

                        if (loginStatus == null || loginStatus.equalsIgnoreCase("null"))
                        {
                            startActivity(new Intent(WebViewActivity.this, ViewDataActivity.class));
                            finish();
                            return;
                        }
                    }
                }
                else
                {
                    startActivity(new Intent(WebViewActivity.this, ViewDataActivity.class));
                    finish();
                }
            }
            catch (Exception e)
            {
                Log.e(TAG, "onPageFinished: exception occurred.", e);
            }
        }
    }

    @Override
    public void onRefresh()
    {
        refreshLayout.setRefreshing(true);
        webViewAmul.reload();
    }
}
