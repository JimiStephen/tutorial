package com.jimi.redis.inter;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * * 基本操作入口，包括：
 * <ul>
 * <li>基本的基于key的操作</li>
 * <li>取得其他各数据结构操作入口</li>
 * <li>排序</li>
 * <li>取得当前序列化方式</li>
 * </ul>
 * <p>
 * <br/> Created on 2014-7-3 下午1:50:43
 *
 * @since 3.3
 */
public interface Commands {

    /**
     * <tt>key</tt>是否存在
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return Boolean
     */
    <K> Boolean hasKey(int namespace, K key);

    /**
     * <tt>key</tt>是否存在
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return boolean
     */
    <K> Boolean hasKey(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 删除指定<tt>key</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     */
    <K> void delete(int namespace, K key);

    /**
     * 删除指定<tt>key</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K> void delete(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 批量删除<tt>key</tt>
     *
     * @param namespace 数据所在的namespace
     * @param keys      键的集合
     */
    <K> void delete(int namespace, Collection<K> keys);

    /**
     * 取得<tt>key</tt>的类型
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return 键的类型
     */
    <K> DataType type(int namespace, K key);

    /**
     * 取得<tt>key</tt>的类型
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 键的类型
     */
    <K> DataType type(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param cursor
     * @param pattern
     * @param count
     * @param <K>
     * @return
     */
    <K> ScanResult<K> scan(int namespace, String cursor, String pattern, int count);


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
    <K> ScanResult<K> scan(int namespace, String cursor, String pattern, int count, boolean isKeyStringSerialize);


    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param pattern eg：*key*
     * @param <K>
     * @return
     */
    public <K> Set<K> scanAllKeys(int namespace, String pattern);


    /**
     * 取得<tt>pattern</tt>匹配的<tt>key</tt>集合
     * @param namespace
     * @param pattern eg：*key*
     * @param isKeyStringSerialize
     * @param isThread
     * @param <K>
     * @return
     */
    <K> Set<K> scanAllKeys(int namespace, String pattern, boolean isKeyStringSerialize, boolean isThread);

    /**
     * 重命名<tt>key</tt>
     *
     * @param namespace 数据所在的namespace
     * @param oldKey    旧键
     * @param newKey    新键
     */
    <K> void rename(int namespace, K oldKey, K newKey);

    /**
     * 重命名<tt>key</tt>
     *
     * @param namespace          数据所在的namespace
     * @param oldKey             旧键
     * @param newKey             新键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K> void rename(int namespace, K oldKey, K newKey, boolean useNewKeySerialize);

    /**
     * 如果<tt>oldKey</tt>存在则重命名<tt>oldKey</tt>为<tt>newKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param oldKey    旧键
     * @param newKey    新键
     * @return boolean
     */
    <K> Boolean renameIfAbsent(int namespace, K oldKey, K newKey);

    /**
     * 如果<tt>oldKey</tt>存在则重命名<tt>oldKey</tt>为<tt>newKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param oldKey             旧键
     * @param newKey             新键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return boolean
     */
    <K> Boolean renameIfAbsent(int namespace, K oldKey, K newKey, boolean useNewKeySerialize);

    /**
     * 设置<tt>key</tt>的过期时间
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param timeout   超时时间
     * @param unit      时间单位
     * @return boolean
     */
    <K> Boolean expire(int namespace, K key, long timeout, TimeUnit unit);

    /**
     * 设置<tt>key</tt>的过期时间
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param timeout            超时时间
     * @param unit               时间单位
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return boolean
     */
    <K> Boolean expire(int namespace, K key, long timeout, TimeUnit unit, boolean useNewKeySerialize);

    /**
     * 设置<tt>key</tt>的过期时间
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param date      日期
     * @return boolean
     */
    <K> Boolean expireAt(int namespace, K key, Date date);

    /**
     * 设置<tt>key</tt>的过期时间
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param date               日期
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return
     */
    <K> Boolean expireAt(int namespace, K key, Date date, boolean useNewKeySerialize);

    /**
     * 取消<tt>key</tt>的过期设置
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return boolean
     */
    <K> Boolean persist(int namespace, K key);

    /**
     * 取消<tt>key</tt>的过期设置
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return boolean
     */
    <K> Boolean persist(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的过期时间(秒数）
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return Long
     */
    <K> Long getExpire(int namespace, K key);

    /**
     * 取得<tt>key</tt>的过期时间（秒数）
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return Long
     */
    <K> Long getExpire(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的过期时间(毫秒数）
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return Long
     */
    <K> Long getExpireMs(int namespace, K key);

    /**
     * 取得<tt>key</tt>的过期时间（毫秒数）
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return Long
     */
    <K> Long getExpireMs(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 排序
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param params    参数
     * @return List集合
     */
    <K, V> List<V> sort(int namespace, K key, SortParams params);

    /**
     * 排序
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param params             参数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return list集合
     */
    <K, V> List<V> sort(int namespace, K key, SortParams params, boolean useNewKeySerialize);

    /**
     * 排序
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param params    参数
     * @param storeKey  排序的键
     * @return long
     */
    <K> Long sort(int namespace, K key, SortParams params, K storeKey);

    /**
     * 排序
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param params             参数
     * @param storeKey           排序的key
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return long
     */
    <K> Long sort(int namespace, K key, SortParams params, K storeKey, boolean useNewKeySerialize);

}
