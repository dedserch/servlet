package com.serzhputovski.servlet.servlet;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.AuthService;
import com.serzhputovski.servlet.service.UserService;
import com.serzhputovski.servlet.service.impl.AuthServiceImpl;
import com.serzhputovski.servlet.service.impl.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authService = new AuthServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            authService.login(username, password);
            User user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/profile/profile.jsp");
        } catch (DatabaseException e) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
