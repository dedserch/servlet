package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.dao.impl.UserDaoImpl;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = new UserDaoImpl();

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean isUsernameAvailable(String username) {
        User user = userDao.findByUsername(username);

        return user == null;
    }

    public void registerUser(User user) {
        boolean isUsernameAvailable = isUsernameAvailable(user.getUsername());
        if (!isUsernameAvailable) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println(user.getPassword());

        try {
            userDao.save(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
