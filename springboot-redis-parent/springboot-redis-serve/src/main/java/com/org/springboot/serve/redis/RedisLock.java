package com.org.springboot.serve.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis分布式锁
 *
 * @Author: Tonny
 * @CreateDate: 18/11/21 上午 09:52
 * @Version: 1.0
 */
@Slf4j
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 分布式加锁
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            System.out.println("========新加锁");
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {//currentValue不为空且小于当前时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);//对应getset，如果key存在
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                System.out.println("========锁过期");
                return true;
            }
        }
        System.out.println("========锁未过期");
        return false;
    }

    /**
     * 分布式解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);//删除key
                System.out.println("========解锁");
            }
        } catch (Exception e) {
            log.error("[Redis分布式锁] 解锁出现异常了，{}", e);
        }
    }
}
