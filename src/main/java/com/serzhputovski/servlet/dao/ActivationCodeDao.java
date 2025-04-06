package com.serzhputovski.servlet.dao;

import com.serzhputovski.servlet.entity.ActivationCode;
import com.serzhputovski.servlet.exception.DatabaseException;

import java.time.LocalDateTime;

public interface ActivationCodeDao {
    void save(int userId, String code, LocalDateTime expiration) throws DatabaseException;
    ActivationCode findByCode(String code) throws DatabaseException;
    void delete(int userId) throws DatabaseException;
}
