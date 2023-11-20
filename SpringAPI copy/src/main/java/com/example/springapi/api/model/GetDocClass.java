package com.example.springapi.api.model;

public class GetDocClass {
    private String indexName;

    private String docId;

    public GetDocClass(){}

    public GetDocClass(String indexName, String docId){
        this.indexName = indexName;
        this.docId = docId;
    }

    public String getIndexName(){
        return indexName;
    }

    public void setIndexName(String indexName){
        this.indexName = indexName;
    }

    public String getdocId(){
        return docId;
    }

    public void setdocId(String docId){
        this.docId = docId;
    }
}
