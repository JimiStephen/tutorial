package com.jimi.redis.inter.impl;

import com.jimi.redis.inter.ListCommands;
import com.ucarinc.framework.client.redis.support.ListPosition;
import com.zuche.redis.binary.RedisListCommands.Position;
import com.zuche.redis.exception.RedisConnectionException;
import com.zuche.redis.exception.RedisRuntimeException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <br/> Created on 2014-7-3 下午1:55:37
 *
 *
 * @since 3.3
 */
public class DefaultListCommands extends DefaultCommands implements ListCommands {

    public DefaultListCommands(String groupName) {
        super(groupName);
    }

    @Override
    public <K, V> V index(final int namespace, final K key, final long index) {
        return index(namespace, key, index, false);
    }

    @Override
    public <K, V> V index(final int namespace, final K key, final long index, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.lIndex(rawKey(namespace, key, useNewKeySerialize), index);
            }
        }));
    }

    @Override
    public <K, V> V leftPop(final int namespace, final K key) {
        return leftPop(namespace, key, false);
    }

    @Override
    public <K, V> V leftPop(final int namespace, final K key, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.leftPopWithoutTry(namespace, key, useNewKeySerialize);
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
        throw new RedisException("执行leftPop失败！");
    }

    private <K, V> V  leftPopWithoutTry(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lPop(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> V leftPop(final int namespace, final K key, final long timeout, final TimeUnit unit) {
        return leftPop(namespace, key, timeout, unit, false);
    }

    @Override
    public <K, V> V leftPop(final int namespace, final K key, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.leftPopWithoutTry(namespace, key, timeout, unit, useNewKeySerialize);
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
        throw new RedisException("执行leftPop失败！");
    }

    private <K, V> V leftPopWithoutTry(final int namespace, final K key, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        final int tm = (int) unit.toSeconds(timeout);
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                List<byte[]> bytes = commands.bLPop(tm, rawKey(namespace, key, useNewKeySerialize));
                int thresholdValue = 2;
                if(bytes != null && bytes.size() == thresholdValue){
                    return bytes.get(1);
                }
                return null;
            }
        }));
    }

    @Override
    public <K, V> Long leftPush(final int namespace, final K key, final V... value) {
        return leftPush(false, namespace, key, value);
    }

    @Override
    public <K, V> Long leftPush(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.leftPushWithoutTry(useNewKeySerialize, namespace, key, value);
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
        throw new RedisException("执行leftPush失败！");
    }

    private <K, V> Long leftPushWithoutTry(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lPush(rawKey(namespace, key, useNewKeySerialize), rawValues(value));
            }
        });
    }

    @Override
    public <K, V> Long leftInsert(final int namespace, final K key, final V pivot, final V value) {
        return leftInsert(namespace, key, pivot, value, false);
    }

    @Override
    public <K, V> Long leftInsert(final int namespace, final K key, final V pivot, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.leftInsertWithoutTry(namespace, key, pivot, value, useNewKeySerialize);
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
        throw new RedisException("执行leftInsert失败！");
    }

    @Override
    public <K, V> Long leftInsert(int namespace, K key, ListPosition where, V pivot, V value) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    @Override
    public <K, V> Long leftInsert(int namespace, K key, ListPosition where, V pivot, V value, boolean useNewKeySerialize) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    private <K, V> Long leftInsertWithoutTry(final int namespace, final K key, final V pivot, final V value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lInsert(rawKey(namespace, key, useNewKeySerialize), Position.BEFORE, rawValue(pivot), rawValue(value));
            }
        });
    }

    @Override
    public <K, V> Long leftPushIfPresent(final int namespace, final K key, final V value) {
        return leftPushIfPresent(namespace, key, value, false);
    }

    @Override
    public <K, V> Long leftPushIfPresent(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.leftPushIfPresentWithoutTry(namespace, key, value, useNewKeySerialize);
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
        throw new RedisException("执行leftPushIfPresent失败！");
    }

    @Override
    public <K, V> Long leftPushIfPresent(int namespace, K key, V[] value) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    @Override
    public <K, V> Long leftPushIfPresent(boolean useNewKeySerialize, int namespace, K key, V[] value) {
        throw new RedisException("当前redis版本不支持该命令！");
    }

    private <K, V> Long leftPushIfPresentWithoutTry(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lPushX(rawKey(namespace, key, useNewKeySerialize), rawValue(value));
            }
        });
    }

    @Override
    public <K, V> List<V> range(final int namespace, final K key, final long start, final long end) {
        return range(namespace, key, start, end, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> List<V> range(final int namespace, final K key, final long start, final long end, final boolean useNewKeySerialize) {
        return deserializeValues((List<byte[]>) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.lRange(rawKey(namespace, key, useNewKeySerialize), start, end);
            }
        }));
    }

    @Override
    public <K, V> Long remove(final int namespace, final K key, final long i, final Object value) {
        return remove(namespace, key, i, value, false);
    }

    @Override
    public <K, V> Long remove(final int namespace, final K key, final long i, final Object value, final boolean useNewKeySerialize) {
        for (int j = 0; j < REPEAT_NUM; j++) {
            try {
                return this.removeWithoutTry(namespace, key, i, value, useNewKeySerialize);
            } catch (RedisConnectionException e) {
                if (ERROR_MESSAGE.equals(e.getMessage()) && j < REPEAT_NUM - 1) {
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
        throw new RedisException("执行list的remove失败！");
    }

    private <K, V> Long removeWithoutTry(final int namespace, final K key, final long i, final Object value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lRem(rawKey(namespace, key, useNewKeySerialize), i, rawValue(value));
            }
        });
    }

    @Override
    public <K, V> V rightPop(final int namespace, final K key) {
        return rightPop(namespace, key, false);
    }

    @Override
    public <K, V> V rightPop(final int namespace, final K key, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.rightPopWithoutTry(namespace, key, useNewKeySerialize);
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
        throw new RedisException("执行rightPop失败！");
    }

    private <K, V> V rightPopWithoutTry(final int namespace, final K key, final boolean useNewKeySerialize) {
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.rPop(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public <K, V> V rightPop(final int namespace, final K key, final long timeout, final TimeUnit unit) {
        return rightPop(namespace, key, timeout, unit, false);
    }

    @Override
    public <K, V> V rightPop(final int namespace, final K key, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.rightPopWithoutTry(namespace, key, timeout, unit, useNewKeySerialize);
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
        throw new RedisException("执行rightPop失败！");
    }

    private <K, V> V rightPopWithoutTry(final int namespace, final K key, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        final int tm = (int) unit.toSeconds(timeout);
        return deserializeValue((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                List<byte[]> bytes = commands.bRPop(tm, rawKey(namespace, key, useNewKeySerialize));
                int thresholdValue = 2;
                if(bytes != null && bytes.size() == thresholdValue){
                    return bytes.get(1);
                }
                return null;
            }
        }));
    }

    @Override
    public <K, V> V rightPopAndLeftPush(final int namespace, final K sourceKey, final K destinationKey) {
        return rightPopAndLeftPush(namespace, sourceKey, destinationKey, false);
    }

    @Override
    public <K, V> V rightPopAndLeftPush(final int namespace, final K sourceKey, final K destinationKey, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> V rightPopAndLeftPush(final int namespace, final K sourceKey, final K destinationKey, long timeout, TimeUnit unit) {
        return rightPopAndLeftPush(namespace, sourceKey, destinationKey, timeout, unit, false);
    }

    @Override
    public <K, V> V rightPopAndLeftPush(final int namespace, final K sourceKey, final K destinationKey, long timeout, TimeUnit unit, final boolean useNewKeySerialize) {
        /**
         * 此方法只适用于同一台redis，由于目前采用redis集群，故暂不支持 此方法
         */
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public <K, V> Long rightPush(final int namespace, final K key, final V... value) {
        return rightPush(false, namespace, key, value);
    }

    @Override
    public <K, V> Long rightPush(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.rightPushWithoutTry(useNewKeySerialize, namespace, key, value);
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
        throw new RedisException("执行rightPush失败！");
    }

    private <K, V> Long rightPushWithoutTry(final boolean useNewKeySerialize, final int namespace, final K key, final V... value) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.rPush(rawKey(namespace, key, useNewKeySerialize), rawValues(value));
            }
        });
    }

    @Override
    public <K, V> Long rightInsert(final int namespace, final K key, final V pivot, final V value) {
        return rightInsert(namespace, key, pivot, value, false);
    }

    @Override
    public <K, V> Long rightInsert(final int namespace, final K key, final V pivot, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.rightInsertWithoutTry(namespace, key, pivot, value, useNewKeySerialize);
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
        throw new RedisException("执行rightInsert失败！");
    }

    private <K, V> Long rightInsertWithoutTry(final int namespace, final K key, final V pivot, final V value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.lInsert(rawKey(namespace, key, useNewKeySerialize), Position.AFTER, rawValue(pivot), rawValue(value));
            }
        });
    }

    @Override
    public <K, V> Long rightPushIfPresent(final int namespace, final K key, final V value) {
        return rightPushIfPresent(namespace, key, value, false);
    }

    @Override
    public <K, V> Long rightPushIfPresent(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.rightPushIfPresentWithoutTry(namespace, key, value, useNewKeySerialize);
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
        throw new RedisException("执行rightPushIfPresent失败！");
    }

    private <K, V> Long rightPushIfPresentWithoutTry(final int namespace, final K key, final V value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.rPushX(rawKey(namespace, key, useNewKeySerialize), rawValue(value));
            }
        });
    }

    @Override
    public <K, V> void set(final int namespace, final K key, final long index, final V value) {
        set(namespace, key, index, value, false);
    }

    @Override
    public <K, V> void set(final int namespace, final K key, final long index, final V value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.setWithoutTry(namespace, key, index, value, useNewKeySerialize);
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
        throw new RedisException("执行list的set失败！");
    }

    private <K, V> void setWithoutTry(final int namespace, final K key, final long index, final V value, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.lSet(rawKey(namespace, key, useNewKeySerialize), index, rawValue(value));
                return null;
            }
        });
    }

    @Override
    public <K, V> Long size(final int namespace, final K key) {
        return size(namespace, key, false);
    }

    @Override
    public <K, V> Long size(final int namespace, final K key, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.lLen(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }

    @Override
    public <K, V> void trim(final int namespace, final K key, final long start, final long end) {
        trim(namespace, key, start, end, false);
    }

    @Override
    public <K, V> void trim(final int namespace, final K key, final long start, final long end, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.trimWithoutTry(namespace, key, start, end, useNewKeySerialize);
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
        throw new RedisException("执行list的trim失败！");
    }

    private <K, V> void trimWithoutTry(final int namespace, final K key, final long start, final long end, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.lTrim(rawKey(namespace, key, useNewKeySerialize), start, end);
                return null;
            }
        });
    }

}
