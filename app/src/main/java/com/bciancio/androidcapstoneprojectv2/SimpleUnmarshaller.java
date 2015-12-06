package com.bciancio.androidcapstoneprojectv2;

import com.bciancio.androidcapstoneprojectv2.entity.Transactions;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
/**
 * Created by student on 12/6/2015.
 */
public class SimpleUnmarshaller {

    Transactions mTransactions;

    public SimpleUnmarshaller() {
        mTransactions = new Transactions();
    }

    public  Transactions unmarshallFile(File xmlFile){

        // Deserialize the Person
        if (xmlFile.exists())
        {
            try {
                Serializer serializer = new Persister();
                mTransactions = serializer.read(Transactions.class, xmlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mTransactions;
    }

}
