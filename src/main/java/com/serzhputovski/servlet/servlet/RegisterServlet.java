package com.serzhputovski.servlet.servlet;

import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.service.impl.UserServiceImpl;
import com.serzhputovski.servlet.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/account/register.html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        if(!userServiceImpl.isUsernameAvailable(username)){
            request.setAttribute("error", "Username is already taken");
            request.getRequestDispatcher("/account/register.html").forward(request, response);
            return;
        }

        try{
            UserValidator validator = new UserValidator();
            User user = new User(username, password);
            validator.validate(user);
            userServiceImpl.registerUser(user);
            response.sendRedirect("/account/login.html");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("/account/register.html").forward(request, response);
        }
    }

}
