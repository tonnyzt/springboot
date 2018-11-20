package com.org.springboot.service;

import com.org.springboot.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    User updateUser(User user);

    int deleteById(Long id);

    User add(User user);

    String cacheKeyGenerator(String param1, String param2);
}
