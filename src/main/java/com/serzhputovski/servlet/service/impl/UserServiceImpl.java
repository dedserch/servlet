package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.dao.impl.UserDaoImpl;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public int save(String username, String email, String password) throws DatabaseException {
        return userDao.save(username, email, password);
    }

    @Override
    public User findByUsername(String username) throws DatabaseException {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean isUsernameAvailable(String username) throws DatabaseException {
        User user = userDao.findByUsername(username);
        return user == null;
    }

    @Override
    public void updateUser(int userId, String newUsername, String newAvatarUrl) throws DatabaseException {
        userDao.updateUser(userId, newUsername, newAvatarUrl);
    }
}
