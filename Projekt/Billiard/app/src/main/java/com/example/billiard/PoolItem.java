package com.example.billiard;

public class PoolItem {
    private String name;
    private int cue;
    private int price;
    private boolean free;

    public PoolItem(String name, int cue, int price, boolean free) {
        this.name = name;
        this.cue = cue;
        this.price = price;
        this.free = free;
    }

    public String getName() {
        return name;
    }

    public int getCue() {
        return cue;
    }

    public int getPrice() {
        return price;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isFree() {
        return free;
    }
}
