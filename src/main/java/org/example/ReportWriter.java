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
    public void writeReport(String filePath, ReportData reportData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("STUDENT GRADES REPORT\n");
            writer.write("=====================\n");
            writer.write("Total students: " + reportData.totalStudents() + "\n");
            writer.write("Average grade: " + String.format("%.2f", reportData.averageGrade()) + "\n");
            writer.write("Pass rate: " + String.format("%.2f", reportData.passRate()) + "\n");
            writer.write("Best student: " + reportData.bestStudent() + "\n");
            writer.write("Worst student: " + reportData.worstStudent() + "\n");
            writer.write("\nTop 3 students:\n");
            for (StudentPerformance student : reportData.topStudents()) {
                writer.write(student.toString() + "\n");
            }
            writer.write("\nGrade distribution:\n");
            for (Map.Entry<Double, Long> entry : reportData.gradeDistribution().entrySet()) {
                writer.write("Grade " + entry.getKey() + ": " + entry.getValue() + " student(s)\n");
            }
            writer.write("\nPassed students:\n");
            for (StudentPerformance student : reportData.passedStudents()) {
                writer.write(student.toString() + "\n");
            }
            writer.write("\nFailed students:\n");
            for (StudentPerformance student : reportData.failedStudents()) {
                writer.write(student.toString() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to write report to file: " + filePath, e);
        }
    }
}