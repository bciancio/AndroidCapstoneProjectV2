package com.bciancio.androidcapstoneprojectv2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bciancio.androidcapstoneprojectv2.entity.Transaction;

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
 * Created by student on 11/17/2015.
 */
public class TransactionsListFragment extends Fragment {
    ArrayList<Transaction> mTransactionArrayList;
    String mUrlBase = "https://damp-mesa-6637.herokuapp.com/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wireUpWidgets();
        populateData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.transactions_list_layout, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initalizeListView();
    }

    public void initalizeListView() {

        RecyclerView mRecyclerView = (RecyclerView)getActivity().findViewById(R.id
                .recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Transaction> aTransactionArrayList = new ArrayList<>();

        aTransactionArrayList.add(new Transaction(1,"sold",100,100));
        aTransactionArrayList.add(new Transaction(2,"sold",111,111));
        aTransactionArrayList.add(new Transaction(3,"sold",222,222));
        aTransactionArrayList.add(new Transaction(4,"purchased",333,333));

        // TODO set myDataset == Transactions object - modify MyAdapter after
        // specify an adapter (see also next example)
//        MyAdapter mAdapter = new MyAdapter(aTransactionArrayList, getActivity());
//        mRecyclerView.setAdapter(mAdapter);

        MyAdapter mAdapter = new MyAdapter(aTransactionArrayList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
    public  void wireUpWidgets() {
        // Perform magic and make the fabulous floating action bar disappear!
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public void populateData() {
//        MyTask myTask = new MyTask();
//        myTask.execute(mUrlBase + "transactionDriver/getAllTransactions");
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In TransactionsList fragment: " + message);
    }


    public class  MyTask extends AsyncTask<String, Void, ArrayList<Transaction>> {

        private final String ns = null;



        @Override
        protected void onPostExecute(ArrayList<Transaction> transactions) {
           // mTransactionArrayList = transactions;
            //logcatThis(mTransactionArrayList.size() + "  size");
        }



        @Override
        protected ArrayList<Transaction> doInBackground(String... params) {
            logcatThis("doInBackgroundStarted");
            String urlString = params[0];
            String resultToDisplay = "";
            InputStream in = null;
            URLConnection connection;
            HttpURLConnection urlConnection;

            // HTTP Get
            try {
                URL url = new URL(urlString);
                connection = url.openConnection();
                connection.connect();
                urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());

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


            // Parse XML
            XmlPullParserFactory pullParserFactory;
            try {
                logcatThis("About to try the parser.");
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                mTransactionArrayList = readFeed(parser);
                logcatThis("size: " + mTransactionArrayList.size() + " First: " +
                        mTransactionArrayList.get(0).getType() + " endTest");

                in.close();
                urlConnection.disconnect();

                // TODO?
                initalizeListView();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        private ArrayList<Transaction> readFeed(XmlPullParser parser)
                throws XmlPullParserException, IOException{

            ArrayList<Transaction> theTransactionArrayList = new ArrayList<>();
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String tag = parser.getName();
                if (tag.equals("PurchasedTransaction")) {
                    logcatThis("tag == 'purchased'");
                    theTransactionArrayList.add(readPurchased(parser));
                } else if (tag.equals("SoldTransaction")) {
                    // TODO STUFF
                }
            }

            return theTransactionArrayList;
        }

        private Transaction readPurchased(XmlPullParser parser)
                throws XmlPullParserException, IOException {

            int id = 0;
            String type = "purchased";
            int fgAmnt = 0;
            int iggAmnt = 0;

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String tag = parser.getName();
                if (tag.equals("id")) {
                    id = readId(parser);
                } else if (tag.equals("fgAmnt")) {
                    fgAmnt = readFg(parser);
                } else if (tag.equals("iggAmnt")) {
                    iggAmnt= readIgg(parser);
                }
            }
            return new Transaction(id, type, fgAmnt, iggAmnt);
        }

        private int readId(XmlPullParser parser) throws IOException,
                XmlPullParserException {

            parser.require(XmlPullParser.START_TAG, ns, "id");
            int id = Integer.parseInt(readText(parser));
            parser.require(XmlPullParser.END_TAG, ns, "id");
            return id;
        }

        private int readFg(XmlPullParser parser) throws IOException,
                XmlPullParserException {

            parser.require(XmlPullParser.START_TAG, ns, "fgAmnt");
            int fgAmnt = Integer.parseInt(readText(parser));
            parser.require(XmlPullParser.END_TAG, ns, "fgAmnt");
            return fgAmnt;
        }

        private int readIgg(XmlPullParser parser) throws IOException,
                XmlPullParserException {

            parser.require(XmlPullParser.START_TAG, ns, "iggAmnt");
            int iggAmnt = Integer.parseInt(readText(parser));
            parser.require(XmlPullParser.END_TAG, ns, "iggAmnt");
            return iggAmnt;
        }

        private String readText(XmlPullParser parser) throws IOException,
                XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
            }
            return result;
        }
    }
}
