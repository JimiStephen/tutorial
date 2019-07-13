package com.jimi.solr.client.util;

import org.apache.solr.client.solrj.SolrClient;

public class SolrClientUtils extends AbsSolrClientUtils {


    public static SolrClient getHttpSolrClient(){

        return genaretorHttpSolrClient();
    }
}
