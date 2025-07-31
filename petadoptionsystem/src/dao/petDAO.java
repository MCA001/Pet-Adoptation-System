package dao;

import database.DBConnection;
import model.pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class petDAO {

    // CREATE
    public void addPet(pet pet) {
        String sql = "INSERT INTO pet (name, type, age, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getStatus());

            stmt.executeUpdate();
            System.out.println("Pet added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding pet: " + e.getMessage());
        }
    }

    // READ
    public List<pet> getAllpets() {
        List<pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pet pet = new pet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("age"),
                        rs.getString("status")
                );
                pets.add(pet);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving pets: " + e.getMessage());
        }
        return pets;
    }

    // UPDATE
    public void updatePet(pet pet) {
        String sql = "UPDATE pet SET name=?, type=?, age=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getStatus());
            stmt.setInt(5, pet.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Pet updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating pet: " + e.getMessage());
        }
    }

    // DELETE
    public void deletePet(int id) {
        String sql = "DELETE FROM pet WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Pet deleted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting pet: " + e.getMessage());
        }
    }
}

