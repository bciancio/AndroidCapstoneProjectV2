package com.bciancio.androidcapstoneprojectv2;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.bciancio.androidcapstoneprojectv2.entity.MySingleton;
import com.bciancio.androidcapstoneprojectv2.entity.Transaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/**
 * Created by student on 12/8/2015.
 */
public class MyTaskHandler extends AsyncTask<String, Void, ArrayList<Transaction>> implements ProjectSettings {

    private Transaction mAddThisTransaction;

    private final String ns = null;

    Activity mActivity;

    public MyTaskHandler() {}

    public MyTaskHandler(Activity activity) {
        mActivity = activity;
    }

    public MyTaskHandler(Activity activity, Transaction addThisTransaction) {
        mActivity = activity;
        mAddThisTransaction = addThisTransaction;
    }


    @Override
    protected void onPostExecute(ArrayList<Transaction> transactions) {
        super.onPostExecute(transactions);
    }

    @Override
    protected ArrayList<Transaction> doInBackground(String... params) {
        logcatThis("doInBackground called");
        String urlString = URL_BASE + params[0];

        InputStream in;
        String result;

        switch (params[0]) {

            case TRANSACTION_DRIVER +  "/" + GET_TEST:

                in = getInputStream(urlString);
                result = getStringFromInputStream(in);
                logcatThis("Result: " + result);
                break;

            case TRANSACTION_DRIVER +  "/" + GET_ALL_TRANSACTIONS:

                in  = getInputStream(urlString);
                ArrayList<Transaction> transactionArrayList = createArrayListFromResponse(in);
                addTransactionArrayToSingleton(transactionArrayList);

                logcatThis("ArrayListSize: " + transactionArrayList.size());
                break;

            case TRANSACTION_DRIVER + "/" + ADD_TRANSACTION:
                in  = getInputStream(""
                                + urlString + "/"
                                + mAddThisTransaction.getType() + "/"
                                + mAddThisTransaction.getFgAmnt() + "/"
                                + mAddThisTransaction.getIggAmnt()
                );


                // TODO set the id of the newly added transaction(current v of service returns a string)
                result = getStringFromInputStream(in);
                if(checkTransactionAdded(result)){
                    MySingleton mySingleton = MySingleton.get(mActivity);
                    mySingleton.addTransactionToSingletonArrayList(mAddThisTransaction);
                }

                logcatThis("Result: " + result);
                break;
        }
        return null;
    }

    private boolean checkTransactionAdded(String result) {
        if (result.toLowerCase().contains("success:")) {
            return true;
        }
        logcatThis("I returned false?!");
        return  false;
    }

    private ArrayList<Transaction> createArrayListFromResponse(InputStream in){
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();

        try {
            // Try to parse the response from the input stream.
            XmlPullParser parser = createXMLParserToUse(in);

            XMLPullParserUsage xmlParserUsage = new XMLPullParserUsage();
            transactionArrayList = xmlParserUsage.readFeed(parser);

            in.close();
        }    catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactionArrayList;
    }

    private void addTransactionArrayToSingleton(ArrayList<Transaction> transactionArrayList) {
        // set the transactionArrayList in the Singleton
        MySingleton mySingleton = MySingleton.get(mActivity);
        mySingleton.setTransactionArrayList(transactionArrayList);
    }

    private InputStream getInputStream(String urlString) {
        URLConnection connection;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(urlString);
            connection = url.openConnection();
            connection.connect();
            // TODO why does the next two lines work?
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

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    public void logcatThis(String message) {
        Log.d("MyDebug", "In MyTaskHandler fragment: " + message);
    }

}

