//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

import org.example.InvalidStudentDataException;
import org.example.Student;
import org.example.StudentCsvReader;
import org.example.StudentFileReadException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aminoiu
 * @since 6/23/2026
 */
public class StudentCsvReaderTest {
    private static StudentCsvReader csvReader;

    @BeforeAll
    static void setUp() {
        csvReader = new StudentCsvReader();
    }

    @Test
    void readStudentsFromCsv_validCsvFile_returnsListOfStudents() {
        List<Student> result = csvReader.readStudentsFromCsv("src/test/resources/students.csv");

        assertEquals(3, result.size());
        assertEquals("Ana", result.get(0).getName());
        assertEquals(9.5, result.get(0).getGrade());
        assertEquals("Ion", result.get(1).getName());
        assertEquals(8.5, result.get(1).getGrade());
        assertEquals("Gheorghe", result.get(2).getName());
        assertEquals(6.4, result.get(2).getGrade());
    }

    @Test
    void readStudentsFromCsv_emptyFile_returnsEmptyList() {
        List<Student> result = csvReader.readStudentsFromCsv("src/test/resources/empty.csv");

//        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void readStudentFromCsv_missingFile_throwsException() {
        assertThrows(StudentFileReadException.class, () -> csvReader.readStudentsFromCsv("src/test/resources/missing.csv"));
    }

    @Test
    void readStudentFromCsv_invalidData_throwsException() {
        assertThrows(InvalidStudentDataException.class, () -> csvReader.readStudentsFromCsv("src/test/resources/invalid.csv"));
    }
}