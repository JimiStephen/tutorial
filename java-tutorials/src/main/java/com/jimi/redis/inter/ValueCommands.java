package com.jimi.redis.inter;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 简单的key value对象操作
 * <p>
 * <br/> Created on 2014-7-3 下午1:50:30
 *
 *
 * @since 3.3
 */
public interface ValueCommands extends Commands {

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要覆盖或者新增的值
     */
    <K, V> void set(int namespace, K key, V value);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要覆盖或者新增的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void set(int namespace, K key, V value, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要覆盖或者新增的值
     * @param timeout   数据的有效时间
     * @param unit      时间单位
     */
    <K, V> void set(int namespace, K key, V value, long timeout, TimeUnit unit);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要覆盖或者新增的值
     * @param timeout            数据的有效时间
     * @param unit               时间单位
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void set(int namespace, K key, V value, long timeout, TimeUnit unit, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要新增的值
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    <K, V> Boolean setIfAbsent(int namespace, K key, V value);

    /**
     * 设置数据，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要新增的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    <K, V> Boolean setIfAbsent(int namespace, K key, V value, boolean useNewKeySerialize);

    /**
     * 设置数据的同时设置过期时间，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要新增的值
     * @param timeout   数据的有效时间
     * @param unit      时间单位
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    <K, V> Boolean setIfAbsent(int namespace, K key, V value, long timeout, TimeUnit unit);

    /**
     * 设置数据的同时设置过期时间，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要新增的值
     * @param timeout            数据的有效时间
     * @param unit               时间单位
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    <K, V> Boolean setIfAbsent(int namespace, K key, V value, long timeout, TimeUnit unit, boolean useNewKeySerialize);

    /**
     * 批量设置数据
     *
     * @param namespace 数据所在的namespace
     * @param m         多个key,value的集合
     */
    <K, V> void multiSet(int namespace, Map<? extends K, ? extends V> m);

    /**
     * 批量设置数据，只有当数据不存在时才设置
     *
     * @param namespace 数据所在的namespace
     * @param m         多个key,value的集合
     */
    <K, V> void multiSetIfAbsent(int namespace, Map<? extends K, ? extends V> m);

    /**
     * 获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return 返回key的数据
     */
    <K, V> V get(int namespace, K key);

    /**
     * 获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key的数据
     */
    <K, V> V get(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 设置数据同时返回老数据，此方法是原子操作 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要设置的数据
     * @return 返回key的老数据，不是value
     */
    <K, V> V getAndSet(int namespace, K key, V value);

    /**
     * 设置数据同时返回老数据，此方法是原子操作 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要设置的数据
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key的老数据，不是value
     */
    <K, V> V getAndSet(int namespace, K key, V value, boolean useNewKeySerialize);

    /**
     * 批量获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace
     * @param keys
     * @return
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys);

    /**
     * 批量获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param keys               多个key的集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回keys对应的value列表
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys, boolean useNewKeySerialize);

    /**
     * 使用LocalCache的方式获取数据，降低频繁交互造成的性能损失<br/>
     * 首先到LocalCache中去取数据，如果取不到，则到Redis中取，并且把取得的值按照超时时间放入LocalCache
     *
     * @param namespace 数据所在的namespace
     * @param keys      多个key的集合
     * @param timeout   数据的有效时间
     * @param unit      时间单位
     * @return 返回keys对应的value列表
     * @since V1.0.1
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys, long timeout, TimeUnit unit);

    /**
     * 使用LocalCache的方式获取数据，降低频繁交互造成的性能损失<br/>
     * 首先到LocalCache中去取数据，如果取不到，则到Redis中取，并且把取得的值按照超时时间放入LocalCache
     *
     * @param namespace 数据所在的namespace
     * @param keys      多个key的集合
     * @param expireAt  过期的截止时间
     * @return 返回keys对应的value列表
     * @since V1.0.1
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys, Date expireAt);

}
