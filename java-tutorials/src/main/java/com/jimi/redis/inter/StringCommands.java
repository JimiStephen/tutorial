package com.jimi.redis.inter;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * String 操作
 * <p>
 * <br/> Created on 2014-7-3 下午1:49:02
 *
 * @since 3.3
 */
public interface StringCommands extends Commands {

    /**
     * 在为<tt>key</tt>的String追加<tt>value</tt>组成新的字符串，并返回组装后的字符串长度
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要append的值
     * @return 返回append后的字符串长度
     */
    Long append(int namespace, String key, String value);

    /**
     * 在为<tt>key</tt>的String追加<tt>value</tt>组成新的字符串，并返回组装后的字符串长度
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要append的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回append后的字符串长度
     */
    Long append(int namespace, String key, String value, boolean useNewKeySerialize);

    /**
     * 得到String从位置<tt>start</tt>到<tt>end</tt>的子字符串
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param start     开始下标
     * @param end       结束下标
     * @return 返回指定的子字符串
     */
    String get(int namespace, String key, long start, long end);

    /**
     * 得到String从位置<tt>start</tt>到<tt>end</tt>的子字符串
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param start              开始下标
     * @param end                结束下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回指定的子字符串
     */
    String get(int namespace, String key, long start, long end, boolean useNewKeySerialize);

    /**
     * 从<tt>offset</tt>开始替换为<tt>value</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     替换的字符串值
     * @param offset    开始替换的下标
     */
    void set(int namespace, String key, String value, long offset);

    /**
     * 从<tt>offset</tt>开始替换为<tt>value</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              替换的字符串值
     * @param offset             开始替换的下标
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    void set(int namespace, String key, String value, long offset, boolean useNewKeySerialize);

    /**
     * 取得字符串长度
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return 返回字符串长度
     */
    Long size(int namespace, String key);

    /**
     * 取得字符串长度
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回字符串长度
     */
    Long size(int namespace, String key, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     覆盖的值
     */
    void set(int namespace, String key, String value);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              覆盖的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     */
    void set(int namespace, String key, String value, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     覆盖的值
     * @param timeout   数据的有效时间
     * @param unit      时间单位
     */
    void set(int namespace, String key, String value, long timeout, TimeUnit unit);

    /**
     * 设置数据，如果数据已经存在，则覆盖，如果不存在，则新增
     *
     * @param namespace                          数据所在的namespace
     * @param key                                键
     * @param value                              覆盖的值
     * @param timeout                            数据的有效时间
     * @param unit                               时间单位
     * @param useNewKeySerialize(是否使用新的key序列化方式)
     */
    void set(int namespace, String key, String value, long timeout, TimeUnit unit, boolean useNewKeySerialize);

    /**
     * 设置数据，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     不存在时没需要新增的值
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    Boolean setIfAbsent(int namespace, String key, String value);

    /**
     * 设置数据，如果数据已经存在，则保留原值返回<tt>false</tt>，如果不存在，则新增，返回<tt>true</tt>
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              不存在时没需要新增的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 如果数据不存在返回<tt>true</tt>，否则返回<tt>false</tt>
     */
    Boolean setIfAbsent(int namespace, String key, String value, boolean useNewKeySerialize);

    /**
     * 批量设置数据
     *
     * @param namespace 数据所在的namespace
     * @param m         多个key和vaue的集合
     */
    void multiSet(int namespace, Map<String, String> m);

    /**
     * 批量设置数据，只有当数据不存在时才设置
     *
     * @param namespace 数据所在的namespace
     * @param m         多个key和vaue的集合
     */
    void multiSetIfAbsent(int namespace, Map<String, String> m);

    /**
     * 获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @return 返回key对应的数据
     */
    String get(int namespace, Object key);

    /**
     * 获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return
     */
    String get(int namespace, Object key, boolean useNewKeySerialize);

    /**
     * 设置数据同时返回老数据，此方法是原子操作 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace 数据所在的namespace
     * @param key       键
     * @param value     需要重新设置的值
     * @return 返回key原来的值（不是value）
     */
    String getAndSet(int namespace, String key, String value);

    /**
     * 设置数据同时返回老数据，此方法是原子操作 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param key                键
     * @param value              需要重新设置的值
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回key原来的值（不是value）
     */
    String getAndSet(int namespace, String key, String value, boolean useNewKeySerialize);

    /**
     * 批量获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace 数据所在的namespace
     * @param keys      多个key的集合
     * @return 返回keys的value集合
     */
    List<String> multiGet(int namespace, Collection<String> keys);

    /**
     * 批量获取数据 注意不能用此方法去获取increment方法产生的key
     *
     * @param namespace          数据所在的namespace
     * @param keys               多个key的集合
     * @param useNewKeySerialize 是否使用新的key序列化方式
     * @return 返回keys的value集合
     */
    List<String> multiGet(int namespace, Collection<String> keys, boolean useNewKeySerialize);

    /**
     * 原子的设置value过期时间
     *
     * @param namespace
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    Boolean setexnx(int namespace, String key, String value, long timeout, TimeUnit unit);

    /**
     * 原子的设置value过期时间
     *
     * @param namespace
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @param useNewKeySerialize
     * @return
     */
    Boolean setexnx(int namespace, String key, String value, long timeout, TimeUnit unit, boolean useNewKeySerialize);

    /**
     * 统计字符串被设置为1的bit数
     *
     * @param namespace
     * @param key
     * @return 被设置为 1 的位的数量。
     */
    Long bitcount(int namespace, String key);

    /**
     * 统计字符串被设置为1的bit数
     *
     * @param namespace
     * @param key
     * @param isKeyStringSerialize
     * @return 被设置为 1 的位的数量。
     */
    Long bitcount(int namespace, String key, boolean isKeyStringSerialize);

    /**
     * 根据字符串start-end被设置为1的bit数
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return 被设置为 1 的位的数量。
     */
    Long bitcount(int namespace, String key, long start, long end);

    /**
     * 根据字符串start-end被设置为1的bit数
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @param isKeyStringSerialize
     * @return 被设置为 1 的位的数量。
     */
    Long bitcount(int namespace, String key, long start, long end, boolean isKeyStringSerialize);

    /**
     * BitOP命令，返回destKey的字符串的长度
     *
     * @param bitOP
     * @param namespace
     * @param destKey
     * @param otherKeys
     * @return
     */
    Long bitop(BitOP bitOP, int namespace, String destKey, String... otherKeys);

    /**
     * BitOP命令，返回destKey的字符串的长度
     *
     * @param bitOP
     * @param isKeyStringSerialize
     * @param namespace
     * @param destKey
     * @param otherKeys
     * @return
     */
    Long bitop(boolean isKeyStringSerialize, BitOP bitOP, int namespace, String destKey, String... otherKeys);

    /**
     * 在指定offset的bit值
     *
     * @param namespace
     * @param key
     * @param offset
     * @param value
     * @return 原来的offset处的bit值
     */
    Boolean setbit(int namespace, String key, long offset, boolean value);

    /**
     * 获取指定key，指定offset的bit值
     *
     * @param namespace
     * @param key
     * @param offset
     * @param value
     * @param isKeyStringSerialize
     * @return 原来的offset处的bit值
     */
    Boolean setbit(int namespace, String key, long offset, boolean value, boolean isKeyStringSerialize);

    /**
     * 获取指定key，指定offset的bit值
     *
     * @param namespace
     * @param key
     * @param offset
     * @return 在offset处的bit值
     */
    Boolean getbit(int namespace, String key, long offset);

    /**
     * 获取指定key，指定offset的bit值
     *
     * @param namespace
     * @param key
     * @param offset
     * @param isKeyStringSerialize
     * @return 在offset处的bit值
     */
    Boolean getbit(int namespace, String key, long offset, boolean isKeyStringSerialize);

}
