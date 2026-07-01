package org.example;


import java.sql.Connection;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class Main {
    private static final String STUDENTS_CSV_FILE_PATH = "input/students.csv";
    private static final String REPORT_FILE_PATH = "output/report.txt";

    public static void main(String[] args) throws Exception {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            StudentCsvReader csvReader = new StudentCsvReader();
            CsvImportService csvImportService = new CsvImportService(connection);
            List<CSVExamRecord> records = csvReader.readStudentsFromCsv(STUDENTS_CSV_FILE_PATH);
            csvImportService.importRecord(records);

            ReportWriter reportWriter = new ReportWriter();
            ReportService reportService = new ReportService(connection);
            ReportData reportData = reportService.buildReportData(2);
            reportWriter.writeReport(REPORT_FILE_PATH, reportData);
        }
/*        printAllStudents(students);
        reportWriter.writeReport(REPORT_FILE_PATH, students);*/
    }

    private static void printAllStudents(List<Student> students) {
        System.out.println("All students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}