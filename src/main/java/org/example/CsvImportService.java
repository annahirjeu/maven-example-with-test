//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class CsvImportService {
    private final Connection connection;
    private final StudentRepository studentRepository;
    private final ExamResultRepository examResultRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public CsvImportService(Connection connection) {
        this.connection = connection;
        this.studentRepository = new StudentRepository(connection);
        this.examResultRepository = new ExamResultRepository(connection);
        this.courseRepository = new CourseRepository(connection);
        this.groupRepository = new GroupRepository(connection);
        this.teacherRepository = new TeacherRepository(connection);
    }

    public void importRecord(List<CSVExamRecord> records) {
        for (CSVExamRecord record : records) {
            insertRecord(record);
        }
    }

    private void insertRecord(CSVExamRecord record) {
        Integer groupId = groupRepository.saveGroup(record.groupName());
        Integer teacherId = teacherRepository.saveTeacher(record.teacherName());
        Integer courseId = courseRepository.saveCourse(new Course(record.courseCode(), record.courseName(), teacherId));

        studentRepository.saveStudent(new Student(record.studentId(), record.studentName(), record.email(), groupId));
        examResultRepository.saveExamResult(new ExamResults(record.studentId(), courseId, record.examType(), record.grade(), record.examDate()));


    }
}