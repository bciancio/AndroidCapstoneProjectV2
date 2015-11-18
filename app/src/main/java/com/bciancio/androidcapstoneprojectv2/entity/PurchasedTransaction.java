package com.bciancio.androidcapstoneprojectv2.entity;

/**
 * Created by Student on 11/3/2015.
 */
public class PurchasedTransaction {
    private int id;

    private String date;

    private int fgAmnt;
    private int iggAmnt;

    // Empty constructor
    public PurchasedTransaction() { }

    // Constructor that takes a fg and igg amnt
    public PurchasedTransaction(int fgAmnt, int iggAmnt) {
        this.fgAmnt = fgAmnt;
        this.iggAmnt = iggAmnt;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFgAmnt() {
        return fgAmnt;
    }

    public void setFgAmnt(int fgAmnt) {
        this.fgAmnt = fgAmnt;
    }

    public int getIggAmnt() {
        return iggAmnt;
    }

    public void setIggAmnt(int iggAmnt) {
        this.iggAmnt = iggAmnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
