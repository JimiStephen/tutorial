package com.jimi.solr.client.excise;

import com.jimi.solr.client.util.SolrClientUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpSolrClientSample {

    private static String collection = "gettingstarted";

    public static void main (String args[]) {

       /* try {
            search();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }*/

        createIndex();
    }

    public static void search() throws IOException, SolrServerException {
        final SolrClient solrClient = SolrClientUtils.getHttpSolrClient();

        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "*:*");
        queryParamMap.put("fl", "id, name");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        //techproducts
        final QueryResponse response = solrClient.query(collection, queryParams);
        final SolrDocumentList documents = response.getResults();

        print("Found " + documents.getNumFound() + " documents");
        for(SolrDocument document : documents) {
            final String id = (String) document.getFirstValue("id");
            final String name = (String) document.getFirstValue("name");

            print("id: " + id + "; name: " + name);
        }

    }

    public static void createIndex(){
        final SolrClient client = SolrClientUtils.getHttpSolrClient();

        final SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", UUID.randomUUID().toString());
        doc.addField("name", "Amazon Kindle Paperwhite");

        try {
            final UpdateResponse updateResponse = client.add(collection, doc);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Indexed documents must be committed
        try {
            client.commit(collection);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void print(String msg){

        System.out.println(msg);
    }
}
