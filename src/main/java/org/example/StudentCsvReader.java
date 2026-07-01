//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class StudentCsvReader {
    public List<CSVExamRecord> readStudentsFromCsv(String filePath) {
        List<CSVExamRecord> students = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                CSVExamRecord student = createRecord(record);
                students.add(student);
            }

        } catch (IOException e) {
            throw new StudentFileReadException("Error reading CSV file", e);
        }
        return students;
    }

    private CSVExamRecord createRecord(CSVRecord record) {
        int studentId = parseId(record.get("student_id"));
        String studentName = record.get("student_name");
        String email = record.get("email");
        String group_name = record.get("group_name");
        String courseCode = record.get("course_code");
        String courseName = record.get("course_name");
        String teacherName = record.get("teacher_name");
        String examType = record.get("exam_type");
        double grade = parseGrade(record.get("grade"));
        LocalDate examDate = LocalDate.parse(record.get("exam_date"));
        validateRecord(studentId, studentName, email, group_name, courseCode, courseName, teacherName, examType, grade, examDate);
        return new CSVExamRecord(studentId, studentName, email, group_name, courseCode, courseName, teacherName, examType, grade, examDate);

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

    private void validateRecord(int id,
                                String studentName,
                                String email,
                                String group_name,
                                String courseCode,
                                String courseName,
                                String teacherName,
                                String examType,
                                double grade,
                                LocalDate examDate) {
        if (id <= 0) {
            throw new InvalidStudentDataException("Student  id must be greater than 0.");
        }
        if (studentName == null || studentName.isEmpty()) {
            throw new InvalidStudentDataException("Student name cannot be empty.");
        }

        if (!isValid(email) || !isValid(group_name) || !isValid(courseCode) || !isValid(courseName) || !isValid(teacherName) || !isValid(examType) || examDate == null) {
            throw new InvalidStudentDataException("Invalid data. Please check the data.");
        }

        if (grade < 1 || grade > 10) {
            throw new InvalidStudentDataException("Student grade must be between 1 and 10.");
        }
    }

    private boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }

}