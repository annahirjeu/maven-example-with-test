//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

import org.example.ReportWriter;
import org.example.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author aminoiu
 * @since 6/23/2026
 */
public class ReportWriterTest {
    private static List<Student> students;
    private static ReportWriter reportWriter;

    @BeforeAll
    static void setUp() {
        Student student = new Student(1, "John Doe", 10.0);
        Student student2 = new Student(2, "Jane Doe", 9.5);
        Student student3 = new Student(3, "Bob Smith", 7.8);
        Student student4 = new Student(4, "Alice Johnson", 6.5);
        Student student5 = new Student(5, "Charlie Brown", 4.5);
        students = List.of(student, student2, student3, student4, student5);
        reportWriter = new ReportWriter();
    }

    @Test
    void writeReport_withValidData_reportCompleted() throws IOException {
        Path reportPath = Path.of("src/test/resources/report.txt");

        reportWriter.writeReport(reportPath.toString(), students);
        String content = Files.readString(reportPath);
        assertTrue(content.contains("STUDENT GRADES REPORT"));
        assertTrue(content.contains("Total students: 5"));
        assertTrue(content.contains("Average grade: 7.66"));
        assertTrue(content.contains("Pass rate: 80.0"));
        assertTrue(content.contains("Best student: "));
        assertTrue(content.contains("Worst student: "));
        assertTrue(content.contains("Top 3 students:"));
        assertTrue(content.contains("Grade distribution:"));
        assertTrue(content.contains("Failed students:"));
        assertTrue(content.contains("Passed students:"));

    }

    @Test
    void writeReport_withEmptyData_reportCompleted() throws IOException {
        Path reportPath = Path.of("src/test/resources/report.txt");
        reportWriter.writeReport(reportPath.toString(), List.of());
        String content = Files.readString(reportPath);
        assertTrue(content.contains("STUDENT GRADES REPORT"));
        assertTrue(content.contains("Total students: 0"));
        assertTrue(content.contains("Average grade: 0.00"));
        assertTrue(content.contains("Pass rate: 0.00"));
        assertTrue(content.contains("Best student: null"));
        assertTrue(content.contains("Worst student: null"));
        assertTrue(content.contains("Top 3 students:"));
        assertTrue(content.contains("Grade distribution:"));
        assertTrue(content.contains("Failed students:"));
        assertTrue(content.contains("Passed students:"));
    }

    @Test
    void writeReport_invalidDirectory_throwsException() {
        Path invalidPath = Path.of("invalid_directory/report.txt");
/*        try {
            reportWriter.writeReport(invalidPath.toString(), students);
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Failed to write report to file:"));
        }*/
        assertThrows(RuntimeException.class, () -> reportWriter.writeReport(invalidPath.toString(), students));
    }
}