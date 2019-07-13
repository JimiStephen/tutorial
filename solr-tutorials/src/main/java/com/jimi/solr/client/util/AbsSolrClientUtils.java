package com.jimi.solr.client.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public abstract class AbsSolrClientUtils {

    public static SolrClient genaretorHttpSolrClient() {
        String baseUrl = "http://127.0.0.1:8983/solr";
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder();

        SolrClient solrClient = builder.withBaseSolrUrl(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();

        return solrClient;
    }
}
