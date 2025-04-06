package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;

public interface AuthService {
    void register (User user) throws DatabaseException;
    void login (String username, String password) throws DatabaseException;
}
