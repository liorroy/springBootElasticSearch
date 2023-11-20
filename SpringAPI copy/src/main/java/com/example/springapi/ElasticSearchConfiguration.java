package com.example.springapi;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Configuration
public class ElasticSearchConfiguration {
    @Bean
    public RestClient getRestClient() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        Path caCertificatePath = Paths.get("/Users/study/elasticsearch-8.11.1/config/certs/http_ca.crt");
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        InputStream is = Files.newInputStream(caCertificatePath);
        trustedCa = factory.generateCertificate(is);
        KeyStore truststore = KeyStore.getInstance("pkcs12");
        truststore.load(null, null);
        truststore.setCertificateEntry("ca", trustedCa);

        SSLContextBuilder sslBuilder = SSLContexts.custom()
                .loadTrustMaterial(truststore, null);
        final SSLContext sslContext = sslBuilder.build();
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "https"))
                .setDefaultHeaders(new Header[]{
                new BasicHeader("Authorization", "ApiKey " + "dmRFOTNvc0JhX082YmZOenJNUDQ6TkxvUE9aVFJTX2F5X3dTY1NXTzZJUQ==")
        }).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setSSLContext(sslContext);
                    }
                }).build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return new RestClientTransport(
                getRestClient(), new JacksonJsonpMapper());
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }
}