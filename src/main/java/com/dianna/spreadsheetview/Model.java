package com.dianna.spreadsheetview;

public class Model {
    private String name;
    private int iCon;

    public Model(String name, int iCon) {
        this.name = name;
        this.iCon = iCon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getiCon() {
        return iCon;
    }

    public void setiCon(int iCon) {
        this.iCon = iCon;
    }
}
