package com.serzhputovski.servlet.service.impl;

import com.serzhputovski.servlet.dao.ContactDao;
import com.serzhputovski.servlet.dao.impl.ContactDaoImpl;
import com.serzhputovski.servlet.entity.Contact;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    public ContactServiceImpl() {
        this.contactDao = new ContactDaoImpl();
    }

    @Override
    public List<Contact> findContactsByUserId(int userId) throws DatabaseException {
        return contactDao.findContactsByUserId(userId);
    }

    @Override
    public void deleteContact(int contactId, int userId) throws DatabaseException {
        contactDao.deleteContact(contactId, userId);
    }

    @Override
    public void addContact(Contact contact) throws DatabaseException {
        contactDao.addContact(contact);
    }
}
