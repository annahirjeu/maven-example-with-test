//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/29/2026
 */
public class StudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveStudent(Student student) {
        if (existsById(student.getId())) {
            update(student);
            return;
        }
        String sql = "INSERT INTO students (id, name, email, group_id) VALUES (?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getGroupId());
            int nrOfRowsUpdated = preparedStatement.executeUpdate();
            if (nrOfRowsUpdated == 0) {
                throw new RuntimeException("Failed to save student to the database");
            } else {
                System.out.println("Student saved successfully: " + nrOfRowsUpdated + " row(s) updated");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save student to the database", e);
        }
    }

    public boolean existsById(int id) {
        String sql = "SELECT id FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                //return result.next();
                if (result.next()) {
                    System.out.println("Student with id " + id + " exists in the database");
                    return true;
                } else {
                    System.out.println("Student with id " + id + " does not exist in the database");
                    return false;
                }


            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check if student exists in the database", e);
        }
    }

    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, group_id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, student.getId());
            int nrOfRowsUpdated = preparedStatement.executeUpdate();
            if (nrOfRowsUpdated == 0) {
                throw new RuntimeException("Failed to update student in the database");
            } else {
                System.out.println("Student updated successfully: " + nrOfRowsUpdated + " row(s) updated");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update student in the database", e);
        }
    }

    public long getTotalStudents() {
        String sql= "select count(*) as total_students from students";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getLong("total_students");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get total students", e);
        }
        return 0;
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                List<Student> students = new java.util.ArrayList<>();
                while (result.next()) {
                    Student student = new Student(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("email"),
                            result.getInt("group_id")
                    );
                    students.add(student);
                }
                return students;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get all students", e);
        }
    }
}
