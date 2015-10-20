package com.myapp.sshah.gimagesearch.models;

import java.io.Serializable;

/**
 * Created by sshah1 on 10/19/15.
 */
public class Settings implements Serializable{
    private String size;

    public Settings(){
        size = "small";
    }

    public String getSize(){
        return this.size;
    }

    public void setSize(String size){
        this.size = size;
    }
}