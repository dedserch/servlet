package com.serzhputovski.servlet.dao.impl;

import com.serzhputovski.servlet.dao.ContactDao;
import com.serzhputovski.servlet.database.DatabaseConnection;
import com.serzhputovski.servlet.entity.Contact;
import com.serzhputovski.servlet.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    @Override
    public List<Contact> findContactsByUserId(int userId) throws DatabaseException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contacts.add(new Contact(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch contacts by user ID", e);
        }

        return contacts;
    }

    @Override
    public void deleteContact(int id, int userId) throws DatabaseException {
        String sql = "DELETE FROM contacts WHERE id = ? AND user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete contact", e);
        }
    }

    @Override
    public void addContact(Contact contact) throws DatabaseException {
        String sql = "INSERT INTO contacts (user_id, name, phone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contact.getUserId());
            stmt.setString(2, contact.getName());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add contact", e);
        }
    }
}
