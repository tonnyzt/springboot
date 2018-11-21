package com.org.springboot.test;

import com.alibaba.fastjson.JSONObject;
import com.org.springboot.SpringbootApplication;
import com.org.springboot.entity.User;
import com.org.springboot.serve.redis.RedisLock;
import com.org.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisLockTest {
    @Autowired
    private UserService service;
    @Autowired
    private RedisLock redisLock;
    private final static String LOCKER_PREFIX = "lock:1";

    private List<Integer> list = new ArrayList<>();
    int stockNum = 6;

    @Test
    public void getAll() {
        for (int i = 0; i < 6; i++) {
            this.testLock();
        }
        System.out.println(list);
    }

    @Async
    private void testLock() {
        //加锁
        System.out.println(stockNum);

        Long TIMEOUT = 100L;
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(LOCKER_PREFIX, String.valueOf(time))) {
            System.out.println("===资源被锁定");
            return;
        }
        stockNum--;
        list.add(stockNum);
        if (stockNum == 0) {
            System.out.println("===stockNum:000");
            redisLock.unlock(LOCKER_PREFIX, String.valueOf(time));
            return;
        }
        try {
            Thread.sleep(300);//模拟减库存的处理时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisLock.unlock(LOCKER_PREFIX, String.valueOf(time));
    }
}