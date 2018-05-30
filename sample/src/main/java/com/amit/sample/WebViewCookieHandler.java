package com.amit.sample;

import android.util.Log;
import android.webkit.CookieManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Amit Jangid on 24,May,2018
 **/
public class WebViewCookieHandler implements CookieJar
{
    private static final String TAG = WebViewCookieHandler.class.getSimpleName();
    private CookieManager webViewCookieManager = CookieManager.getInstance();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        String urlString = url.toString();

        for (Cookie cookie : cookies)
        {
            Log.e(TAG, "saveFromResponse: cookie to save are: " + cookie);
            webViewCookieManager.setCookie(urlString, cookie.toString());
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        String urlString = url.toString();
        String cookiesString = webViewCookieManager.getCookie(urlString);
        Log.e(TAG, "loadForRequest: cookie string got is: " + cookiesString);

        if (cookiesString != null && !cookiesString.isEmpty())
        {
            //We can split on the ';' char as the cookie manager only returns cookies
            //that match the url and haven't expired, so the cookie attributes aren't included
            String[] cookieHeaders = cookiesString.split(";");
            List<Cookie> cookies = new ArrayList<>(cookieHeaders.length);

            for (String header : cookieHeaders)
            {
                cookies.add(Cookie.parse(url, header));
            }

            return cookies;
        }

        return Collections.emptyList();
    }
}
