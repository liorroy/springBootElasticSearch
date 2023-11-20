package com.example.springapi.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.transport.ElasticsearchTransport;
import com.example.springapi.api.model.ElasticIndex;
import com.example.springapi.api.model.Product;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticService {


// URL and API key
//    private String serverUrl;
//    private String apiKey;


    // Create the low-level client
    @Autowired
    private RestClient restClient;

    // Create the transport with a Jackson mapper
    @Autowired
    private ElasticsearchTransport transport;

    // And create the API client
    @Autowired
    private ElasticsearchClient esClient;

//a. API/Web service to create an index in your new Elasticsearch server

    public boolean createElasticIndex(ElasticIndex newIndex) throws IOException {
        return esClient.indices().create(c -> c
                .index(newIndex.getName())
        ).acknowledged();

    }

//b. API/Web service to create a document in the index you created

    public void createDocument(ElasticIndex newIndex, Product newProduct) throws IOException {
        IndexResponse response = esClient.index(i -> i
                .index(newIndex.getName())
                .id(newProduct.getId())
                .document(newProduct)
        );
    }

//    c. API/web service which return the Elasticsearch document (created in the previous step) by id

    public Object getDocument(String indexName, String docId) throws IOException {
        GetResponse<Product> response = esClient.get(g -> g
                        .index(indexName)
                        .id(docId),
                Product.class
        );
        if (response.found()) {
            Product product = response.source();
            return product;
        }
        return "Sorry, Not Found";
        }

}
