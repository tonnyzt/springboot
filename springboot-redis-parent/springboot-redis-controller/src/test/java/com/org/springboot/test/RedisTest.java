package com.org.springboot.test;

import com.org.springboot.SpringbootApplication;
import com.org.springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    private List<Object> getData() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(3L);
        list.add("战三");
        list.add(3.2);
        User user = new User();
        user.setName("tom");
        user.setId(2L);
        user.setAge(38);
        list.add(user);
        return list;
    }

    private List<String> getData2() {
        List<String> list = new ArrayList<>();
        list.add("C#");
        list.add("C++");
        list.add("python");
        list.add("java");
        list.add("html");
        list.add("vf");
        list.add("vb");
        list.add("javaScript");
        list.add("typeScript");
        return list;
    }
    @Test
    public void leftPush() {
        redisTemplate.opsForList().rightPush("list", this.getData2());
    }
    @Test
    public void range() {
        redisTemplate.opsForList().range("list", 0, -1);//裁剪第一个元素
    }
    @Test
    public void trim() {
        redisTemplate.opsForList().trim("list", 2, 1);
        System.out.println(redisTemplate.opsForList().size("list"));
    }

    @Test
    public void rightPush() {
        redisTemplate.opsForList().leftPush("list", 2, 1);
        redisTemplate.opsForList().leftPush("list", 2, 1);
        redisTemplate.opsForList().leftPush("list", 2, 1);

    }
}