package com.jimi.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DefaultZkRegistryManager implements ZkRegistryManager {

    /**
     * 连接等待时间(s)
     */
    private static final int CONNECT_WAITING_SECONDS = 2;

    /**
     * 会话超时时间(ms)
     */
    private static final int SESSION_TIMEOUT = 20_000;

    /**
     * 连接超时时间(ms)
     */
    private static final int CONNECTION_TIMEOUT = 20_000;

    private final Map<String, CuratorFramework> curatorMap = new ConcurrentHashMap<>(16);


    @Override
    public CuratorFramework getClient(String clusterCode) {
        return curatorMap.get(clusterCode);
    }

    @Override
    public void refresh() {
        //TODO
        List<ZkInfo> zkInfoList = new ArrayList<>();
//        log.info("开始刷新zk连接信息...");

        for (ZkInfo zkInfo : zkInfoList) {
            CuratorFramework curatorFramework = curatorMap.get(zkInfo.getClusterCode());

            if (curatorFramework == null) {
                curatorFramework = this.connect(zkInfo.getClusterCode(), zkInfo.getConnectString());

            } else if (!curatorFramework.getZookeeperClient().getCurrentConnectionString().equals(zkInfo.getConnectString())) {
                curatorFramework = this.connect(zkInfo.getClusterCode(), zkInfo.getConnectString());
            }


            if (curatorFramework == null) {
//                log.warn("Failed to connect curatorFramework of cluster [{}]", zkInfo.getClusterCode());
                continue;
            }
            curatorMap.put(zkInfo.getClusterCode(), curatorFramework);
        }
    }

    private CuratorFramework connect(String namespace, String connectString) {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3, 3000));
        if (namespace != null) {
            builder.namespace(namespace);
        }

        CuratorFramework client = builder.build();
        client.start();
        boolean established = false;
        try {
            established = client.blockUntilConnected(CONNECT_WAITING_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
            CloseableUtils.closeQuietly(client);
            client = null;
        }
        return client;
    }
}
