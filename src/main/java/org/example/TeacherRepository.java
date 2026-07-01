//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class TeacherRepository {
    private final Connection connection;

    public TeacherRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer saveTeacher(String name) {
        Integer teacherId = getTeacherIdByName(name);
        if (teacherId != null) {
            return teacherId;
        }

        String sql = "INSERT INTO teachers (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                throw new RuntimeException("Failed to save teacher to the database");
            } else {
                System.out.println("Teacher saved successfully: " + updatedRows + " row(s) updated");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save teacher to the database", e);
        }
        return getTeacherIdByName(name);
    }

    public Integer getTeacherIdByName(String name) {
        String sql = "SELECT id FROM teachers WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save teacher to the database", e);
        }
        return null;
    }
}