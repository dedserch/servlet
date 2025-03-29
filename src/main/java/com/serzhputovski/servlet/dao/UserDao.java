package com.serzhputovski.servlet.dao;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;

public interface UserDao {
    User findByUsername(String username) throws DatabaseException;
    void save(String username, String password);
}
