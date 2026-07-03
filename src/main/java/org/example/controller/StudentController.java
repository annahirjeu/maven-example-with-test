//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example.controller;

import org.example.ExamResults;
import org.example.ReportData;
import org.example.Student;
import org.example.service.ExamResultService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 7/3/2026
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ExamResultService examResultService;

    public StudentController(StudentService studentService, ExamResultService examResultService) {
        this.studentService = studentService;
        this.examResultService = examResultService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/add-from-csv")
    public ResponseEntity<ReportData> processCsvWithStudents(@RequestParam(required = false, name = "topStudentsLimit") int topStudentsLimit,
                                                             @RequestParam(name = "courseId") int courseId,
                                                             @RequestParam(name = "examType") String examType) throws SQLException {
        studentService.processCsvWithStudents(courseId, examType, topStudentsLimit);
        ReportData reportData = examResultService.getExamResults(topStudentsLimit, courseId, examType);
        return ResponseEntity.ok(reportData);

    }
}
