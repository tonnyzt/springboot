package com.org.springboot.controller;

import com.org.springboot.entity.User;
import com.org.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统配置控制器
 *
 * @Author: Tonny
 * @CreateDate: 18/11/19 上午 11:32
 * @Version: 1.0
 */
@RequestMapping(value = "user")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    @ResponseBody
    List<User> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    @ResponseBody
    User add(User user) {
        return service.add(user);
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    @ResponseBody
    User getById(Long id) {
        return service.getById(id);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
    @ResponseBody
    User updateUser(User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    @ResponseBody
    int updateUser(Long id) {
        return service.deleteById(id);
    }

    @RequestMapping(value = "cacheKeyGenerator", method = RequestMethod.GET)
    @ResponseBody
    String cacheKeyGenerator(String a,String b) {
        return service.cacheKeyGenerator(a,b);
    }

}
