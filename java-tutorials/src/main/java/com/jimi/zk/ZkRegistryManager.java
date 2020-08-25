package com.jimi.zk;

import org.apache.curator.framework.CuratorFramework;

/**
 * Zk连接管理
 * @author mingyi.guo01
 */
public interface ZkRegistryManager {
    /**
     * 获取客户端连接
     *
     * @param clusterCode 集群编码
     * @return 客户端连接
     */
    CuratorFramework getClient(String clusterCode);

    /**
     * 刷新连接信息, 应在容器初始化成功后手动调用一次
     */
    void refresh();
}
