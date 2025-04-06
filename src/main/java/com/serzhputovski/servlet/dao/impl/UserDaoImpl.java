package com.serzhputovski.servlet.dao.impl;

import com.serzhputovski.servlet.dao.UserDao;
import com.serzhputovski.servlet.database.DatabaseConnection;
import com.serzhputovski.servlet.entity.User;
import com.serzhputovski.servlet.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) throws DatabaseException {
        String query = "SELECT id, username, password, email, enabled, avatar_url FROM users WHERE username = ?";
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getString("username"), resultSet.getString("password"));
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setEnabled(resultSet.getBoolean("enabled"));
                    user.setAvatarUrl(resultSet.getString("avatar_url"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to find user by username", e);
        }
        return user;
    }

    @Override
    public int save(String username, String email, String password) throws DatabaseException {
        String query = "INSERT INTO users (username, email, password, enabled, avatar_url) VALUES (?, ?, ?, false, ?)";
        int generatedId = -1;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, "default-avatar.png");
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to save user", e);
        }
        return generatedId;
    }

    @Override
    public void updateEnabled(int userId) throws DatabaseException {
        String query = "UPDATE users SET enabled = true WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to activate user", e);
        }
    }

    @Override
    public void updateUser(int userId, String newUsername, String newAvatarUrl) throws DatabaseException {
        String query = "UPDATE users SET username = ?, avatar_url = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newUsername);
            statement.setString(2, newAvatarUrl);
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update user", e);
        }
    }
}