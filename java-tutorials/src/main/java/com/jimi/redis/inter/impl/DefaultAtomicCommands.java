package com.jimi.redis.inter.impl;


import com.jimi.redis.inter.AtomicCommands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <br/> Created on 2014-7-3 下午1:55:06
 *
 *
 * @since 3.3
 */
public class DefaultAtomicCommands extends DefaultCommands implements AtomicCommands {

    public DefaultAtomicCommands(String groupName) {
        super(groupName);
    }

    @Override
    public long get(final int namespace, final Object key) {
        return get(namespace, key, false);
    }

    @Override
    public long get(final int namespace, final Object key, final boolean useNewKeySerialize) {
        return deserializeLong((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.get(rawKey(namespace, key, useNewKeySerialize));
            }
        }));
    }


    @Override
    public long getAndSet(final int namespace, final Object key, final long value) {
        return getAndSet(namespace, key, value, false);
    }

    @Override
    public long getAndSet(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
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

    private long getAndSetWithoutTry(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
        return deserializeLong((byte[]) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.getSet(rawKey(namespace, key, useNewKeySerialize), rawLong(value));
            }
        }));
    }

    @Override
    public Long increment(final int namespace, final Object key, final long delta) {
        return increment(namespace, key, delta, false);
    }

    @Override
    public Long increment(final int namespace, final Object key, final long delta, final boolean useNewKeySerialize) {
        for (int i = 0; i < REPEAT_NUM; i++) {
            try {
                return this.incrementWithoutTry(namespace, key, delta, useNewKeySerialize);
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
        throw new RedisException("执行increment失败！");
    }

    @Override
    public Double increment(final int namespace, final Object key, final double delta) {
        return increment(namespace, key, delta,false);
    }

    @Override
    public Double increment(final int namespace, final Object key, final double delta, final boolean useNewKeySerialize) {
        for(int i = 0 ;i < REPEAT_NUM;i++){
            try {
                return this.incrementWithoutTry(namespace, key, delta, useNewKeySerialize);
            } catch (RedisConnectionException e) {
                if(ERROR_MESSAGE.equals(e.getMessage()) && i < REPEAT_NUM - 1) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e1) {
                    }
                    continue;
                }else {
                    throw new RedisException(e);
                }
            }
        }
        throw new RedisException("执行increment失败！");
    }

    private Double incrementWithoutTry(final int namespace, final Object key, final double delta, final boolean useNewKeySerialize) {
        return (Double)doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace,String.valueOf(key) ,groupName) {
            @Override
            public Object execute() {
                byte[] rawKey = rawKey(namespace, key, useNewKeySerialize);
                return commands.incrByFloat(rawKey, delta);
            }
        });
    }

    private Long incrementWithoutTry(final int namespace, final Object key, final long delta, final boolean useNewKeySerialize) {
        return (Long) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                byte[] rawKey = rawKey(namespace, key, useNewKeySerialize);
                if (delta == 1) {
                    return commands.incr(rawKey);
                }
                if (delta == -1) {
                    return commands.decr(rawKey);
                }
                return commands.incrBy(rawKey, delta);
            }
        });
    }


    @Override
    public List<Long> multiGet(final int namespace, final Collection<? extends Object> keys) {
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public void multiSet(final int namespace, final Map<? extends Object, Long> m) {
        throw new RedisRuntimeException("不支持此方法");
    }

    @Override
    public void multiSetIfAbsent(final int namespace, final Map<? extends Object, Long> m) {
        throw new RedisRuntimeException("不支持此方法");

    }

    @Override
    public void set(final int namespace, final Object key, final long value) {
        set(namespace, key, value, false);
    }

    @Override
    public void set(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
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

    public void setWithoutTry(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.set(rawKey(namespace, key, useNewKeySerialize), rawLong(value));
                return null;
            }
        });
    }

    @Override
    public void set(final int namespace, final Object key, final long value, final long timeout, final TimeUnit unit) {
        set(namespace, key, value, timeout, unit, false);
    }

    @Override
    public void set(final int namespace, final Object key, final long value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
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

    private void setWithoutTry(final int namespace, final Object key, final long value, final long timeout, final TimeUnit unit, final boolean useNewKeySerialize) {
        doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                commands.psetEX(rawKey(namespace, key, useNewKeySerialize),  unit.toMillis(timeout), rawLong(value));
                return null;
            }
        });
    }

    @Override
    public Boolean setIfAbsent(final int namespace, final Object key, final long value) {
        return setIfAbsent(namespace, key, value, false);
    }

    @Override
    public Boolean setIfAbsent(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
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

    private Boolean setIfAbsentWithoutTry(final int namespace, final Object key, final long value, final boolean useNewKeySerialize) {
        return (Boolean) doInTedis(namespace, new BaseCommands.AbstractTedisBlock(namespace, String.valueOf(key), groupName) {
            @Override
            public Object execute() {
                return commands.setNX(rawKey(namespace, key, useNewKeySerialize), rawLong(value));
            }
        });
    }

}
