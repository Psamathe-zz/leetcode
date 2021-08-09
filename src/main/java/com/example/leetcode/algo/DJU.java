package com.example.leetcode.algo;

public class DJU {
    public int rank;
    public DJU p;
    public DJU(int rank){
        this.rank = rank;
        p = this;
    }

    public DJU find() {
        if(p.p != p) {
            p = p.find();
        }
        return p;
    }

    public void union(DJU another) {
        this.find().p = another.find();
    }
}
