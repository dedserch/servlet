package com.serzhputovski.servlet.dao;

import com.serzhputovski.servlet.entity.Contact;
import com.serzhputovski.servlet.exception.DatabaseException;

import java.util.List;

public interface ContactDao {
    List<Contact> findContactsByUserId(int userId) throws DatabaseException;
    void deleteContact(int id, int userId) throws DatabaseException;
    void addContact(Contact contact) throws DatabaseException;
}
