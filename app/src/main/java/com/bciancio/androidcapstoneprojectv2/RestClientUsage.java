package com.bciancio.androidcapstoneprojectv2;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
/**
 * Created by student on 11/17/2015.
 */
public class RestClientUsage {

    public void getTest() {
        AsyncHttpClient client = new AsyncHttpClient();
        RestClient.get("transactionDriver/test", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                logcatThis("success: ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                logcatThis("failed: ");

            }
        });
  /*          @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                logcatThis("success: " +responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logcatThis("failed: " + responseString);

            }*/

    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In RestClientUsage: " + message);
    }
}


//    // When the response returned by REST has Http response code '200'
//    @Override
//    public void onSuccess(String response) {
//        // Hide Progress Dialog
//        logcatThis(response);
//    }
//    // When the response returned by REST has Http response code other than '200'
//    @Override
//    public void onFailure(int statusCode, Throwable error,
//                          String content) {
//        // When Http response code is '404'
//        if(statusCode == 404){
//            logcatThis("Requested resource not found");
//        }
//        // When Http response code is '500'
//        else if(statusCode == 500){
//            logcatThis("Something went wrong at server end\"");
//
//        }
//        // When Http response code other than 404, 500
//        else{
//            logcatThis("Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]");
//
//        }
//    }
//});