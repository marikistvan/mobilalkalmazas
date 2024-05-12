package com.example.billiard;

import java.util.Date;

public class PoolItem {
    private String name;
    private int cue;
    private Date date;
    private int count;

    public PoolItem(String name,Date date,int count) {
        this.name = name;
        this.date=date;
        this.count=count;
    }
    public PoolItem(){

    }

    public String getName() {
        return name;
    }

    public int getCue() {
        return cue;
    }

    public Date getDate(){return date;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
