package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String username, String password) {
        UserModel userModel = new UserModel();
        String hashedPassword = userModel.getPassword(username);

        if (hashedPassword == null) return false;

        return BCrypt.checkpw(password, hashedPassword);
    }

    public boolean isUsernameAvailable(String username) {
        UserModel userModel = new UserModel();

        User user = userModel.findUserByUsername(username);

        return user == null;
    }

    public void registerUser(User user) {
        UserService userService = new UserService();
        UserModel userModel = new UserModel();
        if (!userService.isUsernameAvailable(user.getUsername())) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String hashedPassword = userService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println(user.getPassword());

        try {
            userModel.addUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
