package com.serzhputovski.servlet.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    private int idUser;

    @NotBlank(message = "The username must not be empty")
    @Size(min = 5, max = 40, message = "The username must contain from 2 to 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "The username must not contain special symbols")
    private String username;

    @NotBlank(message = "The password must not be empty")
    @Size(min=8, max=64, message="The password must contains at least 8 symbols and maximum 64 symbols")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "The password must contain latins characters, numbers or \"_\"")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
