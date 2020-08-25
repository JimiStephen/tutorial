package com.jimi.zk;

import lombok.Data;

import java.io.Serializable;

/**
 * Zk信息
 *
 */
@Data
public class ZkInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 集群编码
     */
    private String clusterCode;
    /**
     * 集群名称
     */
    private String clusterName;
    /**
     * 连接字符串
     */
    private String connectString;
}
