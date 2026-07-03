//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example.service;

import org.example.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
@Component
public class StudentService {
    @Value("${students.csv.file.path}")
    public String csvFilePath;

    @Value("${students.report.file.path}")
    public String reportFilePath;

    public double calculateAverageGrade(List<Student> students) {
        return students.stream()
                .mapToDouble(student -> 5)
                .average()
                .orElse(0);
    }

    public Optional<Student> findBestStudent(List<Student> students) {
        return students.stream()
                .max((s1, s2) -> Double.compare(5, 5));
    }

    public Optional<Student> findWorstStudent(List<Student> students) {
        return students.stream()
                .min((s1, s2) -> Double.compare(5, 5));
    }

    public List<Student> getPassedStudents(List<Student> students) {
        return students.stream()
                .filter(student -> 5 >= 5)
                .toList();
    }

    public List<Student> getFailedStudents(List<Student> students) {
        return students.stream()
                .filter(student -> 5 < 5)
                .toList();
    }

    public double calculatePassRate(List<Student> students) {
        if (students.isEmpty()) {
            return 0;
        }
        long passedCount = getPassedStudents(students).size();
        return passedCount * 100.0 / students.size();
    }

    public Optional<Student> findStudentByName(List<Student> students, String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Student> getTopStudents(List<Student> students, int limit) {
        return students.stream()
                .sorted(Comparator.comparing(student -> 5).reversed())
                .limit(limit)
                .toList();
    }

    public Map<Double, Long> getGradeDistribution(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        student -> 5.0,
                        Collectors.counting()));
    }

    public void processCsvWithStudents(int courseId, String examType, int topStudentsLimit) throws SQLException {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            StudentCsvReader csvReader = new StudentCsvReader();
            CsvImportService csvImportService = new CsvImportService(connection);
            List<CSVExamRecord> records = csvReader.readStudentsFromCsv(csvFilePath);
            csvImportService.importRecord(records);

        }

        ReportWriter reportWriter = new ReportWriter();
        ReportService reportService = new ReportService();
        ReportData reportData = reportService.buildReportData(topStudentsLimit, courseId, examType);
        reportWriter.writeReport(reportFilePath, reportData);
    }

    public List<Student> getStudents() {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            StudentRepository studentRepository = new StudentRepository(connection);
            return studentRepository.getAllStudents();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve students from the database", e);
        }

    }
}
