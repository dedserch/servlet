package com.serzhputovski.servlet.servlet;


import com.serzhputovski.servlet.entity.Contact;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.ContactService;
import com.serzhputovski.servlet.service.impl.ContactServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/contacts")
public class ContactListServlet extends HttpServlet {
    private final ContactService contactService = new ContactServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
            return;
        }

        try {
            List<Contact> contacts = contactService.findContactsByUserId(user.getId());
            req.setAttribute("contacts", contacts);
            req.getRequestDispatcher("/contacts/contacts.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException("Unable to load contacts", e);
        }
    }
}
