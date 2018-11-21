package com.org.springboot.test;

import com.org.springboot.SpringbootApplication;
import com.org.springboot.serve.redis.RedisLock;
import com.org.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class StringRedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() throws InterruptedException {
        stringRedisTemplate.opsForValue().set("name", "张三");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));

        //=========有效期
        stringRedisTemplate.opsForValue().set("name", "张三", 3, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        Thread.sleep(3000);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    public void test2() {
        stringRedisTemplate.opsForValue().set("name", "张三");
        stringRedisTemplate.opsForValue().set("name", "李四", 5);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    public void test3() {
        stringRedisTemplate.opsForValue().set("name", "张三");
        boolean b = stringRedisTemplate.opsForValue().setIfAbsent("name2", "王麻子");
        System.out.println(b);
    }

    @Test
    public void multiSet() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "10");
        map.put("name", "张三");
        map.put("age", "23");
        stringRedisTemplate.opsForValue().multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("id");
        keys.add("name");
        keys.add("age");
        List<?> list = stringRedisTemplate.opsForValue().multiGet(keys);
        System.out.println(list);
    }

    @Test
    public void multiSetIfAbsent() {
        Map<String, String> map = new HashMap<>();
        map.put("id2", "10");
        map.put("name", "张三");
        map.put("age2", "23");
        stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
        List<String> keys = new ArrayList<>();
        keys.add("id");
        keys.add("name");
        keys.add("age");
        List<?> list = stringRedisTemplate.opsForValue().multiGet(keys);
        System.out.println(list);
    }

    @Test
    public void getAndSet() {
        String str = stringRedisTemplate.opsForValue().getAndSet("name", "张三2");
        System.out.println(str);
    }

    @Test
    public void increment() {
        Long str = stringRedisTemplate.opsForValue().increment("increment", 10);
        System.out.println(str);

        Double d = stringRedisTemplate.opsForValue().increment("increment", 10D);
        System.out.println(d);
    }

    @Test
    public void append() {
        int i = stringRedisTemplate.opsForValue().append("name", "hello");
        System.out.println(i);
        i = stringRedisTemplate.opsForValue().append("name", " word");
        System.out.println(i);

        String str = stringRedisTemplate.opsForValue().get("name", 1, -1);
        System.out.println(str);

        str = stringRedisTemplate.opsForValue().get("name", -3, -1);
        System.out.println(str);

        str = stringRedisTemplate.opsForValue().get("name", -4, -2);
        System.out.println(str);
    }

    @Test
    public void size() {

        stringRedisTemplate.opsForValue().set("name", "张三");

        Long str = stringRedisTemplate.opsForValue().size("name");
        System.out.println(str);

        stringRedisTemplate.opsForValue().set("name", "tom");

        str = stringRedisTemplate.opsForValue().size("name");
        System.out.println(str);
    }

    @Test
    public void setBit() {
        stringRedisTemplate.opsForValue().set("name", "A");
        stringRedisTemplate.opsForValue().setBit("name", 6, true);
        stringRedisTemplate.opsForValue().setBit("name", 7, false);
        String str = stringRedisTemplate.opsForValue().get("name");
        System.out.println(str);
        RedisOperations redisOperations = stringRedisTemplate.opsForValue().getOperations();
        System.out.println(redisOperations);
    }
}