package com.jimi.redis.inter;


import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Set 操作
 * <p>
 * <br/> Created on 2014-7-3 下午1:48:25
 *
 * @since 3.3
 */
public interface SetCommands extends Commands {

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKey</tt>的Set的数据<br />
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c,d}
     * difference(key1, key2) = {a,b}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @return 返回key中有，而otherKey中没有的数据
     */
    <K, V> Set<V> difference(int namespace, K key, K otherKey);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKey</tt>的Set的数据<br />
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c,d}
     * difference(key1, key2) = {a,b}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key中有，而otherKey中没有的数据
     */
    <K, V> Set<V> difference(int namespace, K key, K otherKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKeys</tt>的Set的数据<br />
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,d}
     * otherKeys = {key2,key3}
     * difference(key1, otherKeys) = {b,d}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKeys 其它Set集合
     * @return 返回key中有，而otherKeys中没有的数据
     */
    <K, V> Set<V> difference(int namespace, K key, Collection<K> otherKeys);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKeys</tt>的Set的数据<br />
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,d}
     * otherKeys = {key2,key3}
     * difference(key1, otherKeys) = {b,d}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key中有，而otherKeys中没有的数据
     */
    <K, V> Set<V> difference(int namespace, K key, Collection<K> otherKeys, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKey</tt>的Set的数据，将数据存到<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * destKey = differenceAndStore(key1, key2)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void differenceAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKey</tt>的Set的数据，将数据存到<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * destKey = differenceAndStore(key1, key2)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void differenceAndStore(int namespace, K key, K otherKey, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKeys</tt>的Set的数据，将数据存到<tt>destKey</tt>
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {a}
     * key3 = {a,c}
     * otherKeys = {key2,key3}
     * destKey = differenceAndStore(key1, otherKeys)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKeys 其它Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void differenceAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * 取得<tt>key</tt>的Set中不存在于<tt>otherKeys</tt>的Set的数据，将数据存到<tt>destKey</tt>
     * 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {a}
     * key3 = {a,c}
     * otherKeys = {key2,key3}
     * destKey = differenceAndStore(key1, otherKeys)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void differenceAndStore(int namespace, K key, Collection<K> otherKeys, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKey</tt>的Set中交集的数据 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersect(key1, key2) = {a,c}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @return 返回key和otherKey中都存在的数据
     */
    <K, V> Set<V> intersect(int namespace, K key, K otherKey);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKey</tt>的Set中交集的数据 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersect(key1, key2) = {a,c}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key和otherKey中都存在的数据
     */
    <K, V> Set<V> intersect(int namespace, K key, K otherKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKeys</tt>的Set中交集的数据 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersect(key1, otherKeys) = {c}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKeys 其它Set集合
     * @return 返回key和otherKeys的交集
     */
    <K, V> Set<V> intersect(int namespace, K key, Collection<K> otherKeys);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKeys</tt>的Set中交集的数据 例如： <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersect(key1, otherKeys) = {c}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key和otherKeys的交集
     */
    <K, V> Set<V> intersect(int namespace, K key, Collection<K> otherKeys, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKey</tt>的Set中交集的数据，将数据放入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersectAndStore(key1, key2, destKey)
     * destKey = {a,c}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKey</tt>的Set中交集的数据，将数据放入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersectAndStore(key1, key2, destKey)
     * destKey = {a,c}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKeys</tt>的Set中交集数据，将数据放入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersectAndStore(key1, otherKeys, destKey)
     * destKey = {c}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKeys 其它Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * 取得<tt>key</tt>的Set中<tt>otherKeys</tt>的Set中交集数据，将数据放入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersectAndStore(key1, otherKeys, destKey)
     * destKey = {c}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKey</tt>的Set的并集数据 例如： <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * union(key1, key2) = {a,b,c,d}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @return 返回key和otherKey的所有数据，去重后的
     */
    <K, V> Set<V> union(int namespace, K key, K otherKey);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKey</tt>的Set的并集数据 例如： <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * union(key1, key2) = {a,b,c,d}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key和otherKey的所有数据，去重后的
     */
    <K, V> Set<V> union(int namespace, K key, K otherKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKeys</tt>的Set的并集数据 例如： <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys) = {a,b,c,d,e}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @return
     */
    <K, V> Set<V> union(int namespace, K key, Collection<K> otherKeys);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKeys</tt>的Set的并集数据 例如： <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys) = {a,b,c,d,e}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key和otherKeys的所有数据，去重后的
     */
    <K, V> Set<V> union(int namespace, K key, Collection<K> otherKeys, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKey</tt>的Set的并集数据，将数据存入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * unionAndStore(key1, key2, destKey)
     * destKey = {a,b,c,d}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKey  第二个Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKey</tt>的Set的并集数据，将数据存入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * unionAndStore(key1, key2, destKey)
     * destKey = {a,b,c,d}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKey           第二个Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKeys</tt>的Set的并集数据，将数据存入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys, destKey)
     * destKey = {a,b,c,d,e}
     * </code>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个Set集合
     * @param otherKeys 其它Set集合
     * @param destKey   目标Set集合
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * 取得<tt>key</tt>的Set和<tt>otherKeys</tt>的Set的并集数据，将数据存入<tt>destKey</tt> 例如：
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys, destKey)
     * destKey = {a,b,c,d,e}
     * </code>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个Set集合
     * @param otherKeys          其它Set集合
     * @param destKey            目标Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey, boolean useNewKeySerialize);

    /**
     * 向<tt>key</tt>的Set增加一个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @param value     值
     * @return 返回集合的长度
     */
    <K, V> Long add(int namespace, K key, V... value);

    /**
     * 向<tt>key</tt>的Set增加一个数据
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @param value              值
     * @return 返回集合的长度
     */
    <K, V> Long add(boolean useNewKeySerialize, int namespace, K key, V... value);

    /**
     * 判断<tt>o</tt>是否为<tt>key</tt>的Set中的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @param o         需要判断的值
     * @return 存在返回true, 否则返回false
     */
    <K> Boolean nisMember(int namespace, K key, Object o);

    /**
     * 判断<tt>o</tt>是否为<tt>key</tt>的Set中的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param o                  需要判断的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 存在返回true, 否则返回false
     */
    <K> Boolean nisMember(int namespace, K key, Object o, boolean useNewKeySerialize);

    /**
     * 取得所有成员
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @return 返回所有的集合元素
     */
    <K, V> Set<V> members(int namespace, K key);

    /**
     * 取得所有成员
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回所有的集合元素
     */
    <K, V> Set<V> members(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 移动<tt>key</tt>的Set中的<tt>value</tt>到<tt>destKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @param value     元素
     * @param destKey   目标集合
     * @return 返回是否移动成功
     */
    <K, V> Boolean move(int namespace, K key, V value, K destKey);

    /**
     * 移动<tt>key</tt>的Set中的<tt>value</tt>到<tt>destKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param value              元素
     * @param destKey            目标集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回是否移动成功
     */
    <K, V> Boolean move(int namespace, K key, V value, K destKey, boolean useNewKeySerialize);

    /**
     * 随机取得一个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @return 返回随机得到的集合元素
     */
    <K, V> V randomMember(int namespace, K key);

    /**
     * 随机取得一个数据
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回随机得到的集合元素
     */
    <K, V> V randomMember(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 随机返回count个元素
     *
     * @param namespace
     * @param key
     * @param <K>
     * @param <V>
     * @param count
     * @return count个元素组成的数组
     */
    <K, V> List<V> randomMember(int namespace, K key, int count);

    /**
     * 随机返回count个元素
     *
     * @param namespace
     * @param key
     * @param <K>
     * @param <V>
     * @param count
     * @param useNewKeySerialize
     * @return count个元素组成的数组
     */
    <K, V> List<V> randomMember(int namespace, K key, int count, boolean useNewKeySerialize);

    /**
     * 删除一个或者多个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @param values    需要删除的多个元素数据
     * @return 返回集合长度
     */
    <K, V> Long nremove(int namespace, K key, Object... values);

    /**
     * 删除一个或者多个数据，并且可以指定key的序列化方式
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @param values             需要删除的多个元素数据
     * @return 返回集合长度
     */
    <K, V> Long nremove(boolean useNewKeySerialize, int namespace, K key, Object... values);

    /**
     * 删除并取出第一个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @return 返回第一个元素数据
     */
    <K, V> V pop(int namespace, K key);

    /**
     * 删除并取出第一个数据
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回第一个元素数据
     */
    <K, V> V pop(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 从存储在key的集合中移除并返回多个随机元素。
     *
     * @param namespace
     * @param key
     * @param <K>
     * @param <V>
     * @param count
     * @return 返回被删除的元素集合
     */
    <K, V> Set<V> pop(int namespace, K key, long count);

    /**
     * 从存储在key的集合中移除并返回多个随机元素。
     *
     * @param namespace
     * @param key
     * @param <K>
     * @param <V>
     * @return 返回被删除的元素集合
     */
    <K, V> Set<V> pop(int namespace, K key, long count, boolean useNewKeySerialize);

    /**
     * 取得Set大小
     *
     * @param namespace 数据所在的namespace
     * @param key       Set集合
     * @return 返回集合的长度
     */
    <K, V> Long size(int namespace, K key);

    /**
     * 取得Set大小
     *
     * @param namespace          数据所在的namespace
     * @param key                Set集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回集合的长度
     */
    <K, V> Long size(int namespace, K key, boolean useNewKeySerialize);


    /**
     * 迭代查询set
     * @param namespace
     * @param key
     * @param cursor
     * @param <H>
     * @param <K>
     * @return
     */
    <H, K> ScanResult<K> sscan(int namespace, H key, String cursor);

    /***
     * 迭代查询set
     * @param namespace
     * @param key
     * @param cursor
     * @param isKeyStringSerialize
     * @param <H>
     * @param <K>
     * @return
     */
    <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, boolean isKeyStringSerialize);

    /**
     * 迭代查询set
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param <H>
     * @param <K>
     * @return
     */
    <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, String pattern, int count);

    /**
     * 迭代查询set
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
    <H, K> ScanResult<K> sscan(int namespace, H key, String cursor, String pattern, int count, boolean isKeyStringSerialize);


}
