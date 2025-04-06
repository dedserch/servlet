package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;

public interface ActivationCodeService {
    void generateAndSendVerificationCode(User user) throws DatabaseException;
    boolean verifyCode(String token) throws DatabaseException;
}
