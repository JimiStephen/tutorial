package com.jimi.redis;


import org.springframework.util.ClassUtils;


/**
 *
 * @date: 2019/2/21 14:38
 * @description: 通过反射获取RedisClient对象
 */
public class RedisManager {

    private static RedisClient redisClient = null;
private static String REDISCLASS = "com.jimi.redis.RedisClientImpl";
    public static RedisClient getRedisClient()   {
        if (redisClient == null) {
            synchronized (RedisManager.class) {
                if (redisClient == null) {
                    try {
                        redisClient = findPresentRedisClient();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return redisClient;
    }

    private static RedisClient findPresentRedisClient() throws Exception {
        if (ClassUtils.isPresent(REDISCLASS, null)) {
            return createRedisClient(REDISCLASS);
        }
        throw new Exception("init redis client error , could not find class [" + REDISCLASS + "] ");
    }

    private static RedisClient createRedisClient(String redisClazz) throws Exception {
        try {
            Class clazz = Class.forName(redisClazz);
            return (RedisClient) clazz.newInstance();
        } catch (Exception ex) {
            throw new Exception("init redis client error , reflect [" + redisClazz + "] to instance fail");
        }
    }

}
