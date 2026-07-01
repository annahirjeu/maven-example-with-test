//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 7/1/2026
 */
public class ReportRepository {
    private final Connection connection;

    public ReportRepository(Connection connection) {
        this.connection = connection;
    }

    public List<StudentPerformance> getBestNStudent(long limit) {
        String sql = "select s.id, s.name," +
                "round(avg(grade),2) as avg_grade " +
                "from students s " +
                "join exam_results er on er.student_id =s.id " +
                "group by s.id,s.name " +
                "order by avg_grade desc " +
                "limit ?";
        List<StudentPerformance> studentPerformances = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, limit);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double avgGrade = resultSet.getDouble("avg_grade");
                studentPerformances.add(new StudentPerformance(id, name, avgGrade));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve best student", e);
        }
        return studentPerformances;
    }

    public List<StudentPerformance> getWorstNStudent(long limit) {
        String sql = "select s.id, s.name," +
                "round(avg(grade),2) as avg_grade " +
                "from students s " +
                "join exam_results er on er.student_id =s.id " +
                "group by s.id,s.name " +
                "order by avg_grade asc " +
                "limit ?";
        List<StudentPerformance> studentPerformances = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, limit);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double avgGrade = resultSet.getDouble("avg_grade");
                studentPerformances.add(new StudentPerformance(id, name, avgGrade));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve best student", e);
        }
        return studentPerformances;
    }

}