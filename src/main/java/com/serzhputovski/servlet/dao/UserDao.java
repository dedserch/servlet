package com.serzhputovski.servlet.dao;

import com.serzhputovski.servlet.entity.User;

public interface UserDao {
    User findByUsername(String username);
    void save(String username, String password);
}
