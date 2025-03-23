package com.serzhputovski.servlet.servlet;

import com.serzhputovski.servlet.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/account/login.html");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserServiceImpl userServiceImpl = new UserServiceImpl();


        if(userServiceImpl.isUsernameAvailable(username)) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/account/login.html").forward(request, response);
            return;
        }

        if(!userServiceImpl.checkPassword(username, password)) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/account/login.html").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user", username);
        response.sendRedirect(request.getContextPath() + "/account/login.html");
//        System.out.println("Account logged in");
    }
}
