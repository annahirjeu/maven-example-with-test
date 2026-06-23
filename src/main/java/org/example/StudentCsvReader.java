//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class StudentCsvReader {
    public List<Student> readStudentsFromCsv(String filePath) {
        List<Student> students = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                Student student = createStudent(record);
                students.add(student);
            }

        } catch (IOException e) {
            throw new StudentFileReadException("Error reading CSV file", e);
        }
        return students;
    }

    private Student createStudent(CSVRecord record) {
        int id = parseId(record.get("id"));
        String name = record.get("name");
        double grade = parseGrade(record.get("grade"));
        validateStudent(id, name, grade);
        return new Student(id, name, grade);

    }

    private int parseId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidStudentDataException("Invalid id format[id: " + id + "]");
        }
    }

    private double parseGrade(String grade) {
        try {
            return Double.parseDouble(grade);
        } catch (NumberFormatException e) {
            throw new InvalidStudentDataException("Invalid grade format[grade: " + grade + "]");
        }
    }

    private void validateStudent(int id, String name, double grade) {
        if (id <= 0) {
            throw new InvalidStudentDataException("Student  id must be greater than 0.");
        }
        if (name == null || name.isEmpty()) {
            throw new InvalidStudentDataException("Student name cannot be empty.");
        }

        if (grade < 1 || grade > 10) {
            throw new InvalidStudentDataException("Student grade must be between 1 and 10.");
        }
    }

}