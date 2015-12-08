package com.bciancio.androidcapstoneprojectv2.entity;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by student on 12/8/2015.
 */
public class XMLPullParserUsage {
    private final String ns = null;

    public XMLPullParserUsage() { }

    public ArrayList<Transaction> readFeed(XmlPullParser parser)
            throws XmlPullParserException, IOException {

        ArrayList<Transaction> theTransactionArrayList = new ArrayList<>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            if (tag.equals("PurchasedTransaction")) {
                logcatThis("tag == 'PurchasedTransaction'");
                theTransactionArrayList.add(readTransaction(parser, "purchased"));
            } else if (tag.equals("SoldTransaction")) {
                theTransactionArrayList.add(readTransaction(parser, "sold"));
                logcatThis("tag == 'SoldTransaction'");
            }
        }

        return theTransactionArrayList;
    }

    private Transaction readTransaction(XmlPullParser parser, String type)
            throws XmlPullParserException, IOException {

        int id = 0;
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

    public void logcatThis(String message) {
        Log.d("MyDebug", "In XMLPullParserUsage fragment: " + message);
    }
}
