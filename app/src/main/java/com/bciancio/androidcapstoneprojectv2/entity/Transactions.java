package com.bciancio.androidcapstoneprojectv2.entity;


import java.util.ArrayList;
/**
 * Created by student on 11/17/2015.
 */
public class Transactions {

    private ArrayList<PurchasedTransaction> purchasedTransactions = null;

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

