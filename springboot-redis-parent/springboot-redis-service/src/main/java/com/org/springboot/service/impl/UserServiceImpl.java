package com.org.springboot.service.impl;


import com.org.springboot.dao.UserDao;
import com.org.springboot.entity.User;
import com.org.springboot.serve.redis.RedisService;
import com.org.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Cacheable(value = "User", key = "'user'.concat(#id.toString())")
    public User getById(Long id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    @CachePut(value = "User", key = "'user'.concat(#user.id.toString())")
    public User updateUser(User user) {
        userDao.updateUser(user);
        return user;
    }

    @Override
    @CacheEvict(value = "User", key = "'user'.concat(#id.toString())")
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    @CachePut(value = "User", key = "'user'.concat(#user.id.toString())")
    public User add(User user) {
        userDao.insert(user);
        return user;
    }

    /**
     * keyGenerator策略生产key
     *
     * @param param1
     * @param param2
     * @return
     */
    @Cacheable(value = "redis", key = "#p0")
    @Override
    public String cacheKeyGenerator(String param1, String param2) {
        String str = param2;
        return str;
    }
}
