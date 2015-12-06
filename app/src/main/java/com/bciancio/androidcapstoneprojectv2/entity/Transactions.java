package com.bciancio.androidcapstoneprojectv2.entity;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
/**
 * Created by student on 11/17/2015.
 */

@Root
public class Transactions {

    @ElementArray
    private ArrayList<PurchasedTransaction> purchasedTransactions = null;

    @ElementArray
    private ArrayList<SoldTransaction> soldTransactions = null;

    public ArrayList<PurchasedTransaction> getPurchasedTransactions() {
        return purchasedTransactions;
    }

    public void setPurchasedTransactions(ArrayList<PurchasedTransaction> purchasedTransactions) {
        this.purchasedTransactions = purchasedTransactions;
    }

    public ArrayList<SoldTransaction> getSoldTransactions() {
        return soldTransactions;
    }

    public void setSoldTransactions(ArrayList<SoldTransaction> soldTransactions) {
        this.soldTransactions = soldTransactions;
    }
}

