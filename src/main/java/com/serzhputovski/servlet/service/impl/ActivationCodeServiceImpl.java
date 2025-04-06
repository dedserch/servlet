package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.dao.ActivationCodeDao;
import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.dao.impl.ActivationCodeDaoImpl;
import com.serzhputovski.servlet.dao.impl.UserDaoImpl;
import com.serzhputovski.servlet.entity.ActivationCode;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.ActivationCodeService;
import com.serzhputovski.servlet.util.EmailSenderUtil;
import com.serzhputovski.servlet.util.VerificationUtil;

import java.time.LocalDateTime;

public class ActivationCodeServiceImpl implements ActivationCodeService {
    private final ActivationCodeDao activationCodeDao = new ActivationCodeDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void generateAndSendVerificationCode(User user) throws DatabaseException {
        String token = VerificationUtil.generateVerificationToken();
        LocalDateTime expiration = LocalDateTime.now().plusHours(24);

        activationCodeDao.save(user.getId(), token, expiration);

        try {
            EmailSenderUtil.sendConfirmationEmail(user.getEmail(), token);
        } catch (Exception e) {
            throw new DatabaseException("Failed to send confirmation email", e);
        }
    }

    @Override
    public boolean verifyCode(String token) throws DatabaseException {
        ActivationCode activationCode = activationCodeDao.findByCode(token);
        if (activationCode == null || activationCode.getExpiration().isBefore(LocalDateTime.now())) {
            return false;
        }

        int userId = activationCode.getUserId();
        userDao.updateEnabled(userId);
        activationCodeDao.delete(userId);
        return true;
    }
}
