package com.jimi.redis;


import com.jimi.redis.inter.*;

/**
 * @date: 2019/2/21 10:23
 * @description: 客户端提供用于各种类型的操作Redis的接口
 */
public class RedisFactory {

    /**
     * 初始化Redis客户端
     */
    private static void init() {
        RedisManager.getRedisClient().initNormalRedis();
    }

    /**
     * 销毁RedisCluster
     */
    public static void destoryRedis() {
        RedisManager.getRedisClient().destroyRedis();
    }

    /**
     * 返回计数操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static AtomicCommands getClusterAtomicCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterAtomicCommands(groupName);
    }

    /**
     * 返回Hash操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static HashCommands getClusterHashCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterHashCommands(groupName);
    }

    /**
     * 返回List操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static ListCommands getClusterListCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterListCommands(groupName);
    }


    /**
     * 返回Set操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static SetCommands getClusterSetCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterSetCommands(groupName);
    }

    /**
     * 返回String操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static StringCommands getClusterStringCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterStringCommands(groupName);
    }

    /**
     * 返回简单操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static ValueCommands getClusterValueCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterValueCommands(groupName);
    }

    /**
     * 返回Zset操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static ZsetCommands getClusterZSetCommands(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterZSetCommands(groupName);
    }
    
    /**
     * 返回基本操作接口，默认从配置中心初始化客户端
     * <p>
     * 从配置中心初始化Redis，建议使用该方法
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static Commands getClusterTedisManager(String groupName) {
        init();
        return RedisManager.getRedisClient().getClusterTedisManager(groupName);
    }

    /**
     * 返回计数操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static AtomicCommands getClusterAtomicCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterAtomicCommands(groupName, name);
    }

    /**
     * 返回Hash操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static HashCommands getClusterHashCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterHashCommands(groupName, name);
    }

    /**
     * 返回List操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static ListCommands getClusterListCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterListCommands(groupName, name);
    }

    /**
     * 返回Set操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static SetCommands getClusterSetCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterSetCommands(groupName, name);
    }

    /**
     * 返回String操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static StringCommands getClusterStringCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterStringCommands(groupName, name);
    }

    /**
     * 返回基本操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static Commands getClusterTedisManager(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterTedisManager(groupName, name);
    }

    /**
     * 返回简单操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static ValueCommands getClusterValueCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterValueCommands(groupName, name);
    }

    /**
     * 返回Zset操作接口，默认从配置中心初始化客户端
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @param name      一般情况为数据源cachecloud.redis.appName里面的rwName
     * @return
     */
    public static ZsetCommands getClusterZSetCommands(String groupName, String name) {
        init();
        return RedisManager.getRedisClient().getClusterZSetCommands(groupName, name);
    }

    /**
     * 判断是否存在指定的集群
     *
     * @param groupName 集群名（注意：2.8.12为groupName）
     * @return
     */
    public static boolean clusterExist(String groupName) {
        init();
        return RedisManager.getRedisClient().clusterExist(groupName);
    }

}
