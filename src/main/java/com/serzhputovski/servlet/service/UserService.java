package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;

public interface UserService {
    void registerUser(User user) throws DatabaseException;
    boolean isUsernameAvailable(String username) throws DatabaseException;
}
