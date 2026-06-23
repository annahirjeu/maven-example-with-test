//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

import org.example.Student;
import org.example.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author aminoiu
 * @since 6/23/2026
 */
public class StudentServiceTest {
    private StudentService studentService;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();

        Student student = new Student(1, "John Doe", 10.0);
        Student student2 = new Student(2, "Jane Doe", 9.5);
        Student student3 = new Student(3, "Bob Smith", 7.8);
        Student student4 = new Student(4, "Alice Johnson", 6.5);
        Student student5 = new Student(5, "Charlie Brown", 4.5);
        students = List.of(student, student2, student3, student4, student5);
    }

    @Test
    void calculateAverageGrade_withValidData_calculatesAvgGradeCorrectly() {
        double avgGrade = studentService.calculateAverageGrade(students);
        double expectedAvgGrade = 7.66;
        assertEquals(expectedAvgGrade, avgGrade, 0.01);
    }

    @Test
    void calculateAverageGrade_withEmptyList_returnsZero() {
        double avgGrade = studentService.calculateAverageGrade(List.of());
        assertEquals(0.0, avgGrade, 0.01);
    }

    @Test
    void findBestStudent_withValidData_returnsCorrectStudent() {
        Optional<Student> bestStudent = studentService.findBestStudent(students);
        assertTrue(bestStudent.isPresent());
        assertEquals("John Doe", bestStudent.get().getName());
    }

    @Test
    void findBestStudent_withEmptyList_returnsEmptyOptional() {
        Optional<Student> bestStudent = studentService.findBestStudent(List.of());
        assertTrue(bestStudent.isEmpty());
    }

    @Test
    void findWorstStudent_withValidData_returnsCorrectStudent() {
        Optional<Student> worstStudent = studentService.findWorstStudent(students);
        assertTrue(worstStudent.isPresent());
        assertEquals("Charlie Brown", worstStudent.get().getName());
    }

    @Test
    void getPassedStudents_withValidData_returnsCorrectStudents() {
        List<Student> passedStudents = studentService.getPassedStudents(students);
        assertEquals(4, passedStudents.size());
        assertEquals("John Doe", passedStudents.get(0).getName());
    }

    @Test
    void getFailedStudents_withValidData_returnsCorrectStudents() {
        List<Student> failedStudents = studentService.getFailedStudents(students);
        assertEquals(1, failedStudents.size());
        assertEquals("Charlie Brown", failedStudents.get(0).getName());
    }

    @Test
    void calculatePassRate_withValidData_returnRate() {
        double rate = studentService.calculatePassRate(students);
        double expectedRate = 4 * 100.0 / 5;
        System.out.println("Calculated pass rate: " + rate);
        assertEquals(expectedRate, rate, 0.01);
    }

    @Test
    void findStudentByName_withValidName_returnsCorrectStudent() {
        Optional<Student> student = studentService.findStudentByName(students, "Bob Smith");
        assertTrue(student.isPresent());
        assertEquals("Bob Smith", student.get().getName());
    }

    @Test
    void getTopStudents_withValidData_returnsCorrectStudents() {
        List<Student> topStudents = studentService.getTopStudents(students, 2);
        assertEquals(2, topStudents.size());
        assertEquals("John Doe", topStudents.get(0).getName());
        assertEquals("Jane Doe", topStudents.get(1).getName());
    }

    @Test
    void getGradeDistribution_withValidData_returnsCorrectDistribution() {
        Student student = new Student(1, "John Doe", 10.0);
        Student student2 = new Student(2, "Jane Doe", 10.0);
        Student student3 = new Student(3, "Bob Smith", 7.8);
        Student student4 = new Student(4, "Alice Johnson", 5.0);
        Student student5 = new Student(5, "Charlie Brown", 5.0);
        List<Student> students = List.of(student, student2, student3, student4, student5);
        Map<Double, Long> distribution = studentService.getGradeDistribution(students);
        assertEquals(3, distribution.size());
        assertEquals(2L, distribution.get(10.0));
        assertEquals(1L, distribution.get(7.8));
        assertEquals(2L, distribution.get(5.0));
    }

}