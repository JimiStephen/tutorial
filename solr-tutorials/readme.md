

### solrj 学习-简单入门
>Types of SolrClients
SolrClient has a few concrete implementations, each geared towards a different usage-pattern or resiliency model:

1，HttpSolrClient - geared towards query-centric workloads, though also a good general-purpose client. Communicates directly with a single Solr node.

2，LBHttpSolrClient - balances request load across a list of Solr nodes. Adjusts the list of "in-service" nodes based on node health.

3，CloudSolrClient - geared towards communicating with SolrCloud deployments. Uses already-recorded ZooKeeper state to discover and route requests to healthy Solr nodes.

4，ConcurrentUpdateSolrClient - geared towards indexing-centric workloads. Buffers documents internally before sending larger batches to Solr.