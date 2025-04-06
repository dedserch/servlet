package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.AuthService;
import com.serzhputovski.servlet.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class AuthServiceImpl implements AuthService {
    private static UserService userService = new UserServiceImpl();

    public void register(User user) throws DatabaseException {
        boolean isUsernameAvailable = userService.isUsernameAvailable(user.getUsername());
        if (!isUsernameAvailable) {
            throw new DatabaseException("Username is already taken");
        }
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        int generatedId = userService.save(user.getUsername(), user.getEmail(), user.getPassword());
        user.setId(generatedId);
    }

    public void login(String username, String password) throws DatabaseException {
        User user = userService.findByUsername(username);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new DatabaseException("Invalid username or password");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
