package com.example.springapi.api.model;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.springapi.service.ElasticService;

public class CreateDocClass {
        //Declaration of instance variables:
        private ElasticIndex _elasticIndex;
        private Product _product;

        private ElasticService elasticService;
        private ElasticsearchClient esClient;

//        public CreateDocClass (CreateDocClass other){
//            elasticIndex = new ElasticIndex(other.elasticIndex;
//            product = new Product(other.product);
// }

//    public CreateDocClass(){}

    public CreateDocClass(ElasticIndex elasticIndex, Product product){
        this._elasticIndex = elasticIndex;
        this._product = product;
    }
//
//    public CreateDocClass(String indexName, String id, String productName, double price){
//        this._elasticIndex.setName(indexName);
//        this._product.setId(id);
//        this._product.setName(productName);
//        this._product.setPrice(price);
//    }

    public ElasticIndex getIndex(){
        return this._elasticIndex;
    }

    public String getIndexName(){
        return this._elasticIndex.getName();
    }

    public Product getProduct(){
        return this._product;
    }

    public String getProductId(){
        return  this._product.getId();
    }


}
