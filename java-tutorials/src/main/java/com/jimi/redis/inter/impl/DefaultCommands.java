package com.jimi.redis.inter.impl;

import com.jimi.redis.inter.Commands;
import com.ucarinc.framework.client.redis.common.ScanParams;
import com.ucarinc.framework.client.redis.common.ScanResult;
import com.ucarinc.framework.client.redis.support.DataType;
import com.ucarinc.framework.client.redis.support.SortParams;
import com.zuche.framework.thread.pool.IAsynchronousHandler;
import com.zuche.redis.cluster.AssignRedisPool;
import com.zuche.redis.cluster.ClusterManager;
import com.zuche.redis.cluster.RedisCluster;
import com.zuche.redis.config.RedisReadWriteConfig;
import com.zuche.redis.core.BaseCommands;
import com.zuche.redis.core.CommandsExecutor;
import com.zuche.redis.exception.BusinessRuntimeException;
import com.zuche.redis.exception.RedisConnectionException;
import com.zuche.redis.exception.RedisRuntimeException;
import com.zuche.redis.pool.RedisPool;
import com.zuche.redis.serializer.BaseSerializationUtils;
import com.zuche.redis.utils.RedisThreadPool;

import java.security.acl.Group;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <br/> Created on 2014-7-3 下午1:55:06
 *
 *
 * @since 3.3
 */
public class DefaultCommands extends BaseCommands implements Commands {

    protected String groupName;

    /**
     * 单位毫秒
     */
    static final int SLEEP_TIME = 500;
    static final String ERROR_MESSAGE = "com.zuche.redis.TedisDataException: READONLY You can't write against a read only slave.";
    /**
     * 重试次数
     */
    static final int REPEAT_NUM = 3;

    public DefaultCommands(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public <K> void delete(final int namespace, final K key) {
        delete(namespace, key, false);
    }

    @Override
    public <K> void delete(final int namespace, final K key, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.deleteForTry(namespace, key, useNewKeySerialize);
                return;
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && i < REPEAT_NUM - 1) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                } else {
                    throw new RedisException(e);
                }
            }
        }
    }

    private <K> void deleteForTry(final int namespace, final K key, final boolean useNewKeySerialize) {
        doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.del(rawKey(namespace, key, useNewKeySerialize));
                return null;
            }
        });
    }

    @Override
    public <K> void delete(final int namespace, final Collection<K> keys) {
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K> Boolean expire(final int namespace, final K key, long timeout, TimeUnit unit) {
        return expire(namespace, key, timeout, unit, false);
    }

    @Override
    public <K> Boolean expire(final int namespace, final K key, long timeout, TimeUnit unit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.expireForTry(namespace, key, timeout, unit, useNewKeySerialize);
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && i < REPEAT_NUM - 1) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                } else {
                    throw new RedisException(e);
                }
            }
        }
        throw new RedisException("执行expire失败！");
    }

    private <K> Boolean expireForTry(final int namespace, final K key, long timeout, TimeUnit unit, final boolean useNewKeySerialize) {
        final long seconds = unit.toSeconds(timeout);
        return (Boolean) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.expire(rawKey(namespace, key, useNewKeySerialize), seconds);
            }
        });
    }

    @Override
    public <K> Boolean expireAt(final int namespace, final K key, final Date date) {
        return expireAt(namespace, key, date, false);
    }

    @Override
    public <K> Boolean expireAt(final int namespace, final K key, final Date date, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.expireAtForTry(namespace, key, date, useNewKeySerialize);
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && i < REPEAT_NUM - 1) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                } else {
                    throw new RedisException(e);
                }
            }
        }
        throw new RedisException("执行expireAt失败！");
    }

    private <K> Boolean expireAtForTry(final int namespace, final K key, final Date date, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.expireAt(rawKey(namespace, key, useNewKeySerialize), date.getTime() / 1000);
            }
        });
    }

    @Override
    public <K> Long getExpire(final int namespace, final K key) {
        return getExpire(namespace, key, false);
    }

    @Override
    public <K> Long getExpire(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.ttl(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }


    @Override
    public <K> Long getExpireMs(final int namespace, final K key) {
        return getExpireMs(namespace, key, false);
    }

    @Override
    public <K> Long getExpireMs(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.pttl(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }


    @Override
    public <K> Boolean hasKey(final int namespace, final K key) {
        return hasKey(namespace, key, false);
    }

    @Override
    public <K> Boolean hasKey(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.exists(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }

    @Override
    public <K> Boolean persist(final int namespace, final K key) {
        return persist(namespace, key, false);
    }

    @Override
    public <K> Boolean persist(final int namespace, final K key, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.persistForTry(namespace, key, useNewKeySerialize);
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && i < REPEAT_NUM - 1) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                } else {
                    throw new RedisException(e);
                }
            }
        }
        throw new RedisException("执行persist失败！");
    }

    private <K> Boolean persistForTry(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.persist(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }

    @Override
    public <K> void rename(final int namespace, final K oldKey, final K newKey) {
        rename(namespace, oldKey, newKey, false);
    }

    @Override
    public <K> void rename(final int namespace, final K oldKey, final K newKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K> Boolean renameIfAbsent(final int namespace, final K oldKey, final K newKey) {
        return renameIfAbsent(namespace, oldKey, newKey, false);
    }

    @Override
    public <K> Boolean renameIfAbsent(final int namespace, final K oldKey, final K newKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> List<V> sort(final int namespace, final K key, final SortParams params) {
        return sort(namespace, key, params, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> List<V> sort(final int namespace, final K key, final SortParams params, final boolean useNewKeySerialize) {
        return deserializeValues((List<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sort(rawKey(namespace, key, useNewKeySerialize), params);
            }
        }));
    }

    @Override
    public <K> Long sort(final int namespace, final K key, final SortParams params, final K storeKey) {
        return sort(namespace, key, params, storeKey, false);
    }

    @Override
    public <K> Long sort(final int namespace, final K key, final SortParams params, final K storeKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K> DataType type(final int namespace, final K key) {
        return type(namespace, key, false);
    }

    @Override
    public <K> DataType type(final int namespace, final K key, final boolean useNewKeySerialize) {
        return DataType.fromCode((String) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.type(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    protected <K, V> List<V> multiGet(int namespace, Collection<K> keys,
                                      boolean useNewKeySerialize, CommandsExecutor<K, V> commandsExecutor) {
        Map<String, List<K>> map = groupForKeys(namespace, groupName, keys);
        if (map == null || map.size() == 0) {
            return new ArrayList<V>();
        }
        RedisCluster redisCluster = ClusterManager.clusterMap.get(groupName);
        if (redisCluster == null) {
            throw new BusinessRuntimeException("未找到groupName为" + groupName + "的集群信息");
        }
        List<RedisReadWriteConfig> listConf = redisCluster.getAllRedisReadWriteConfig();
        if (listConf.size() == 0) {
            throw new BusinessRuntimeException("groupName为" + groupName + "的集群中未配置实例");
        }

        int cluSize = listConf.size();
        List<V> listRes = new ArrayList<V>();
        if (keys.size() > cluSize) {

            Iterator<Map.Entry<String, List<K>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                List<K> partKeys = iterator.next().getValue();
                final byte[][] rawKeys = new byte[partKeys.size()][];
                int counter = 0;
                for (K key : partKeys) {
                    rawKeys[counter++] = rawKey(namespace, key, useNewKeySerialize);
                }
                List<V> list = commandsExecutor.multiExecute(namespace, partKeys, rawKeys);
                for (V v : list) {
                    if (v != null) {
                        listRes.add(v);
                    }
                }
            }
            return listRes;
        }

        for (K key : keys) {
            V value = commandsExecutor.singleExecute(namespace, key, useNewKeySerialize);
            if (value != null) {
                listRes.add(value);
            }
        }

        return listRes;
    }

    public Group getProvider() {
        return this.commandsProvider;
    }


    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param cursor
     * @param pattern
     * @param count
     * @param <K>
     * @return
     */
    @Override
    public <K> ScanResult<K> scan(int namespace, String cursor, String pattern, int count) {
        return scan(namespace, cursor,  pattern, count, false);
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param cursor
     * @param pattern
     * @param count
     * @param isKeyStringSerialize
     * @param <K>
     * @return
     */
    @Override
    public <K> ScanResult<K> scan(int namespace, String cursor, String pattern, int count, boolean isKeyStringSerialize) {
        throw new UnsupportedOperationException();
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param pattern
     * @param <K>
     * @return
     */
    @Override
    public <K> Set<K> scanAllKeys(int namespace, String pattern) {
        return scanAllKeys(namespace,  pattern, false, false);
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param pattern
     * @param isKeyStringSerialize
     * @param <K>
     * @return
     */
    @Override
    public <K> Set<K> scanAllKeys(int namespace, String pattern, boolean isKeyStringSerialize, boolean isThread) {
        RedisCluster redisCluster = ClusterManager.clusterMap.get(groupName);
        if(redisCluster == null){
            throw new BusinessRuntimeException("未找到groupName为"+groupName+"的集群信息");
        }
        List<RedisReadWriteConfig> listConf = redisCluster.getAllRedisReadWriteConfig();

        Set<byte[]> allBytekeys;
        if(isThread) {
            try {
                allBytekeys = getScanResultByThread(namespace, pattern, listConf);
            } catch (Exception e) {
                throw new BusinessRuntimeException(e);
            }
        }else{
            allBytekeys = getScanResultNotThread(namespace, pattern, listConf);
        }

        Set<byte[]> newbytekeys = new HashSet<byte[]>();
        for (byte[] bytekey : allBytekeys) {
            newbytekeys.add(removeNamespaceFromKey(bytekey));
        }
        if(isKeyStringSerialize){
            return BaseSerializationUtils.deserialize(newbytekeys, getStringSerializer());
        }else{
            return BaseSerializationUtils.deserialize(newbytekeys, getKeySerializer());
        }
    }


    @SuppressWarnings("unchecked")
    private Set<byte[]> getScanResultByThread(int namespace, String pattern, List<RedisReadWriteConfig> listConf) throws Exception {
        final Iterator<RedisReadWriteConfig> ite = listConf.iterator();
        Set<byte[]> resultSet = new HashSet<>();

        int threadNum = listConf.size();
        Queue<Future<Object>> fQueue = new LinkedList<Future<Object>>();
        for (int i = 0; i < threadNum; i++) {
            Future<Object> future = RedisThreadPool.submit(new IAsynchronousHandler() {

                @Override
                public Object call() throws Exception {
                    final RedisReadWriteConfig config = ite.next();
                    //强制走读库
                    RedisPool pool = ClusterManager.getReadRedis(config);
                    AssignRedisPool.set(pool);

                    Set<byte[]> allBytekeys = new HashSet<byte[]>();
                    String cursor = "0";
                    do {
                        String finalCursor = cursor;
                        ScanResult<byte[]> byteResult = (ScanResult<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(pattern), groupName, READ) {
                            @Override
                            public Object execute() {
                                ScanParams params = new ScanParams();
                                params.match((namespace + ":" + pattern).getBytes());
                                // 默认是10
                                params.count(1000);
                                return commands.scan(finalCursor.getBytes(), params);
                            }
                        });

                        if(byteResult != null) {
                            cursor = byteResult.getCursor();
                            List<byte[]> bytekeys = byteResult.getResult();
                            allBytekeys.addAll(bytekeys);
                        }
                    } while(!"0".equals(cursor));

                    return allBytekeys;
                }

                @Override
                public void executeAfter(Throwable t) {
                }

                @Override
                public void executeBefore(Thread t) {
                }
            });
            fQueue.offer(future);
        }

        Future<Object> future = null;
        Long t = System.currentTimeMillis();
        while ((future = fQueue.poll()) != null) {

            if (System.currentTimeMillis() - t > 2000) {
                throw new RuntimeException("多线程查询超时， 超时时间2s！");
            }

            if (future.isDone()) {
                Object obj = future.get();
                if (obj == null) {
                    throw new RuntimeException("多线程查询失败！");
                }
                Set<byte[]> tempSet = (Set<byte[]>) obj;
                if (tempSet.size() > 0) {
                    resultSet.addAll(tempSet);
                }
            } else {
                fQueue.offer(future);
            }
        }

        return resultSet;
    }



    @SuppressWarnings("unchecked")
    private Set<byte[]> getScanResultNotThread(int namespace, String pattern, List<RedisReadWriteConfig> listConf) {
        Set<byte[]> allBytekeys = new HashSet<byte[]>();
        for(RedisReadWriteConfig config : listConf){
            //强制走读库
            RedisPool pool = ClusterManager.getReadRedis(config);
            AssignRedisPool.set(pool);

            String cursor = "0";
            do {
                String finalCursor = cursor;
                ScanResult<byte[]> byteResult = (ScanResult<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(pattern), groupName, READ) {
                    @Override
                    public Object execute() {
                        ScanParams params = new ScanParams();
                        params.match((namespace + ":" + pattern).getBytes());
                        // 默认是10
                        params.count(1000);
                        return commands.scan(finalCursor.getBytes(), params);
                    }
                });

                if(byteResult != null) {
                    cursor = byteResult.getCursor();
                    List<byte[]> bytekeys = byteResult.getResult();
                    allBytekeys.addAll(bytekeys);
                }
            } while(!"0".equals(cursor));
        }
        return allBytekeys;
    }

}
