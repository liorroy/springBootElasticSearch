package com.example.springapi.api.controller;

import com.example.springapi.api.model.CreateDocClass;
import com.example.springapi.api.model.ElasticIndex;
import com.example.springapi.api.model.GetDocClass;
import com.example.springapi.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ElasticController {

    private ElasticService elasticService;

    @Autowired
    public ElasticController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    /*
    Create 3 REST API endpoints using JAVA and deploy them on the remote machine.
a. API/Web service to create an index in your new Elasticsearch server
b. API/Web service to create a document in the index you created
c. API/web service which return the Elasticsearch document (created in the previous step) by id
https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/getting-started-java.html
     */

//a. API/Web service to create an index in your new Elasticsearch server

    @PostMapping(path = "/createIndex",
            //consumes =
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElasticIndex> create(@RequestBody ElasticIndex newIndex) throws IOException {
        elasticService.createElasticIndex(newIndex);
        return new ResponseEntity<>(newIndex, HttpStatus.CREATED);
    }

//b. API/Web service to create a document in the index you created

    @PostMapping(path = "/createDocument",
            //consumes =
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateDocClass> create(@RequestBody CreateDocClass req) throws IOException {
        elasticService.createDocument(req.getIndex(), req.getProduct());
        return new ResponseEntity<>(req, HttpStatus.CREATED);
    }

//    c. API/web service which return the Elasticsearch document (created in the previous step) by id

    @PostMapping(path = "/getDocument",
            //consumes =
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@RequestBody GetDocClass getDoc) throws IOException {
        return elasticService.getDocument(getDoc.getIndexName(), getDoc.getdocId());
    }


/*
    for postman: localhost:8080/createIndex

    Body:
    {
        "name": ""
    }

    for postman: localhost:8080/createDocument

    {
        "elasticIndex": {
            "name": ""
        },
        "product": {
            "id": "",
            "name": "",
            "price": .
        }
    }

    for postman: localhost:8080/getDocument

    {
        "indexName": "",
        "docId": ""
    }

 */
}



