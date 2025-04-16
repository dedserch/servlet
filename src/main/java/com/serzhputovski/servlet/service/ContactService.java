package com.serzhputovski.servlet.service;

import com.serzhputovski.servlet.entity.Contact;
import com.serzhputovski.servlet.exception.DatabaseException;
import java.util.List;

public interface ContactService {
    List<Contact> findContactsByUserIdPaged(int userId, int page, int pageSize) throws DatabaseException;
    int countContactsByUserId(int userId) throws DatabaseException;
    void deleteContact(int contactId, int userId) throws DatabaseException;
    void addContact(Contact contact) throws DatabaseException;
}
