package com.jimi.redis.inter;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * hash 操作
 * <p>
 * <br/> Created on 2014-7-3 下午1:47:10
 *
 *
 * @since 3.3
 */
public interface HashCommands extends Commands {

    /**
     * 删除<tt>key</tt>的Hash对象的<tt>hashKey</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象的子键
     * @return , 是否成功
     */
    <H, HK, HV> boolean ndelete(int namespace, H key, Object... hashKey);

    /**
     * 删除<tt>key</tt>的Hash对象的<tt>hashKey</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @param hashKey            hash对象的子键
     * @return , 是否成功
     */
    <H, HK, HV> boolean ndelete(boolean useNewKeySerialize, int namespace, H key, Object... hashKey);

    /**
     * 判断<tt>key</tt>的Hash对象的<tt>hashKey</tt>是否存在
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象的子键
     * @return 存在返回<tt>true</tt>，不存在返回<tt>false</tt>
     */
    <H, HK, HV> Boolean hasKey(int namespace, H key, Object hashKey);

    /**
     * 判断<tt>key</tt>的Hash对象的<tt>hashKey</tt>是否存在
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKey            hash对象的子键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 存在返回<tt>true</tt>，不存在返回<tt>false</tt>
     */
    <H, HK, HV> Boolean hasKey(int namespace, H key, Object hashKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Hash对象的<tt>hashKey</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象的子键
     * @return 返回hashkey对应的value
     */
    <H, HK, HV> HV get(int namespace, H key, Object hashKey);

    /**
     * 取得<tt>key</tt>的Hash对象的<tt>hashKey</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKey            hash对象的子键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回hashkey对应的value
     */
    <H, HK, HV> HV get(int namespace, H key, Object hashKey, boolean useNewKeySerialize);

    /**
     * 批量取得数据
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKeys  多个hash对象的子键
     * @return 返回多个子键对应的值
     */
    <H, HK, HV> Collection<HV> multiGet(int namespace, H key, Collection<HK> hashKeys);

    /**
     * 批量取得数据
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKeys           多个hash对象的子键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回多个子键对应的值
     */
    <H, HK, HV> Collection<HV> multiGet(int namespace, H key, Collection<HK> hashKeys, boolean useNewKeySerialize);

    /**
     * 计数操作方法 <tt>delta</tt>可为正、负、0值。正值为增加计数、负值为减少计数、0为取得当前值
     * 此方法为只是hash的计数方法，不能操作由put方法维护的value
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象的子键
     * @param delta     步长
     * @return 当前hashkey的计数
     */
    <H, HK, HV> Long increment(int namespace, H key, HK hashKey, long delta);

    /**
     * 计数操作方法 <tt>delta</tt>可为正、负、0值。正值为增加计数、负值为减少计数、0为取得当前值
     * 此方法为只是hash的计数方法，不能操作由put方法维护的value
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKey            hash对象的子键
     * @param delta              步长
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 当前hashkey的计数
     */
    <H, HK, HV> Long increment(int namespace, H key, HK hashKey, long delta, boolean useNewKeySerialize);

    /**
     * 指定key的hash的field字段值执行float类型的increment加。如果field不存在，则在执行该操作前设置为0
     *
     * @param namespace
     * @param key
     * @param fieldKey
     * @param increment
     * @param <K>
     * @return field执行increment加后的值
     */
    <K, HK> Double incrementByFloat(int namespace, K key, HK fieldKey, double increment);

    /**
     * 指定key的hash的field字段值执行float类型的increment加。如果field不存在，则在执行该操作前设置为0
     *
     * @param namespace
     * @param key
     * @param fieldKey
     * @param increment
     * @param <K>
     * @param useNewKeySerialize
     * @return field执行increment加后的值
     */
    <K, HK> Double incrementByFloat(int namespace, K key, HK fieldKey, double increment, boolean useNewKeySerialize);

    /**
     * 得到<tt>key</tt>的Hash对象的所有key
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @return 返回hash对象的所有key
     */
    <H, HK, HV> Set<HK> hKeys(int namespace, H key);

    /**
     * 得到<tt>key</tt>的Hash对象的所有key
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回hash对象的所有key
     */
    <H, HK, HV> Set<HK> hKeys(int namespace, H key, boolean useNewKeySerialize);

    /**
     * 缺的<tt>key</tt>的Hash对象的大小
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @return hash对象的大小
     */
    <H, HK, HV> Long size(int namespace, H key);

    /**
     * 缺的<tt>key</tt>的Hash对象的大小
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return hash对象的大小
     */
    <H, HK, HV> Long size(int namespace, H key, boolean useNewKeySerialize);

    /**
     * 批量设置数据
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param m         设置的多个key和value数据
     */
    <H, HK, HV> void putAll(int namespace, H key, Map<? extends HK, ? extends HV> m);

    /**
     * 批量设置数据
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param m                  设置的多个key和value数据
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <H, HK, HV> void putAll(int namespace, H key, Map<? extends HK, ? extends HV> m, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据存在，则覆盖，如果数据不存在，则新增
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象里面的子键
     * @param value     子键对应的值
     */
    <H, HK, HV> void put(int namespace, H key, HK hashKey, HV value);

    /**
     * 设置数据，如果数据存在，则覆盖，如果数据不存在，则新增
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKey            hash对象里面的子键
     * @param value              子键对应的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <H, HK, HV> void put(int namespace, H key, HK hashKey, HV value, boolean useNewKeySerialize);

    /**
     * 设置数据，只有数据不存在才能设置成功
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param hashKey   hash对象里面的子键
     * @param value     子键对应的值
     * @return 是否成功
     */
    <H, HK, HV> Boolean putIfAbsent(int namespace, H key, HK hashKey, HV value);

    /**
     * 设置数据，只有数据不存在才能设置成功
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param hashKey            hash对象里面的子键
     * @param value              子键对应的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 是否成功
     */
    <H, HK, HV> Boolean putIfAbsent(int namespace, H key, HK hashKey, HV value, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Hash对象的所有值
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @return 值返回hash对象的所有value
     */
    <H, HK, HV> Collection<HV> values(int namespace, H key);

    /**
     * 取得<tt>key</tt>的Hash对象的所有值
     *
     * @param namespace          数据所在的namespace
     * @param key                hash对象的键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回Hash对象的所有值
     */
    <H, HK, HV> Collection<HV> values(int namespace, H key, boolean useNewKeySerialize);

    /**
     * 缺的<tt>key</tt>的Hash对象的所有key、value
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return 获取指定hash对象的所有key和value
     */
    <H, HK, HV> Map<HK, HV> entries(int namespace, H key);

    /**
     * 缺的<tt>key</tt>的Hash对象的所有key、value
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 获取指定hash对象的所有key和value
     */
    <H, HK, HV> Map<HK, HV> entries(int namespace, H key, boolean useNewKeySerialize);


    /**
     * 批量获取Hash对象的所有key、value
     *
     * @param namespace 数据所在的namespace
     * @param keys      指定key的集合
     * @return 批量获取Hash对象的所有key、value
     */
    <H, HK, HV> List<Map<HK, HV>> multiEntries(int namespace, Collection<H> keys);

    /**
     * 批量获取Hash对象的所有key、value
     *
     * @param namespace          数据所在的namespace
     * @param keys               指定key的集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @param isThread           是否启用多线程
     * @return 批量获取Hash对象的所有key、value
     */
    <H, HK, HV> List<Map<HK, HV>> multiEntries(int namespace, Collection<H> keys, boolean useNewKeySerialize, boolean isThread);

    /**
     * 缺的<tt>key</tt>的hash对象的某个field字符对长度
     *
     * @param namespace 数据所在的namespace
     * @param key       hash对象的键
     * @param fieldKey
     * @return hash对象的某个field字符对长度
     */
    <K, HK> Long hstrlen(int namespace, K key, HK fieldKey);

    /**
     * 缺的<tt>key</tt>的Hash对象的某个field字符对长度
     *
     * @param namespace           数据所在的namespace
     * @param key                 hash对象的键
     * @param fieldKey
     * @param isKeyStringSerialize 是否使用Hessian序列化
     * @return hash对象的某个field字符对长度
     */
    <K, HK> Long hstrlen(int namespace, K key, HK fieldKey, boolean isKeyStringSerialize);

    /**
     * hscan，迭代查询
     * @param namespace
     * @param key
     * @param cursor
     * @param <H>
     * @param <HK>
     * @param <HV>
     * @return
     */
    <H, HK, HV> ScanResult<Map<HK, HV>> hscan(int namespace, H key, String cursor);

    /**
     * hscan
     * @param namespace
     * @param key
     * @param cursor
     * @param isKeyStringSerialize
     * @param <H>
     * @param <HK>
     * @param <HV>
     * @return
     */
    <H, HK, HV> ScanResult<Map<HK, HV>> hscan(int namespace, H key, String cursor, boolean isKeyStringSerialize);

    /**
     * hscan
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param <H>
     * @param <HK>
     * @param <HV>
     * @return
     */
    <H, HK, HV> ScanResult<Map<HK, HV>> hscan(int namespace, H key, String cursor, String pattern, int count);

    /**
     * hscan
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param isKeyStringSerialize
     * @param <H>
     * @param <HK>
     * @param <HV>
     * @return
     */
    <H, HK, HV> ScanResult<Map<HK, HV>> hscan(int namespace, H key, String cursor, String pattern, int count, boolean isKeyStringSerialize);

}
