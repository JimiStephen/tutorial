package com.jimi.redis.inter;




import java.util.List;
import java.util.Map;

/**
 *
 * @date: 2019-08-09 14:54
 *
 * @description: 地理位置相关，geo的数据类型为zset
 * 不支持 GEORADIUSBYMEMBER_RO、GEORADIUS_RO
 */
public interface GeoCommands extends ZsetCommands {

    /**
     * geoadd (添加位置)
     * 经纬度要求:
     * 有效的经度从-180度到180度。
     * 有效的纬z储地理位置的集合名称
     * @param namespace
     * @param key 存储地理位置的集合名称
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    位置名称
     * @return 新添加到key里面的空间元素数量,不包括那些已经存在但是被更新的元素。
     */
    <K, MK>Long geoadd(int namespace, K key, double longitude, double latitude, MK member);

    /**
     * geoadd (添加位置)
     * 经纬度要求:
     * 有效的经度从-180度到180度。
     * 有效的纬z储地理位置的集合名称
     * @param namespace
     * @param key                   存储地理位置的集合名称
     * @param longitude             经度
     * @param latitude              纬度
     * @param member                位置名称
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return 新添加到key里面的空间元素数量,不包括那些已经存在但是被更新的元素。
     */
    <K, MK>Long geoadd(int namespace, K key, double longitude, double latitude, MK member, boolean isKeyStringSerialize);

    /**
     * 添加多个位置
     * @param namespace                 存储地理位置的集合名称
     * @param key                 存储地理位置的集合名称
     * @param memberCoordinateMap 位置名称-经纬度 map
     * @return
     */
    <K, MK>Long geoadd(int namespace, K key, Map<MK, GeoCoordinate> memberCoordinateMap);

    /**
     * 添加多个位置
     * @param namespace             存储地理位置的集合名称
     * @param key                   存储地理位置的集合名称
     * @param memberCoordinateMap   位置名称-经纬度 map
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return
     */
    <K, MK>Long geoadd(int namespace, K key, Map<MK, GeoCoordinate> memberCoordinateMap, boolean isKeyStringSerialize);

    /**
     * 计算给定两个位置之间的距离，使用米作为单位
     * 1.如果两个位置之间的其中一个不存在,那么命令返回空值。
     * 2.在计算距离时会假设地球为完美的球形,在极限情况下,这一假设最大会造成0.5%的误差。
     * @param namespace
     * @param key     存储地理位置的集合名称
     * @param member1 位置1名称
     * @param member2 位置2名称
     * @return        member1与member2的距离
     */
    <K, MK>Double geodist(int namespace, K key, MK member1, MK member2);

    /**
     * 计算给定两个位置之间的距离，使用米作为单位
     * 1.如果两个位置之间的其中一个不存在,那么命令返回空值。
     * 2.在计算距离时会假设地球为完美的球形,在极限情况下,这一假设最大会造成0.5%的误差。
     * @param namespace
     * @param key                   存储地理位置的集合名称
     * @param member1               位置1名称
     * @param member2               位置2名称
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      member1与member2的距离
     */
    <K, MK>Double geodist(int namespace, K key, MK member1, MK member2, boolean isKeyStringSerialize);

    /**
     * 计算给定两个位置之间的距离
     * 1.如果两个位置之间的其中一个不存在,那么命令返回空值。
     * 2.指定单位的参数unit必须是以下单位的其中一个：
     * m表示单位为米
     * km表示单位为千米
     * mi表示单位为英里
     * ft表示单位为英尺
     * 3.如果用户没有显式地指定单位参数,那么geodist默认使用米作为单位。
     * 4.在计算距离时会假设地球为完美的球形,在极限情况下,这一假设最大会造成0.5%的误差。
     * @param namespace
     * @param key     存储地理位置的集合名称
     * @param member1 位置1名称
     * @param member2 位置2名称
     * @param unit    距离单位
     * @return        member1与member2的距离
     */
    <K, MK>Double geodist(int namespace, K key, MK member1, MK member2, GeoUnit unit);

    /**
     * 计算给定两个位置之间的距离
     * 1.如果两个位置之间的其中一个不存在,那么命令返回空值。
     * 2.指定单位的参数unit必须是以下单位的其中一个：
     * m表示单位为米
     * km表示单位为千米
     * mi表示单位为英里
     * ft表示单位为英尺
     * 3.如果用户没有显式地指定单位参数,那么geodist默认使用米作为单位。
     * 4.在计算距离时会假设地球为完美的球形,在极限情况下,这一假设最大会造成0.5%的误差。
     * @param namespace
     * @param key                   存储地理位置的集合名称
     * @param member1               位置1名称
     * @param member2               位置2名称
     * @param unit                  距离单位
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      member1与member2的距离
     */
    <K, MK>Double geodist(int namespace, K key, MK member1, MK member2, GeoUnit unit, boolean isKeyStringSerialize);

    /**
     * 二维经纬度转换为一维字符串，字符串越长表示位置更精确,两个字符串越相似表示距离越近
     * @param namespace
     * @param key      存储地理位置的集合名称
     * @param members  地址位置元素名称
     * @return         一个数组,数组的每个项都是一个geohash。
     *                 命令返回的geohash的位置与用户给定的位置元素的位置一一对应。
     */
    <K, MK>List<String> geohash(int namespace, K key, MK... members);

    /**
     * 二维经纬度转换为一维字符串，字符串越长表示位置更精确,两个字符串越相似表示距离越近
     * @param namespace
     * @param key                   存储地理位置的集合名称
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @param members               地址位置元素名称
     * @return                      一个数组,数组的每个项都是一个geohash。
     *                              命令返回的geohash的位置与用户给定的位置元素的位置一一对应。
     */
    <K, MK>List<String> geohash(boolean isKeyStringSerialize, int namespace, K key, MK... members);

    /**
     * key返回所有members的位置(经度和纬度)
     * @param namespace
     * @param key      地理位置集合名称
     * @param members  位置名称
     * @return         members的位置
     */
    <K, MK>List<GeoCoordinate> geopos(int namespace, K key, MK... members);

    /**
     * key返回所有members的位置(经度和纬度)
     * @param namespace
     * @param key                   地理位置集合名称
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @param members               位置名称
     * @return                      members的位置
     */
    <K, MK>List<GeoCoordinate> geopos(boolean isKeyStringSerialize, int namespace, K key, MK... members);

    /**
     * 以给定的经纬度为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     * 1.GeoUnit距离单位：
     *    m  米。
     *    km 千米。
     *    mi 英里。
     *    ft 英尺。
     * @param namespace
     * @param key
     * @param longitude 给定中心的经度
     * @param latitude  给定中心的纬度
     * @param radius    与中心的最大距离（半径）
     * @param unit      距离单位
     * @return          与给定经纬度距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadius(int namespace, K key, double longitude, double latitude, double radius, GeoUnit unit);
    /**
     * 以给定的经纬度为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     * 1.GeoUnit距离单位：
     *    m  米。
     *    km 千米。
     *    mi 英里。
     *    ft 英尺。
     * @param namespace
     * @param key
     * @param longitude             给定中心的经度
     * @param latitude              给定中心的纬度
     * @param radius                与中心的最大距离（半径）
     * @param unit                  距离单位
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      与给定经纬度距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadius(int namespace, K key, double longitude, double latitude, double radius,
                                           GeoUnit unit, boolean isKeyStringSerialize);
    /**
     * 以给定的经纬度为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     * 1.GeoUnit距离单位：
     *    m  米。
     *    km 千米。
     *    mi 英里。
     *    ft 英尺。
     * 2.给定GeoRadiusParam,返回附加信息：
     *    withdist: 在返回位置元素的同时,将位置元素与中心之间的距离也一并返回.距离的单位和用户给定的范围单位保持一致。
     *    withcoord: 将位置元素的经度和纬度也一并返回。
     * 3. 制定排序方式（默认未排序）：
     *    asc:根据中心的位置,按照从近到远的方式返回位置元素
     *    desc:根据中心的位置,按照从远到近的方式返回位置元素。
     * 4. 返回位置元素个数（默认返回所有匹配的）：
     *    通过使用 COUNT <count>选项，可以将结果限制为前 N 个匹配项
     * @param namespace
     * @param key
     * @param longitude   给定中心的经度
     * @param latitude    给定中心的纬度
     * @param radius      与中心的最大距离（半径）
     * @param unit        距离单位
     * @param param       附加条件（withdist、withcoord、asc/desc、count）
     * @return            与给定经纬度距离不超过radius的所有位置元素、
     *                    在没有给定附加条件下,返回线性列表
     */
    <K, MK>List<GeoRadiusResult> georadius(int namespace, K key, double longitude, double latitude, double radius,
                                           GeoUnit unit, GeoRadiusParam param);
    /**
     * 以给定的经纬度为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     * 1.GeoUnit距离单位：
     *    m  米。
     *    km 千米。
     *    mi 英里。
     *    ft 英尺。
     * 2.给定GeoRadiusParam,返回附加信息：
     *    withdist: 在返回位置元素的同时,将位置元素与中心之间的距离也一并返回.距离的单位和用户给定的范围单位保持一致。
     *    withcoord: 将位置元素的经度和纬度也一并返回。
     * 3. 制定排序方式（默认未排序）：
     *    asc:根据中心的位置,按照从近到远的方式返回位置元素
     *    desc:根据中心的位置,按照从远到近的方式返回位置元素。
     * 4. 返回位置元素个数（默认返回所有匹配的）：
     *    通过使用 COUNT <count>选项，可以将结果限制为前 N 个匹配项
     * @param namespace
     * @param key
     * @param longitude             给定中心的经度
     * @param latitude              给定中心的纬度
     * @param radius                与中心的最大距离（半径）
     * @param unit                  距离单位
     * @param param                 附加条件（withdist、withcoord、asc/desc、count）
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      与给定经纬度距离不超过radius的所有位置元素、
     *                              在没有给定附加条件下,返回线性列表
     */
    <K, MK>List<GeoRadiusResult> georadius(int namespace, K key, double longitude, double latitude, double radius,
                                           GeoUnit unit, GeoRadiusParam param, boolean isKeyStringSerialize);
    /***
     * 找出位于指定范围内的元素,但是georadiusbymember的中心点是由给定的位置元素决定的,
     * 而不是像georadius那样,使用输入的经度和纬度来决定中心点。
     * @param namespace
     * @param key
     * @param member    给定的位置元素
     * @param radius    与中心的最大距离（半径）
     * @param unit      距离单位
     * @return          与给定位置元素距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadiusByMember(int namespace, K key, MK member, double radius, GeoUnit unit);

    /***
     * 找出位于指定范围内的元素,但是georadiusbymember的中心点是由给定的位置元素决定的,
     * 而不是像georadius那样,使用输入的经度和纬度来决定中心点。
     * @param namespace
     * @param key
     * @param member                给定的位置元素
     * @param radius                与中心的最大距离（半径）
     * @param unit                  距离单位
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      与给定位置元素距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadiusByMember(int namespace, K key, MK member, double radius, GeoUnit unit, boolean isKeyStringSerialize);

    /**
     * 以给定的位置元素为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     *
     * @param namespace
     * @param key
     * @param member    给定的位置元素
     * @param radius    与中心的最大距离（半径）
     * @param unit      距离单位
     * @param param     附加条件（withdist、withcoord、asc/desc、count）
     * @return          与给定位置元素距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadiusByMember(int namespace, K key, String member, double radius, GeoUnit unit,
                                                   GeoRadiusParam param);
    /**
     * 以给定的位置元素为中心,返回key包含的位置元素当中,与中心的距离不超过给定最大距离(radius)的所有位置元素。
     *
     * @param namespace
     * @param key
     * @param member                给定的位置元素
     * @param radius                与中心的最大距离（半径）
     * @param unit                  距离单位
     * @param param                 附加条件（withdist、withcoord、asc/desc、count）
     * @param isKeyStringSerialize  key序列化方式，true: string序列化，false: hessian序列化
     * @return                      与给定位置元素距离不超过radius的所有位置元素
     */
    <K, MK>List<GeoRadiusResult> georadiusByMember(int namespace, K key, String member, double radius, GeoUnit unit,
                                                   GeoRadiusParam param, boolean isKeyStringSerialize);
}

