//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aminoiu
 * @since 6/22/2026
 */
public class ReportWriter {
    public void writeReport(String filePath, List<Student> students) {
        StudentService studentService = new StudentService();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("STUDENT GRADES REPORT\n");
            writer.write("=====================\n");
            writer.write("Total students: " + students.size() + "\n");
            writer.write("Average grade: " + String.format("%.2f", studentService.calculateAverageGrade(students)) + "\n");
            writer.write("Pass rate: " + String.format("%.2f", studentService.calculatePassRate(students)) + "\n");
            writer.write("Best student: " + studentService.findBestStudent(students).orElse(null) + "\n");
            writer.write("Worst student: " + studentService.findWorstStudent(students).orElse(null) + "\n");
            writer.write("\nTop 3 students:\n");
            for (Student student : studentService.getTopStudents(students, 3)) {
                writer.write(student.toString() + "\n");
            }
            writer.write("\nGrade distribution:\n");
            for (Map.Entry<Double, Long> entry : studentService.getGradeDistribution(students).entrySet()) {
                writer.write("Grade " + entry.getKey() + ": " + entry.getValue() + " student(s)\n");
            }
            writer.write("\nPassed students:\n");
            for (Student student : studentService.getPassedStudents(students)) {
                writer.write(student.toString() + "\n");
            }
            writer.write("\nFailed students:\n");
            for (Student student : studentService.getFailedStudents(students)) {
                writer.write(student.toString() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + filePath, e);
        }
    }
}