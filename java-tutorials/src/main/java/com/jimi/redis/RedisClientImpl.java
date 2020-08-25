package com.jimi.redis;

import com.jimi.redis.inter.*;
import com.jimi.redis.inter.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @date: 2019/2/25 09:49
 * @description: ucarinc-framework-common 里面RedisClient接口的具体实现
 */
public class RedisClientImpl implements RedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClientImpl.class);

    /**
     * 普通集群的初始化，true为初始化成功
     */
    private volatile AtomicBoolean isInitNormalCluster = new AtomicBoolean(false);

    /**
     * 架构公共集群初始化，true为初始化成功
     */
    private volatile AtomicBoolean isInitPublicCluster = new AtomicBoolean(false);

    /**
     * 初始化Redis客户端，从配置中心
     */
    @Override
    public void initNormalRedis() {
        //判断是否已经初始化
        if (isInitNormalCluster.get()) {
            return;
        }

        if (RedisClientInit.initRedisFromConfcenter()) {
            isInitNormalCluster.set(true);
            LOGGER.info("init redis from cfcenter success");
            return;
        }
        throw new RedisInitException("init redis from cfcenter error ! ! ! please check [pojectName.redis.group.register] and [cachecloud.redis.appName]");
  }

    /**
     * 初始化framework架构公共集群
     */
    @Override
    public void initFrameworkPublicRedis() {
        if (isInitPublicCluster.get()) {
            return;
        }
        if (RedisClientInit.initPublicRedisFromConfcenter()) {
            isInitPublicCluster.set(true);
            LOGGER.info("init public redis from cfcenter success ! ! !");
            return;
        }
        throw new RedisInitException("init public redis from cfcenter error ! ! ! please check [public.redis.register] and appName for framework ");
    }

    /**
     * 停止 释放缓冲池 处理器
     */
    @Override
    public void destroyRedis() {

        LOGGER.info("start stop redis Pool");

        for (Map.Entry<String, RedisCluster> redisEntry : ClusterManager.clusterMap.entrySet()) {
            String group = redisEntry.getKey();
            LOGGER.info("stop redis Group : " + group);
            for (RedisProxy proxy : redisEntry.getValue().getRedisProxyList()) {
                List<RedisReadWriteConfig> redisReadWriteConfigList = proxy.getRedisConfigList();
                if (redisReadWriteConfigList != null) {
                    for (RedisReadWriteConfig config : redisReadWriteConfigList) {
                        try {
                            config.abandonOldRedis();
                        } catch (Exception e) {
                            LOGGER.error("close RedisReadWriteConfig : " + config.getName() + " error.");
                        }
                    }
                }
            }
        }
        //是否需要设置为false
        //this.isInitPublicCluster.set(false);
        //this.isInitNormalCluster.set(false);
    }


    /**
     * 根据groupName判断是否存在可用集群
     *
     * @param groupName 指定组
     * @return boolean
     */
    @Override
    public boolean clusterExist(String groupName) {
        return ClusterManager.clusterExist(groupName);
    }

    /**
     * 计数操作
     *
     * @param groupName 组名
     * @return AtomicCommands
     */
    @Override
    public AtomicCommands getClusterAtomicCommands(String groupName) {
        return new DefaultAtomicCommands(groupName);
    }

    /**
     * Hash操作
     *
     * @param groupName 组名
     * @return HashCommands
     */
    @Override
    public HashCommands getClusterHashCommands(String groupName) {
        return new DefaultHashCommands(groupName);
    }

    /**
     * List操作
     *
     * @param groupName 组名
     * @return ListCommands
     */
    @Override
    public ListCommands getClusterListCommands(String groupName) {
        return new DefaultListCommands(groupName);
    }

    /**
     * Set操作
     *
     * @param groupName 组名
     * @return SetCommands
     */
    @Override
    public SetCommands getClusterSetCommands(String groupName) {
        return new DefaultSetCommands(groupName);
    }

    /**
     * String操作
     *
     * @param groupName 组名
     * @return StringCommands
     */
    @Override
    public StringCommands getClusterStringCommands(String groupName) {
        return new DefaultStringCommands(groupName);
    }

    /**
     * 简单的key、value对象操作
     *
     * @param groupName 组名
     * @return ValueCommands
     */
    @Override
    public ValueCommands getClusterValueCommands(String groupName) {
        return new DefaultValueCommands(groupName);
    }

    /**
     * ZSet操作
     *
     * @param groupName 组名
     * @return ZSetCommands
     */
    @Override
    public ZsetCommands getClusterZSetCommands(String groupName) {
        return new DefaultZsetCommands(groupName);
    }

    /**
     * 基本操作
     *
     * @param groupName 组名
     * @return Commands
     */
    @Override
    public Commands getClusterTedisManager(String groupName) {
        return new DefaultCommands(groupName);
    }


    /**
     * 计数操作
     *
     * @param groupName 组名
     * @return AtomicCommands
     */
    @Override
    public AtomicCommands getClusterAtomicCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultAtomicCommands(groupName);
    }


    /**
     * Hash操作
     *
     * @param groupName 组名
     * @return HashCommands
     */
    @Override
    public HashCommands getClusterHashCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultHashCommands(groupName);
    }

    /**
     * List操作
     *
     * @param groupName 组名
     * @return ListCommands
     */
    @Override
    public ListCommands getClusterListCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultListCommands(groupName);
    }

    /**
     * Set操作
     *
     * @param groupName 组名
     * @return SetCommands
     */
    @Override
    public SetCommands getClusterSetCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultSetCommands(groupName);
    }

    /**
     * String操作
     *
     * @param groupName 组名
     * @return StringCommands
     */
    @Override
    public StringCommands getClusterStringCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultStringCommands(groupName);
    }

    /**
     * 简单的key、value对象操作
     *
     * @param groupName 组名
     * @return ValueCommands
     */
    @Override
    public ValueCommands getClusterValueCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultValueCommands(groupName);
    }

    /**
     * ZSet操作
     *
     * @param groupName 组名
     * @return ZSetCommands
     */
    @Override
    public ZsetCommands getClusterZSetCommands(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultZsetCommands(groupName);
    }

    /**
     * 基本操作
     *
     * @param groupName 组名
     * @return Commands
     */
    @Override
    public Commands getClusterTedisManager(String groupName, String name) {
        setAssignRedisPoolContext(groupName, name);
        return new DefaultCommands(groupName);
    }

    /**
     * 记录指定的Redis
     *
     * @param groupName
     * @param name
     */
    private void setAssignRedisPoolContext(String groupName, String name) {
        RedisPool pool = ClusterManager.clusterModelHash(groupName, name, null);
        AssignRedisPool.set(pool);
    }
}
