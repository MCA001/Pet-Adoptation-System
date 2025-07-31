package dao;

import database.DBConnection;
import model.adopter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adopterDAO {

    // CREATE
    public void addAdopter(adopter adopter) {
        if (adopter.getContact() == null || adopter.getContact().length() != 10) {
        throw new IllegalArgumentException("Contact number must be exactly 10 digits.");
    }
        String sql = "INSERT INTO adopter (name, contact) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, adopter.getName());
            stmt.setString(2, adopter.getContact());

            stmt.executeUpdate();
            System.out.println("Adopter added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding adopter: " + e.getMessage());
        }
    }

    // READ - Get all adopters
    public List<adopter> getAllAdopters() {
        List<adopter> adopters = new ArrayList<>();
        String sql = "SELECT * FROM adopter";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                adopter adopter = new adopter(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact")
                );
                adopters.add(adopter);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving adopters: " + e.getMessage());
        }
        return adopters;
    }

    // UPDATE
    public void updateAdopter(adopter adopter) {
        String sql = "UPDATE adopter SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adopter.getId());
            stmt.setString(2, adopter.getName());
            stmt.setString(3, adopter.getContact());


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Adopter updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error updating adopter: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteAdopter(int id) {
        String sql = "DELETE FROM adopter WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Adopter deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting adopter: " + e.getMessage());
        }
    }
}

