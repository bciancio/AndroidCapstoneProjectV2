package com.bciancio.androidcapstoneprojectv2.entity;

/**
 * Created by student on 12/7/2015.
 */
public class Transaction {
    private int id;

    private String type;
    private int fgAmnt;
    private int iggAmnt;

    /**
     * Empty constructor
     */
    public Transaction() {
    }

    /**
     *  Constructor that takes all instanace variables
     * @param id
     * @param type
     * @param fgAmnt
     * @param iggAmnt
     */
    public Transaction(int id, String type, int fgAmnt, int iggAmnt) {
        this.id = id;
        this.type = type;
        this.fgAmnt = fgAmnt;
        this.iggAmnt = iggAmnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
