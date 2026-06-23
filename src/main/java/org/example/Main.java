package org.example;


import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class Main {
    private static final String STUDENTS_CSV_FILE_PATH = "input/students.csv";
    private static final String REPORT_FILE_PATH = "output/report.txt";

    public static void main(String[] args) {
        StudentCsvReader csvReader = new StudentCsvReader();
        ReportWriter reportWriter = new ReportWriter();
        List<Student> students = csvReader.readStudentsFromCsv(STUDENTS_CSV_FILE_PATH);
        printAllStudents(students);
        reportWriter.writeReport(REPORT_FILE_PATH, students);
    }

    private static void printAllStudents(List<Student> students) {
        System.out.println("All students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}