package com.serzhputovski.servlet.dao.impl;

import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.database.DatabaseConnection;
import com.serzhputovski.servlet.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    public User findByUsername(String username) {
        String query = "SELECT idUser, username, password FROM user WHERE username = ?";
        User user = null;
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()) {
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void save(String username, String password) {
        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
