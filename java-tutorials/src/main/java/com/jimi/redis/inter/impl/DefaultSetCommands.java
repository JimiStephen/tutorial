package com.jimi.redis.inter.impl;

import com.jimi.redis.inter.SetCommands;
import com.ucarinc.framework.client.redis.common.ScanParams;
import com.ucarinc.framework.client.redis.common.ScanResult;
import com.zuche.redis.exception.RedisConnectionException;
import com.zuche.redis.exception.RedisRuntimeException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <br/> Created on 2014-7-3 下午1:58:54
 *
 *
 * @since 3.3
 */
public class DefaultSetCommands extends DefaultCommands implements SetCommands {

    public DefaultSetCommands(String groupName) {
        super(groupName);
    }

    @Override
    public <K, V> Long add(final int namespace, final K key, final V... value) {
        return add(false, namespace, key, value);
    }

    @Override
    public <K, V> Long add(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.addWithoutTry(useNewKeySerialize, namespace, key, value);
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
        throw new RedisException("执行set的add失败！");
    }

    private <K, V> Long addWithoutTry(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        return (Long) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sAdd(rawKey(namespace, key, useNewKeySerialize), rawValues(value));
            }
        });
    }

    @Override
    public <K, V> Set<V> difference(final int namespace, K key, K otherKey) {
        return difference(namespace, key, otherKey, false);
    }

    @Override
    public <K, V> Set<V> difference(final int namespace, K key, K otherKey, final boolean useNewKeySerialize) {
        return difference(namespace, key, Collections.singleton(otherKey), useNewKeySerialize);
    }

    @Override
    public <K, V> Set<V> difference(final int namespace, final K key, final Collection<K> otherKeys) {
        return difference(namespace, key, otherKeys, false);
    }

    @Override
    public <K, V> Set<V> difference(final int namespace, final K key, final Collection<K> otherKeys, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> void differenceAndStore(final int namespace, K key, K otherKey, K destKey) {
        differenceAndStore(namespace, key, Collections.singleton(otherKey), destKey);
    }

    @Override
    public <K, V> void differenceAndStore(final int namespace, K key, K otherKey, K destKey, final boolean useNewKeySerialize) {
        differenceAndStore(namespace, key, Collections.singleton(otherKey), destKey, useNewKeySerialize);
    }

    @Override
    public <K, V> void differenceAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey) {
        differenceAndStore(namespace, key, otherKeys, destKey, false);
    }

    @Override
    public <K, V> void differenceAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> Set<V> intersect(final int namespace, final K key, final K otherKey) {
        return intersect(namespace, key, Collections.singleton(otherKey));
    }

    @Override
    public <K, V> Set<V> intersect(final int namespace, final K key, final K otherKey, final boolean useNewKeySerialize) {
        return intersect(namespace, key, Collections.singleton(otherKey), useNewKeySerialize);
    }

    @Override
    public <K, V> Set<V> intersect(final int namespace, final K key, final Collection<K> otherKeys) {
        return intersect(namespace, key, otherKeys, false);
    }

    @Override
    public <K, V> Set<V> intersect(final int namespace, final K key, final Collection<K> otherKeys, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> void intersectAndStore(final int namespace, K key, K otherKey, K destKey) {
        intersectAndStore(namespace, key, Collections.singleton(otherKey), destKey);
    }

    @Override
    public <K, V> void intersectAndStore(final int namespace, K key, K otherKey, K destKey, final boolean useNewKeySerialize) {
        intersectAndStore(namespace, key, Collections.singleton(otherKey), destKey, useNewKeySerialize);
    }

    @Override
    public <K, V> void intersectAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey) {
        intersectAndStore(namespace, key, otherKeys, destKey, false);
    }

    @Override
    public <K, V> void intersectAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K> Boolean nisMember(int namespace, K key, Object o) {
        return nisMember(namespace, key, o, false);
    }

    @Override
    public <K> Boolean nisMember(final int namespace, final K key, final Object o, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sIsMember(rawKey(namespace, key, useNewKeySerialize), rawValue(o));
            }
        });
    }

    @Override
    public <K, V> Set<V> members(final int namespace, final K key) {
        return members(namespace, key, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Set<V> members(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValues((Set<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sMembers(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> Boolean move(final int namespace, final K key, final V value, final K destKey) {
        return move(namespace, key, value, destKey, false);
    }

    @Override
    public <K, V> Boolean move(final int namespace, final K key, final V value, final K destKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> V pop(final int namespace, final K key) {
        return pop(namespace, key, false);
    }

    @Override
    public <K, V> V pop(final int namespace, final K key, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.popWithoutTry(namespace, key, useNewKeySerialize);
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
        throw new RedisException("执行set的pop失败！");
    }

    @Override
    public <K, V> Set<V> pop(int namespace, K key, long count) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    @Override
    public <K, V> Set<V> pop(int namespace, K key, long count, boolean useNewKeySerialize) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    private <K, V> V popWithoutTry(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sPop(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> V randomMember(final int namespace, final K key) {
        return randomMember(namespace, key, false);
    }

    @Override
    public <K, V> V randomMember(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sRandMember(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> List<V> randomMember(int namespace, K key, int count) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    @Override
    public <K, V> List<V> randomMember(int namespace, K key, int count, boolean useNewKeySerialize) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    private <K, V> Long removeWithoutTry(final int namespace, final K key, final boolean useNewKeySerialize, final Object... o) {
        return (Long) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sRem(rawKey(namespace, key, useNewKeySerialize), rawValues(o));
            }
        });
    }

    @Override
    public <K, V> Long nremove(final int namespace, final K key, final Object... values) {
        return nremove(false, namespace, key, values);
    }

    @Override
    public <K, V> Long nremove(final boolean useNewKeySerialize, final int namespace, final K key, final Object... values) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.removeWithoutTry(namespace, key, useNewKeySerialize, values);
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
        throw new RedisException("执行set的remove失败！");
    }


    @Override
    public <K, V> Long size(final int namespace, final K key) {
        return size(namespace, key, false);
    }

    @Override
    public <K, V> Long size(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.sCard(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }

    @Override
    public <K, V> Set<V> union(final int namespace, final K key, final K otherKey) {
        return union(namespace, key, Collections.singleton(otherKey));
    }

    @Override
    public <K, V> Set<V> union(final int namespace, final K key, final K otherKey, final boolean useNewKeySerialize) {
        return union(namespace, key, Collections.singleton(otherKey), useNewKeySerialize);
    }

    @Override
    public <K, V> Set<V> union(final int namespace, final K key, final Collection<K> otherKeys) {
        return union(namespace, key, otherKeys, false);
    }

    @Override
    public <K, V> Set<V> union(final int namespace, final K key, final Collection<K> otherKeys, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> void unionAndStore(final int namespace, K key, K otherKey, K destKey) {
        unionAndStore(namespace, key, Collections.singleton(otherKey), destKey);
    }

    @Override
    public <K, V> void unionAndStore(final int namespace, K key, K otherKey, K destKey, final boolean useNewKeySerialize) {
        unionAndStore(namespace, key, Collections.singleton(otherKey), destKey, useNewKeySerialize);
    }

    @Override
    public <K, V> void unionAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey) {
        unionAndStore(namespace, key, otherKeys, destKey, false);
    }

    @Override
    public <K, V> void unionAndStore(final int namespace, final K key, final Collection<K> otherKeys, final K destKey, final boolean useNewKeySerialize) {
        /*doInTedis(namespace,  new AbstractTedisBlock(namespace,String.valueOf(key) ,groupName) {
            @Override
            public Object execute() {
                commands.sUnionStore(rawKey(namespace, destKey, useNewKeySerialize), rawKeys(namespace, key, otherKeys, useNewKeySerialize));
                return null;
            }
        });*/
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param key
     * @param cursor
     * @param <H>
     * @param <K>
     * @return
     */
    @Override
    public <H, K> ScanResult<K> sscan(int namespace, H key, String cursor) {
        return sscan(namespace, key, cursor, false);
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param key
     * @param cursor
     * @param <H>
     * @param <K>
     * @return
     */
    @Override
    public <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, boolean isKeyStringSerialize) {
        return deserializeSScanResult((ScanResult<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.sscan(rawKey(namespace, key, isKeyStringSerialize), cursor.getBytes());
            }
        }));
    }

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param <H>
     * @param <K>
     * @return
     */
    @Override
    public <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, String pattern, int count) {
        return sscan(namespace, key, cursor,  pattern, count, false);
    }


    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param isKeyStringSerialize
     * @param <H>
     * @param <K>
     * @return
     */
    @Override
    public <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, String pattern, int count, boolean isKeyStringSerialize) {
        return deserializeSScanResult((ScanResult<byte[]>) doInTedis(namespace, new AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                ScanParams params = new ScanParams();
                params.count(count);
                params.match((namespace + ":" + pattern).getBytes());
                return commands.sscan(rawKey(namespace, key, isKeyStringSerialize), cursor.getBytes(), params);
            }
        }));
    }
}
