package com.jimi.redis;

import com.jimi.redis.inter.*;

/**
 * @date: 2019/2/21 14:24
 * @description: 根据组信息，返回各种操作的接口对象
 */
public interface RedisClient {


    /**
     * 初始化普通集群
     */
    void initNormalRedis();

    /**
     * 初始化公共集群
     */
    void initFrameworkPublicRedis();

    /**
     * 销毁Redis集群
     */
    void destroyRedis();

    /**
     * 判断指定集群是否存在
     *
     * @param clusterName 指定组
     * @return
     */
    boolean clusterExist(String clusterName);

    /**
     * 计数操作接口
     *
     * @param clusterName 指定组
     * @return UredisAtomicCommands
     */
    AtomicCommands getClusterAtomicCommands(String clusterName);

    /**
     * Hash操作接口
     *
     * @param clusterName 指定组
     * @return UredisHashCommands
     */
    HashCommands getClusterHashCommands(String clusterName);

    /**
     * List操作接口,更多的时候你可以当场一个队列来操作
     *
     * @param clusterName 指定组
     * @return UredisListCommands
     */
    ListCommands getClusterListCommands(String clusterName);

    /**
     * Set操作接口
     *
     * @param clusterName 指定组
     * @return UredisSetCommands
     */
    SetCommands getClusterSetCommands(String clusterName);

    /**
     * String操作接口
     *
     * @param clusterName 指定组
     * @return UredisStringCommands
     */
    StringCommands getClusterStringCommands(String clusterName);

    /**
     * 简单的key value对象操作接口
     *
     * @param clusterName 指定组
     * @return ValueCommands
     */
    ValueCommands getClusterValueCommands(String clusterName);

    /**
     * Zset操作接口
     *
     * @param clusterName 指定组
     * @return ZsetCommands
     */
    ZsetCommands getClusterZSetCommands(String clusterName);

    /**
     * 基本操作接口
     *
     * @param clusterName 指定组
     * @return UredisKeysCommands
     */
    Commands getClusterTedisManager(String clusterName);

    /**
     * 计数操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisAtomicCommands
     */
    AtomicCommands getClusterAtomicCommands(String clusterName, String name);

    /**
     * Hash操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisHashCommands
     */
    HashCommands getClusterHashCommands(String clusterName, String name);

    /**
     * List操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisListCommands
     */
    ListCommands getClusterListCommands(String clusterName, String name);

    /**
     * Set操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisSetCommands
     */
    SetCommands getClusterSetCommands(String clusterName, String name);

    /**
     * String操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisStringCommands
     */
    StringCommands getClusterStringCommands(String clusterName, String name);

    /**
     * 基本操作接口
     *
     * @param groupName 指定组
     * @param name      指定redis标识
     * @return UredisKeysCommands
     */
    Commands getClusterTedisManager(String groupName, String name);

    /**
     * 简单的key，value操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return ValueCommands
     */
    ValueCommands getClusterValueCommands(String clusterName, String name);

    /**
     * ZSet操作接口
     *
     * @param clusterName 指定组
     * @param name        指定redis标识
     * @return UredisZsetCommands
     */
    ZsetCommands getClusterZSetCommands(String clusterName, String name);
}
