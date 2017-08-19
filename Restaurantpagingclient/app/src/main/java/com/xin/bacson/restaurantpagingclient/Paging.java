package com.xin.bacson.restaurantpagingclient;

import java.io.Serializable;

/**
 * Created by Welcomes on 8/14/2017.
 */

public class Paging implements Serializable {
    private int id;
    private String name;
    private int num;
    private int ready;

    public Paging(int id, String name, int num, int ready) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.ready = ready;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getReady() {
        return ready;
    }

    public void setReady(int ready) {
        this.ready = ready;
    }
}
