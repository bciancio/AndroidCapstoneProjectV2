package com.bciancio.androidcapstoneprojectv2;

import android.util.Log;

import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
/**
 * Created by student on 11/17/2015.
 */
public class RestClientUsage {

    public void getTest() {
        RestClient.get("transactionDriver/test", null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logcatThis("failed: " + responseString);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                logcatThis("success: " +responseString);
            }
        });
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In RestClientUsage: " + message);
    }
}