//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class GroupRepository {
    private final Connection connection;

    public GroupRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer saveGroup(String groupName) {
        Integer groupId = getGroupIdByName(groupName);
        if (groupId != null) {
            return groupId;
        }
        String sql = "INSERT INTO groups (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, groupName);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                throw new RuntimeException("Failed to save group to the database");
            } else {
                System.out.println("Group saved successfully: " + updatedRows + " row(s) updated");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save group to the database", e);
        }
        return getGroupIdByName(groupName);
    }

    public Integer getGroupIdByName(String groupName) {
        String sql = "SELECT id FROM groups WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, groupName);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get group id by name", e);
        }
        return null;
    }
}