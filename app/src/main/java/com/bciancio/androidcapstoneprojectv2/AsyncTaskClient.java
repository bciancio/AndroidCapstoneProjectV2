package com.bciancio.androidcapstoneprojectv2;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.bciancio.androidcapstoneprojectv2.entity.MySingleton;
import com.bciancio.androidcapstoneprojectv2.entity.Transaction;
import com.bciancio.androidcapstoneprojectv2.entity.XMLPullParserUsage;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/**
 * Created by student on 12/8/2015.
 */
public class AsyncTaskClient extends AsyncTask<String, Void, ArrayList<Transaction>> implements ProjectSettings {
    private Transaction addThisTransaction;
    private final String ns = null;
    ArrayList<Transaction> mTransactionArrayList;

    Activity mActivity;

    public AsyncTaskClient() {}

    public AsyncTaskClient(Activity activity) {
        mActivity = activity;
    }

    public AsyncTaskClient( Transaction addThisTransaction) {
        this.addThisTransaction = addThisTransaction;
    }

    @Override
    protected void onPostExecute(ArrayList<Transaction> transactions) {
        super.onPostExecute(transactions);
    }

    @Override
    protected ArrayList<Transaction> doInBackground(String... params) {
        logcatThis("doInBackground called");
        String urlString = URL_BASE + params[0];

        InputStream in = getInputStream(urlString);

        switch (params[0]) {
            case TRANSACTION_DRIVER +  "/" + GET_TEST:
                // Parse XML
                try {

                    // Try to parse the response from the input stream.
                    XmlPullParser parser = createXMLParserToUse(in);

                    XMLPullParserUsage xmlParserUsage = new XMLPullParserUsage();
                    mTransactionArrayList = xmlParserUsage.readFeed(parser);

                    logcatThis("size: " + mTransactionArrayList.size() + " First: " +
                            mTransactionArrayList.get(0).getType() + " endTest");

                    in.close();

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TRANSACTION_DRIVER +  "/" + GET_ALL_TRANSACTIONS:

                try {

                    // Try to parse the response from the input stream.
                    XmlPullParser parser = createXMLParserToUse(in);

                    XMLPullParserUsage xmlParserUsage = new XMLPullParserUsage();
                    mTransactionArrayList = xmlParserUsage.readFeed(parser);

                    logcatThis("size: " + mTransactionArrayList.size() + " First: " +
                            mTransactionArrayList.get(0).getType());

                    // set the transactionArrayList in the Singleton
                    MySingleton mySingleton = MySingleton.get(mActivity);
                    mySingleton.setTransactionArrayList(mTransactionArrayList);

                    in.close();

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case TRANSACTION_DRIVER + ADD_TRANSACTION:
                // TODO make a call to rest
                // TODO Use the Transaction variable to pass in necessary data.
                break;
        }
        return null;
    }

    private InputStream getInputStream(String urlString) {
        URLConnection connection;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(urlString);
            connection = url.openConnection();
            connection.connect();
            urlConnection = (HttpURLConnection) url.openConnection();
            return new BufferedInputStream(urlConnection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private XmlPullParser createXMLParserToUse(InputStream in) throws XmlPullParserException {
        XmlPullParserFactory pullParserFactory;
        pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in, null);

        return parser;
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In AsyncTaskClient fragment: " + message);
    }

}

