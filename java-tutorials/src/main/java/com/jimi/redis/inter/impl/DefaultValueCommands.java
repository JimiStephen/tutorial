package com.jimi.redis.inter.impl;

import com.jimi.redis.inter.ValueCommands;
import com.zuche.redis.core.CommandsExecutor;
import com.zuche.redis.exception.RedisConnectionException;
import com.zuche.redis.exception.RedisRuntimeException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <br/> Created on 2014-7-3 下午2:08:55
 *
 *
 * @since 3.3
 */
public class DefaultValueCommands extends DefaultCommands implements ValueCommands {

    public DefaultValueCommands(String groupName) {
        super(groupName);
//    	localCache = new DefaultLocalCache();
    }

    @Override
    public <K, V> V get(final int namespace, final K key) {
        return get(namespace, key, false);
    }

    @Override
    public <K, V> V get(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.get(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> V getAndSet(final int namespace, final K key, final V value) {
        return getAndSet(namespace, key, value, false);
    }

    @Override
    public <K, V> V getAndSet(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.getAndSetWithoutTry(namespace, key, value, useNewKeySerialize);
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
        throw new RedisException("执行getAndSet失败！");
    }

    private <K, V> V getAndSetWithoutTry(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.getSet(rawKey(namespace, key, useNewKeySerialize), rawValue(value));
            }
        }));
    }

    @Override
    public <K, V> List<V> multiGet(final int namespace, final Collection<K> keys) {
        return multiGet(namespace, keys, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> List<V> multiGet(final int namespace, final Collection<K> keys, final boolean useNewKeySerialize) {

        CommandsExecutor<K, V> commandsExecutor = new CommandsExecutor<K, V>() {

            @Override
            public List<V> multiExecute(int namespace, List<K> partKeys, final byte[][] rawKeys) {
                return deserializeHashValues((List<byte[]>) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(partKeys.get(0)), groupName, READ) {
                    @Override
                    public Object execute() {
                        return commands.mGet(rawKeys);
                    }
                }));
            }

            @Override
            public V singleExecute(int namespace,
                                   K key, boolean useNewKeySerialize) {
                return get(namespace, key, useNewKeySerialize);
            }

        };


        return super.multiGet(namespace, keys, useNewKeySerialize, commandsExecutor);

    }

    @Override
    public <K, V> void multiSet(final int namespace, final Map<? extends K, ? extends V> m) {
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> void multiSetIfAbsent(final int namespace, final Map<? extends K, ? extends V> m) {
        throw new RedisRuntimeException("不支持此方法");

    }

    @Override
    public <K, V> void set(final int namespace, final K key, final V value) {
        set(namespace, key, value, false);
    }

    @Override
    public <K, V> void set(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.setWithoutTry(namespace, key, value, useNewKeySerialize);
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

    private <K, V> void setWithoutTry(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.set(rawKey(namespace, key, useNewKeySerialize), rawValue(value));
                return null;
            }
        });
    }

    @Override
    public <K, V> void set(final int namespace, final K key, final V value, final long timeout, final TimeUnit unit) {
        set(namespace, key, value, timeout, unit, false);
    }

    @Override
    public <K, V> void set(final int namespace, final K key, final V value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.setWithoutTry(namespace, key, value, timeout, unit, useNewKeySerialize);
                return;
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && i < 2) {
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

    private <K, V> void setWithoutTry(final int namespace, final K key, final V value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.psetEX(rawKey(namespace, key, useNewKeySerialize),  unit.toMillis(timeout), rawValue(value));
                return null;
            }
        });
    }

    @Override
    public <K, V> Boolean setIfAbsent(final int namespace, final K key, final V value) {
        return setIfAbsent(namespace, key, value, false);
    }

    @Override
    public <K, V> Boolean setIfAbsent(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.setIfAbsentWithoutTry(namespace, key, value, useNewKeySerialize);
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
        throw new RedisException("执行setIfAbsent失败！");
    }

    private <K, V> Boolean setIfAbsentWithoutTry(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.setNX(rawKey(namespace, key, useNewKeySerialize), rawValue(value));
            }
        });
    }


    @Override
    public <K, V> Boolean setIfAbsent(final int namespace, final K key, final V value, final long timeout, final TimeUnit timeUnit) {
        return setIfAbsent(namespace, key, value, timeout, timeUnit, false);
    }

    @Override
    public <K, V> Boolean setIfAbsent(final int namespace, final K key, final V value, final long timeout, final TimeUnit timeUnit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.setIfAbsentExWithoutTry(namespace, key, value, timeout, timeUnit, useNewKeySerialize);
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
        throw new RedisException("执行setIfAbsent失败！");
    }

    private <K, V> Boolean setIfAbsentExWithoutTry(final int namespace, final K key, final V value, final long timeout, final TimeUnit timeUnit, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.setNxEx(rawKey(namespace, key, useNewKeySerialize), timeUnit.toMillis(timeout), rawValue(value));
            }
        });
    }

    @Override
    public <K, V> List<V> multiGet(int namespace, Collection<K> keys, long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <K, V> List<V> multiGet(int namespace, Collection<K> keys, Date expireAt) {
        throw new UnsupportedOperationException();
    }

}
