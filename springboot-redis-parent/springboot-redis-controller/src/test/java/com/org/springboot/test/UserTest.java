package com.org.springboot.test;

import com.alibaba.fastjson.JSONObject;
import com.org.springboot.SpringbootApplication;
import com.org.springboot.entity.User;
import com.org.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class UserTest {
    @Autowired
    private UserService service;

    @Test
    public void getAll() {
        List<User> list = service.getAll();
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void add() {
        User user = new User();
        user.setName("王四");
        user.setAge(3);
        user = service.add(user);
        System.out.println(JSONObject.toJSONString(user));
    }

    @Test
    public void getById() {
        User user = service.getById(1L);
        System.out.println(JSONObject.toJSONString(user));
    }

    @Test
    public void updateUser() {
        int result = service.deleteById(1L);
        System.out.println(result);
    }
}