package com.bciancio.androidcapstoneprojectv2.entity;

import org.simpleframework.xml.Element;
/**
 * Created by student on 11/17/2015.
 */
@Element
public class SoldTransaction {
    private int id;

    private String date;

    private int fgAmnt;
    private int iggAmnt;


    // Empty Constructor
    public SoldTransaction() {}

    // Constructor that takes a fg and igg amnt
    public SoldTransaction(int fgAmnt, int iggAmnt ) {
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
