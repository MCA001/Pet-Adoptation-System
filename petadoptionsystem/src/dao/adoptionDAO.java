package dao;

import database.DBConnection;
import model.adoption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adoptionDAO {

    // CREATE - Add new adoption record
    public void addAdoption(adoption adoption) {
        String sql = "INSERT INTO adoption (pet_id, adopter_id, adoption_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adoption.getPetId());
            stmt.setInt(2, adoption.getAdopterId());
            stmt.setDate(3, java.sql.Date.valueOf(adoption.getAdoptionDate()));


            stmt.executeUpdate();
            System.out.println("Adoption record added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding adoption record: " + e.getMessage());
        }
    }

    // READ - Get all adoption records
    public List<adoption> getAllAdoptions() {
        List<adoption> adoptions = new ArrayList<>();
        String sql = "SELECT * FROM adoption";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                adoption adoption = new adoption(
                        rs.getInt("id"),
                        rs.getInt("pet_id"),
                        rs.getInt("adopter_id"),
                        rs.getDate("adoption_date").toLocalDate()



                );
                adoptions.add(adoption);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving adoptions: " + e.getMessage());
        }
        return adoptions;
    }

    // UPDATE - Update adoption record
    public void updateAdoption(adoption adoption) {
        String sql = "UPDATE adoption SET pet_id = ?, adopter_id = ?, adoption_date = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adoption.getPetId());
            stmt.setInt(2, adoption.getAdopterId());
           // stmt.setDate(3, java.sql.Date.valueOf(adoption.getAdoptionDate()));
            stmt.setInt(4, adoption.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Adoption record updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating adoption record: " + e.getMessage());
        }
    }

    // DELETE - Delete adoption by ID
    public void deleteAdoption(int id) {
        String sql = "DELETE FROM adoption WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Adoption record deleted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting adoption record: " + e.getMessage());
        }
    }
}

