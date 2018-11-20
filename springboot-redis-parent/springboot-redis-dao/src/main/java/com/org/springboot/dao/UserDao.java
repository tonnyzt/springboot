package com.org.springboot.dao;


import com.org.springboot.entity.User;

import java.util.List;

public interface UserDao {
    User getById(Long id);

    List<User> getAll();

    int updateUser(User user);

    int deleteById(Long id);

    int insert(User user);
}