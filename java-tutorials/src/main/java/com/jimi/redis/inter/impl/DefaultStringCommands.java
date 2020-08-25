package com.jimi.redis.inter.impl;

import com.jimi.redis.inter.StringCommands;
import com.ucarinc.framework.client.redis.support.BitOP;
import com.zuche.redis.core.CommandsExecutor;
import com.zuche.redis.exception.RedisConnectionException;
import com.zuche.redis.exception.RedisRuntimeException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <br/> Created on 2014-7-3 下午1:59:08
 *
 *
 * @since 3.3
 */
public class DefaultStringCommands extends DefaultCommands implements StringCommands {

    public DefaultStringCommands(String groupName) {
        super(groupName);
    }

    @Override
    public Long append(final int namespace, final String key, final String value) {
        return append(namespace, key, value, false);
    }

    @Override
    public Long append(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.appendWithoutTry(namespace, key, value, useNewKeySerialize);
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
        throw new RedisException("执行append失败！");
    }

    private Long appendWithoutTry(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.append(rawKey(namespace, key, useNewKeySerialize), rawString(value));
            }
        });
    }

    @Override
    public String get(final int namespace, final Object key) {
        return get(namespace, key, false);
    }

    @Override
    public String get(final int namespace, final Object key, final boolean useNewKeySerialize) {
        return deserializeString((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.get(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }

    @Override
    public String get(final int namespace, final String key, final long start, final long end) {
        return get(namespace, key, start, end, false);
    }

    @Override
    public String get(final int namespace, final String key, final long start, final long end, final boolean useNewKeySerialize) {
        return deserializeString((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.getRange(rawKey(namespace, key, useNewKeySerialize), (int) start, (int) end);
            }
        }));
    }

    @Override
    public String getAndSet(final int namespace, final String key, final String value) {
        return getAndSet(namespace, key, value, false);
    }

    @Override
    public String getAndSet(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
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

    private String getAndSetWithoutTry(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
        return deserializeString((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.getSet(rawKey(namespace, key, useNewKeySerialize), rawString(value));
            }
        }));
    }

    @Override
    public List<String> multiGet(final int namespace, final Collection<String> keys) {
        return multiGet(namespace, keys, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> multiGet(int namespace, Collection<String> keys, boolean useNewKeySerialize) {
        CommandsExecutor<String, String> commandsExecutor = new CommandsExecutor<String, String>() {

            @Override
            public List<String> multiExecute(int namespace, List<String> partKeys, final byte[][] rawKeys) {
                return deserializeStrings((List<byte[]>) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(partKeys.get(0)), groupName, READ) {
                    @Override
                    public Object execute() {
                        return commands.mGet(rawKeys);
                    }
                }));
            }

            @Override
            public String singleExecute(int namespace,
                                        String key, boolean useNewKeySerialize) {
                return get(namespace, key, useNewKeySerialize);
            }

        };


        return super.multiGet(namespace, keys, useNewKeySerialize, commandsExecutor);
    }

    @Override
    public Boolean setexnx(int namespace, String key, String value, long timeout, TimeUnit unit) {
        return setexnx(namespace, key, value, timeout, unit, false);
    }

    @Override
    public Boolean setexnx(int namespace, String key, String value, long timeout, TimeUnit unit, boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.setExNxWithoutTry(namespace, key, value, timeout, unit, useNewKeySerialize);
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

    private <K, V> Boolean setExNxWithoutTry(final int namespace, final K key, final V value, final long timeout, final TimeUnit timeUnit, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.setNxEx(rawKey(namespace, key, useNewKeySerialize), timeUnit.toMillis(timeout), rawString(String.valueOf(value)));
            }
        });
    }

    @Override
    public Long bitcount(int namespace, String key) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Long bitcount(int namespace, String key, boolean isKeyStringSerialize) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Long bitcount(int namespace, String key, long start, long end) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Long bitcount(int namespace, String key, long start, long end, boolean isKeyStringSerialize) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Long bitop(BitOP bitOP, int namespace, String destKey, String... otherKeys) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Long bitop(boolean isKeyStringSerialize, BitOP bitOP, int namespace, String destKey, String... otherKeys) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Boolean setbit(int namespace, String key, long offset, boolean value) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Boolean setbit(int namespace, String key, long offset, boolean value, boolean isKeyStringSerialize) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Boolean getbit(int namespace, String key, long offset) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public Boolean getbit(int namespace, String key, long offset, boolean isKeyStringSerialize) {
        throw new RedisException("当前redis版本不支持命令");
    }

    @Override
    public void multiSet(final int namespace, final Map<String, String> m) {
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public void multiSetIfAbsent(final int namespace, final Map<String, String> m) {
        throw new RedisRuntimeException("不支持此方法");

    }

    @Override
    public void set(final int namespace, final String key, final String value) {
        set(namespace, key, value, false);
    }

    @Override
    public void set(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
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

    private void setWithoutTry(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.set(rawKey(namespace, key, useNewKeySerialize), rawString(value));
                return null;
            }
        });
    }

    @Override
    public void set(final int namespace, final String key, final String value, final long timeout, final TimeUnit unit) {
        set(namespace, key, value, timeout, unit, false);
    }

    @Override
    public void set(final int namespace, final String key, final String value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.setWithoutTry(namespace, key, value, timeout, unit, useNewKeySerialize);
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

    private void setWithoutTry(final int namespace, final String key, final String value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.psetEX(rawKey(namespace, key, useNewKeySerialize), unit.toMillis(timeout), rawString(value));
                return null;
            }
        });
    }

    @Override
    public void set(final int namespace, final String key, final String value, final long offset) {
        set(namespace, key, value, offset, false);
    }

    @Override
    public void set(final int namespace, final String key, final String value, final long offset, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                this.setWithoutTry(namespace, key, value, offset, useNewKeySerialize);
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

    private void setWithoutTry(final int namespace, final String key, final String value, final long offset, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.setRange(rawKey(namespace, key, useNewKeySerialize), rawString(value), offset);
                return null;
            }
        });
    }

    @Override
    public Boolean setIfAbsent(final int namespace, final String key, final String value) {
        return setIfAbsent(namespace, key, value, false);
    }

    @Override
    public Boolean setIfAbsent(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
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

    private Boolean setIfAbsentWithoutTry(final int namespace, final String key, final String value, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.setNX(rawKey(namespace, key, useNewKeySerialize), rawString(value));
            }
        });
    }

    @Override
    public Long size(final int namespace, final String key) {
        return size(namespace, key, false);
    }

    @Override
    public Long size(final int namespace, final String key, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName, READ) {
            @Override
            public Object execute() {
                return commands.strLen(rawKey(namespace, key, useNewKeySerialize));
            }
        });
    }

}
