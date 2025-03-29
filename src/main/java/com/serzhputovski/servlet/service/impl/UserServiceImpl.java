package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.dao.impl.UserDaoImpl;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) throws DatabaseException {
        boolean isUsernameAvailable = isUsernameAvailable(user.getUsername());
        if (!isUsernameAvailable) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userDao.save(user.getUsername(), user.getPassword());
    }

    public void loginUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());

        if(!hashedPassword.equals(user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    @Override
    public boolean isUsernameAvailable(String username) throws DatabaseException {
        User user = userDao.findByUsername(username);

        return user == null;
    }
}
