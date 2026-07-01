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
public class CourseRepository {
    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer saveCourse(Course course) {
        Course existingCourse = getCourseByCode(course.getCode());
        if (existingCourse != null) {
            update(course);
            return existingCourse.getId();
        }

        String sql = "INSERT INTO courses (code, name, teacher_id) VALUES ( ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getCode());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setInt(3, course.getTeacherId());
            int numberOfRowsUpdated = preparedStatement.executeUpdate();
            if (numberOfRowsUpdated == 0) {
                throw new RuntimeException("Failed to save course");
            } else {
                System.out.println("Course saved successfully: " + numberOfRowsUpdated + " row(s) updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save course", e);
        }
        return getCourseByCode(course.getCode()).getId();
    }

    private void update(Course course) {
        String sql = "UPDATE courses SET name = ?, teacher_id = ? WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getTeacherId());
            preparedStatement.setString(3, course.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update course", e);
        }
    }

    public Course getCourseByCode(String code) {
        String sql = "SELECT * FROM courses WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) return new Course(
                        result.getInt("id"),
                        result.getString("code"),
                        result.getString("name"),
                        result.getInt("teacher_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get course by code", e);
        }
        return null;
    }
}