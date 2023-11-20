package com.example.springapi.api.model;

public class ElasticIndex {
    private String name;

    public ElasticIndex(){}

    public ElasticIndex(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
