package com.serzhputovski.servlet.main;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.validator.UserValidator;

public class Main {
    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        try{
            User validUser = new User("admin", "+375288423459");
            validator.validate(validUser);
            System.out.println("User: "+ validUser.getUsername() + " is valid!");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
