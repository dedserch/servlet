package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;

public interface UserService {
    int save(String username, String email, String password) throws DatabaseException;
    boolean isUsernameAvailable(String username) throws DatabaseException;
    User findByUsername(String username) throws DatabaseException;
    void updateUser(int userId, String newUsername, String newAvatarUrl) throws DatabaseException;
}
