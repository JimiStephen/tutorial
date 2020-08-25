package com.jimi.redis.inter;



import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * SortedSet操作
 * <br/> Created on 2014-7-3 下午1:51:19
 *
 *
 * @since 3.3
 */
public interface ZsetCommands extends Commands {

    /**
     * 取得<tt>key</tt>和<tt>otherKey</tt>的交集，将结果存入<tt>destKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个zset集合
     * @param otherKey  第二个zset集合
     * @param destKey   目标zset集合
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * 取得<tt>key</tt>和<tt>otherKey</tt>的交集，将结果存入<tt>destKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个zset集合
     * @param otherKey           第二个zset集合
     * @param destKey            目标zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>和<tt>otherKeys</tt>的交集，将结果存入<tt>destKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个zset集合
     * @param otherKeys 其它zset集合
     * @param destKey   目标zset集合
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * 取得<tt>key</tt>和<tt>otherKeys</tt>的交集，将结果存入<tt>destKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个zset集合
     * @param otherKeys          其它zset集合
     * @param destKey            目标zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>和<tt>otherKey</tt>的并集，将结果存入<tt>destKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个zset集合
     * @param otherKey  第二个zset集合
     * @param destKey   目标zset集合
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * 取得<tt>key</tt>和<tt>otherKey</tt>的并集，将结果存入<tt>destKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个zset集合
     * @param otherKey           第二个zset集合
     * @param destKey            目标zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey, boolean useNewKeySerialize);

    /**
     * 取得<tt>key</tt>和<tt>otherKeys</tt>的并集，将结果存入<tt>destKey</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       第一个zset集合
     * @param otherKeys 其它zset集合
     * @param destKey   目标zset集合
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);


    /**
     * 取得<tt>key</tt>和<tt>otherKeys</tt>的并集，将结果存入<tt>destKey</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                第一个zset集合
     * @param otherKeys          其它zset集合
     * @param destKey            目标zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey, boolean useNewKeySerialize);

    /**
     * 从权重最低的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param start     开始下标
     * @param end       结束下标
     * @return 通过索引区间返回有序集合指定的数据
     */
    <K, V> Set<V> range(int namespace, K key, long start, long end);

    /**
     * 从权重最低的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param start              开始下标
     * @param end                结束下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 通过索引区间返回有序集合指定的数据
     */
    <K, V> Set<V> range(int namespace, K key, long start, long end, boolean useNewKeySerialize);

    /**
     * 从权重最低的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param start     开始下标
     * @param end       结束下标
     * @return 通过索引区间返回有序集合指定的数据及对应的分数
     */
    <K, V> Map<V, Double> rangeWithScore(int namespace, K key, long start, long end);

    /**
     * 从权重最低的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param start              开始下标
     * @param end                结束下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 通过索引区间返回有序集合指定的数据及对应的分数
     */
    <K, V> Map<V, Double> rangeWithScore(int namespace, K key, long start, long end, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @return 通过分数区间返回有序集合指定的元素数据
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 通过分数区间返回有序集合指定的元素数据
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @param offset    开始下标
     * @param count     元素个数
     * @return 返回 min<score<max区间内，从offset开始的count个元素数据
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max, int offset, int count);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param offset             开始下标
     * @param count              元素个数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回 min<score<max区间内，从offset开始的count个元素数据
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max, int offset, int count, boolean useNewKeySerialize);

    /**
     * 返回介于member minMember与maxMember之间的所有元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Set<V> zrangeByScore(int namespace, K key, V minMember, V maxMember);

    /**
     * 返回介于member minMember与maxMember之间的所有元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Set<V> zrangeByScore(int namespace, K key, V minMember, V maxMember, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @return 按分数返回指定范围内的数据，同时返回对应的分数
     */
    <K, V> Map<V, Double> rangeByScoreWithScore(int namespace, K key, double min, double max);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 按分数返回指定范围内的数据，同时返回对应的分数
     */
    <K, V> Map<V, Double> rangeByScoreWithScore(int namespace, K key, double min, double max, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @return 按分数返回指定范围内的数据，同时返回对应的分数
     */
    <K, V> Map<V, Double> rangeByScoreWithScore(int namespace, K key, double min, double max, int offset, int count);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 按分数返回指定范围内的数据，同时返回对应的分数
     */
    <K, V> Map<V, Double> rangeByScoreWithScore(int namespace, K key, double min, double max, int offset, int count, boolean useNewKeySerialize);

    /**
     * 返回指定范围内的元素及分数元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> rangeByScoreWithScores(int namespace, K key, V minMember, V maxMember);

    /**
     * 返回指定范围内的元素及分数元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> rangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, boolean isKeyStringSerialize);

    /**
     * 返回指定范围内的元素及分数元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param offset
     * @param count
     * @param <K>
     * @param offset
     * @param count
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> rangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, int offset, int count);

    /**
     * 返回指定范围内的元素及分数元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param offset
     * @param count
     * @param <K>
     * @param offset
     * @param count
     * @param isKeyStringSerialize
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> rangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean isKeyStringSerialize);

    /**
     * 从权重最高的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param start     开始下标
     * @param end       结束下标
     * @return 按照降序的方式返回指定范围内的元素集合
     */
    <K, V> Set<V> reverseRange(int namespace, K key, long start, long end);

    /**
     * 从权重最高的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param start              开始下标
     * @param end                结束下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 按照降序的方式返回指定范围内的元素集合
     */
    <K, V> Set<V> reverseRange(int namespace, K key, long start, long end, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @return 返回 min<score<max区间内的有序元素集合（按分数递减）
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回 min<score<max区间内的有序元素集合（按分数递减）
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max, boolean useNewKeySerialize);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @param offset    开始下标
     * @param count     元素个数
     * @return 返回 min<score<max区间内，从offset开始的count个元素数据（按分数递减）
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max, int offset, int count);

    /**
     * 取得权重从<tt>min</tt>到<tt>max</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param offset             开始下标
     * @param count              元素个数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回 min<score<max区间内，从offset开始的count个元素数据（按分数递减）
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max, int offset, int count, boolean useNewKeySerialize);

    /**
     * 从权重最高的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param start     开始下标
     * @param end       结束下标
     * @return 按照降序的方式返回指定范围内的元素及对应的分数
     */
    <K, V> Map<V, Double> reverseRangeWithScore(int namespace, K key, long start, long end);

    /**
     * 从权重最高的数据开始，取得位置从<tt>start</tt>到<tt>end</tt>的数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param start              开始下标
     * @param end                结束下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 按照降序的方式返回指定范围内的元素及对应的分数
     */
    <K, V> Map<V, Double> reverseRangeWithScore(int namespace, K key, long start, long end, boolean useNewKeySerialize);

    /**
     * ZREVRANGEBYSCORE 返回有序集合中指定分数区间内的成员，分数由高到低排序。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @return 指定分数范围的元素列表。
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, V minMember, V maxMember);

    /**
     * ZREVRANGEBYSCORE 返回有序集合中指定分数区间内的成员，分数由高到低排序。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @param isKeyStringSerialize
     * @return 指定分数范围的元素列表。
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, V minMember, V maxMember, boolean isKeyStringSerialize);

    /**
     * ZREVRANGEBYSCORE 返回有序集合中指定分数区间内的成员，分数由高到低排序。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @param count     返回结果数量
     * @param offset    返回结果起始位置
     * @return 指定分数范围的元素列表。
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, V minMember, V maxMember, int offset, int count);

    /**
     * ZREVRANGEBYSCORE 返回有序集合中指定分数区间内的成员，分数由高到低排序。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @param count                返回结果数量
     * @param offset               返回结果起始位置
     * @param isKeyStringSerialize
     * @return 指定分数范围的元素列表。
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean isKeyStringSerialize);


    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, double min, double max);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, double min, double max, boolean isKeyStringSerialize);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param <K>
     * @param offset
     * @param count
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, double min, double max, int offset, int count);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param <K>
     * @param offset
     * @param count
     * @param isKeyStringSerialize
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, double min, double max, int offset, int count, boolean isKeyStringSerialize);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, V minMember, V maxMember);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, boolean isKeyStringSerialize);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param offset
     * @param count
     * @param <K>
     * @param offset
     * @param count
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, int offset, int count);

    /**
     * 返回指定范围内的元素及分数元素(由高到低)
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param offset
     * @param count
     * @param <K>
     * @param offset
     * @param count
     * @param isKeyStringSerialize
     * @return 返回指定范围内的元素及分数集合
     */
    <K, V> Map<V, Double> reverseRangeByScoreWithScores(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean isKeyStringSerialize);

    /**
     * 增加一个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param value     新增元素
     * @param score     对应的分数
     * @return 是否成功
     */
    <K, V> Boolean add(int namespace, K key, V value, double score);

    /**
     * 增加一个数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param value              新增元素
     * @param score              对应的分数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 是否成功
     */
    <K, V> Boolean add(int namespace, K key, V value, double score, boolean useNewKeySerialize);

    /**
     * 增加多个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param maps      元素及对应的分数
     * @return 返回集合长度
     */
    <K, V> Long add(int namespace, K key, Map<V, Double> maps);

    /**
     * 增加多个数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param maps               元素及对应的分数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回集合长度
     */
    <K, V> Long add(int namespace, K key, Map<V, Double> maps, boolean useNewKeySerialize);

    /**
     * 将所有指定成员添加到键为key有序集合（sorted set）里面
     *
     * @param namespace
     * @param key
     * @param score
     * @param member
     * @param <K>
     * @param <V>
     * @param zAddParams
     * @return 添加到有序集合的成员数量，不包括已经存在更新分数的成员。
     */
    <K, V> Long add(int namespace, K key, double score, V member, ZAddParams zAddParams);

    /**
     * 将所有指定成员添加到键为key有序集合（sorted set）里面
     *
     * @param namespace
     * @param key
     * @param score
     * @param member
     * @param <K>
     * @param <V>
     * @return 添加到有序集合的成员数量，不包括已经存在更新分数的成员。
     */
    <K, V> Long add(int namespace, K key, double score, V member, ZAddParams zAddParams, boolean useNewKeySerialize);

    /**
     * 批量添加sorted score元素
     *
     * @param namespace
     * @param key
     * @param scoreMembers
     * @param <K>
     * @param <V>
     * @return 添加到有序集合的成员数量，不包括已经存在更新分数的成员。
     */
    <K, V> Long add(int namespace, K key, Map<V, Double> scoreMembers, ZAddParams zAddParams);

    /**
     * 批量添加sorted score元素
     *
     * @param namespace
     * @param key
     * @param scoreMembers
     * @param <K>
     * @param <V>
     * @param zAddParams
     * @param useNewKeySerialize
     * @return 添加到有序集合的成员数量，不包括已经存在更新分数的成员。
     */
    <K, V> Long add(int namespace, K key, Map<V, Double> scoreMembers, ZAddParams zAddParams, boolean useNewKeySerialize);

    /**
     * 增加一个数据的权重，并且返回更新后的权重值
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param value     新增的元素
     * @param delta     分数
     * @return 返回原来的分数
     */
    <K, V> Double incrementScore(int namespace, K key, V value, double delta);

    /**
     * 增加一个数据的权重，并且返回更新后的权重值
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param value              新增的元素
     * @param delta              分数
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回原来的分数
     */
    <K, V> Double incrementScore(int namespace, K key, V value, double delta, boolean useNewKeySerialize);

    /**
     * 得到一个数据跟中权重的排名，排名最低的Rank值为0
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param o         指定的元素
     * @return 返回指定元素的索引
     */
    <K, V> Long rank(int namespace, K key, Object o);

    /**
     * 得到一个数据跟中权重的排名，排名最低的Rank值为0
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param o                  指定的元素
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 指定元素
     */
    <K, V> Long rank(int namespace, K key, Object o, boolean useNewKeySerialize);

    /**
     * 得到一个数据跟中权重的排名，排名最高的Rank值为0
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param o         指定的元素
     * @return 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
     */
    <K, V> Long reverseRank(int namespace, K key, Object o);

    /**
     * 得到一个数据跟中权重的排名，排名最高的Rank值为0
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param o                  指定的元素
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
     */
    <K, V> Long reverseRank(int namespace, K key, Object o, boolean useNewKeySerialize);

    /**
     * 得到一个数据的权重
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param o         指定的元素
     * @return 返回有序集中，成员的分数值
     */
    <K, V> Double score(int namespace, K key, Object o);

    /**
     * 得到一个数据的权重
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param o                  指定的元素
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回有序集中，成员的分数值
     */
    <K, V> Double score(int namespace, K key, Object o, boolean useNewKeySerialize);

    /**
     * 删除一个或者多个数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param value     指定的元素
     * @return 移除有序集合中的一个或多个成员
     */
    <K, V> Long nremove(int namespace, K key, Object... value);

    /**
     * 删除一个或者多个数据，并且可以指定key的序列化方式
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @param value              指定的元素
     * @return 移除有序集合中的一个或多个成员
     */
    <K, V> Long nremove(boolean useNewKeySerialize, int namespace, K key, Object... value);

    /**
     * 删除坐标从<tt>start</tt>到<tt>end</tt>的所有数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param start     开始索引
     * @param end       结束索引
     */
    <K, V> void removeRange(int namespace, K key, long start, long end);

    /**
     * 删除坐标从<tt>start</tt>到<tt>end</tt>的所有数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param start              开始索引
     * @param end                结束索引
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void removeRange(int namespace, K key, long start, long end, boolean useNewKeySerialize);

    /**
     * 删除权重值从<tt>min</tt>到<tt>max</tt>的所有数据
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     */
    <K, V> void removeRangeByScore(int namespace, K key, double min, double max);

    /**
     * 删除权重值从<tt>min</tt>到<tt>max</tt>的所有数据
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    <K, V> void removeRangeByScore(int namespace, K key, double min, double max, boolean useNewKeySerialize);

    /**
     * 返回删除介于minMember 与 maxMember 的所有元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Long removeRangeByScore(int namespace, K key, V minMember, V maxMember);

    /**
     * 返回删除介于minMember 与 maxMember 的所有元素
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @param useNewKeySerialize
     * @return
     */
    <K, V> Long removeRangeByScore(int namespace, K key, V minMember, V maxMember, boolean useNewKeySerialize);

    /**
     * 取得权重值从<tt>min</tt>到<tt>max</tt>的数据数量
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @param min       分数下届
     * @param max       分数上届
     * @return 返回指定范围内的数据量
     */
    <K, V> Long count(int namespace, K key, double min, double max);

    /**
     * 取得权重值从<tt>min</tt>到<tt>max</tt>的数据数量
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param min                分数下届
     * @param max                分数上届
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回指定范围内的数据量
     */
    <K, V> Long count(int namespace, K key, double min, double max, boolean useNewKeySerialize);

    /**
     * 返回有序集key中，元素值在minMember和maxMember之间的成员
     *
     * @param namespace
     * @param key
     * @param minMember 分数较小的元素
     * @param maxMember 分数较大的元素
     * @param <K>
     * @return 指定分数范围的元素个数。
     */
    <K, V> Long count(int namespace, K key, V minMember, V maxMember);

    /**
     * 返回有序集key中，元素值在minMember和maxMember之间的成员
     *
     * @param namespace
     * @param key
     * @param minMember          分数较小的元素
     * @param maxMember          分数较大的元素
     * @param <K>
     * @param useNewKeySerialize
     * @return 指定分数范围的元素个数。
     */
    <K, V> Long count(int namespace, K key, V minMember, V maxMember, boolean useNewKeySerialize);

    /**
     * 取得SortedSet大小
     *
     * @param namespace 数据所在的namespace
     * @param key       zset集合
     * @return 返回有序集合的长度
     */
    <K, V> Long size(int namespace, K key);

    /**
     * 取得SortedSet大小
     *
     * @param namespace          数据所在的namespace
     * @param key                zset集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回有序集合的长度
     */
    <K, V> Long size(int namespace, K key, boolean useNewKeySerialize);

    /**
     * 返回有序集key中，分数排名较小的成员和分数排名较大成员之间的所有成员
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @return 指定分数范围的元素个数。
     */
    <K, V> Long zlexcount(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding);

    /**
     * 返回有序集key中，分数排名较小的成员和分数排名较大成员之间的所有成员
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param isKeyStringSerialize
     * @return 指定分数范围的元素个数。
     */
    <K, V> Long zlexcount(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);

    /**
     * ZRANGEBYLEX 返回指定成员区间内的成员，按成员字典正序排序, 分数必须相同。 在某些业务场景中,需要对一个字符串数组按名称的字典顺序进行排序时,可以使用Redis中SortSet这种数据结构来处理。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @return 指定成员范围的元素列表。
     */
    <K, V> Set<V> zrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding);

    /**
     * ZRANGEBYLEX 返回指定成员区间内的成员，按成员字典正序排序, 分数必须相同。 在某些业务场景中,需要对一个字符串数组按名称的字典顺序进行排序时,可以使用Redis中SortSet这种数据结构来处理。
     *
     * @param namespace
     * @param key
     * @param minMember
     * @param maxMember
     * @param <K>
     * @param <V>
     * @param isKeyStringSerialize
     * @return 指定成员范围的元素列表。
     */
    <K, V> Set<V> zrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);

    /**
     * ZRANGEBYLEX 返回指定成员区间内的成员，按成员字典正序排序, 分数必须相同。 在某些业务场景中,需要对一个字符串数组按名称的字典顺序进行排序时,可以使用Redis中SortSet这种数据结构来处理。
     *
     * @param namespace
     * @param key
     * @param minMember 字典中排序位置较小的成员
     * @param maxMember 字典中排序位置较大的成员
     * @param offset    返回结果的起始为止
     * @param count     返回结果的输数量
     * @param <K>
     * @param <V>
     * @return 指定成员范围的元素列表。
     */
    <K, V> Set<V> zrangeByLex(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean minIncluding, boolean maxIncluding);

    /**
     * ZRANGEBYLEX 返回指定成员区间内的成员，按成员字典正序排序, 分数必须相同。 在某些业务场景中,需要对一个字符串数组按名称的字典顺序进行排序时,可以使用Redis中SortSet这种数据结构来处理。
     *
     * @param namespace
     * @param key
     * @param minMember            字典中排序位置较小的成员
     * @param maxMember            字典中排序位置较大的成员
     * @param offset               返回结果的起始为止
     * @param count                返回结果的输数量
     * @param <K>
     * @param <V>
     * @param isKeyStringSerialize
     * @return 指定成员范围的元素列表。
     */
    <K, V> Set<V> zrangeByLex(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);

    /**
     * ZREMRANGEBYLEX 删除名称按字典由低到高排序成员之间所有成员。
     *
     * @param namespace
     * @param key
     * @param minMember 字典中排序位置较小的成员
     * @param maxMember 字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @return 删除元素的个数。
     */
    <K, V> Long zremrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding);

    /**
     * ZREMRANGEBYLEX 删除名称按字典由低到高排序成员之间所有成员。
     *
     * @param namespace
     * @param key
     * @param minMember            字典中排序位置较小的成员
     * @param maxMember            字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @param isKeyStringSerialize
     * @return 删除元素的个数。
     */
    <K, V> Long zremrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);

    /**
     * ZREVRANGEBYLEX 返回指定成员区间内的成员，按成员字典倒序排序, 分数必须相同。
     *
     * @param namespace
     * @param key
     * @param minMember 字典中排序位置较小的成员
     * @param maxMember 字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Set<V> zrevrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding);

    /**
     * ZREVRANGEBYLEX 返回指定成员区间内的成员，按成员字典倒序排序, 分数必须相同。
     *
     * @param namespace
     * @param key
     * @param minMember            字典中排序位置较小的成员
     * @param maxMember            字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @param isKeyStringSerialize
     * @return
     */
    <K, V> Set<V> zrevrangeByLex(int namespace, K key, V minMember, V maxMember, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);

    /**
     * ZREVRANGEBYLEX 返回指定成员区间内的成员，按成员字典倒序排序, 分数必须相同。
     *
     * @param namespace
     * @param key
     * @param minMember 字典中排序位置较小的成员
     * @param maxMember 字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @param count     返回结果数量
     * @param offset    返回结果起始位置
     * @return
     */
    <K, V> Set<V> zrevrangeByLex(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean minIncluding, boolean maxIncluding);

    /**
     * ZREVRANGEBYLEX 返回指定成员区间内的成员，按成员字典倒序排序, 分数必须相同。
     *
     * @param namespace
     * @param key
     * @param minMember            字典中排序位置较小的成员
     * @param maxMember            字典中排序位置较大的成员
     * @param <K>
     * @param <V>
     * @param count                返回结果数量
     * @param offset               返回结果起始位置
     * @param isKeyStringSerialize
     * @return
     */
    <K, V> Set<V> zrevrangeByLex(int namespace, K key, V minMember, V maxMember, int offset, int count, boolean minIncluding, boolean maxIncluding, boolean isKeyStringSerialize);


    /**
     * 迭代查询zset
     * @param namespace
     * @param key
     * @param cursor
     * @param <H>
     * @param <V>
     * @return
     */
    <H, V> ScanResult<Map<V, Double>> zscan(int namespace, H key, String cursor);


    /**
     * 迭代查询zset
     * @param namespace
     * @param key
     * @param cursor
     * @param isKeyStringSerialize
     * @param <H>
     * @param <V>
     * @return
     */
    <H, V> ScanResult<Map<V, Double>> zscan(int namespace, H key, String cursor, boolean isKeyStringSerialize);

    /**
     * 迭代查询zset
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param <H>
     * @param <V>
     * @return
     */
    <H, V> ScanResult<Map<V, Double>> zscan(int namespace, H key, String cursor, String pattern, int count);


    /**
     * 迭代查询zset
     * @param namespace
     * @param key
     * @param cursor
     * @param pattern
     * @param count
     * @param isKeyStringSerialize
     * @param <H>
     * @param <V>
     * @return
     */
    <H, V> ScanResult<Map<V, Double>> zscan(int namespace, H key, String cursor, String pattern, int count, boolean isKeyStringSerialize);

}
