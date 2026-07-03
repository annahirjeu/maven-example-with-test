//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class ExamResultRepository {
    private final Connection connection;

    public ExamResultRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveExamResult(ExamResults examResult) {
        String sql = "INSERT INTO exam_results (student_id, course_id, exam_type, grade, exam_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, examResult.getStudentId());
            preparedStatement.setInt(2, examResult.getCourseId());
            preparedStatement.setString(3, examResult.getExamType());
            preparedStatement.setDouble(4, examResult.getGrade());
            preparedStatement.setDate(5, Date.valueOf(examResult.getExamDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save exam result", e);
        }
    }

    public double getAverageGrade() {
        String sql = "SELECT ROUND(AVG(grade), 2) as average_grade FROM exam_results";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getDouble("average_grade");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get average grade", e);
        }
        return 0;
    }

    public double getAverageGrade(int courseId, String examType) {
        String sql = "SELECT ROUND(AVG(grade), 2) as average_grade FROM exam_results WHERE course_id = ? AND exam_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setString(2, examType);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getDouble("average_grade");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get average grade for course " + courseId + " and exam type " + examType, e);
        }
        return 0;
    }

    public double getPassRate(int courseId, String examType) {
        String sql = "with average_grade as (" +
                "select count(*) as nrOfStudentsPassed " +
                "from exam_results er " +
                "where er.course_id = ? and exam_type=? and er.grade>=5" +
                ")," +
                "total_students as (" +
                "select count(*) as nrOfStudents " +
                "from exam_results er " +
                "where er.course_id = ? and exam_type=? " +
                ")" +
                "select round(nrOfStudentsPassed*100.0/nrOfStudents,2) as pass_rate " +
                "from average_grade ag, total_students ts";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setString(2, examType);
            statement.setInt(3, courseId);
            statement.setString(4, examType);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getDouble("pass_rate");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get pass rate for course " + courseId + " and exam type " + examType, e);
        }
        return 0;
    }

    public Map<Double, Long> getGradeDistribution() {
        String sql = "select grade, count(*) as numberOfStudents " +
                "from exam_results er " +
                "group by grade " +
                "order by grade";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                Map<Double, Long> distribution = new HashMap<>();
                while (result.next()) {
                    distribution.put(result.getDouble("grade"), result.getLong("numberOfStudents"));
                }
                return distribution;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get grade distribution", e);
        }
    }


}
