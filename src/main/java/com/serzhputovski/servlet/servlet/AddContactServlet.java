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

@WebServlet("/contacts/add")
public class AddContactServlet extends HttpServlet {
    private final ContactService contactService = new ContactServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp");
            return;
        }
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        Contact contact = new Contact(user.getId(), name, phone, email);
        try {
            contactService.addContact(contact);
            resp.sendRedirect(req.getContextPath() + "/contacts");
        } catch (DatabaseException e) {
            throw new ServletException("Unable to add contact", e);
        }
    }
}
