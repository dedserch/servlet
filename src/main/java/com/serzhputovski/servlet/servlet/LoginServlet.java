package com.serzhputovski.servlet.servlet;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import com.serzhputovski.servlet.service.UserService;
import com.serzhputovski.servlet.service.impl.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/account/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.findByUsername(username);
            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/account/login.html").forward(request, response);
                return;
            }
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/account/welcome.html");
            System.out.println("Account logged in");
        } catch (DatabaseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("/account/login.html").forward(request, response);
        }
    }
}
