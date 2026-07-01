//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.time.LocalDate;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class ExamResults {
    private int id;
    private final int studentId;
    private final int courseId;
    private final String examType;
    private final double grade;
    private final LocalDate examDate;

    public ExamResults(int studentId, int courseId, String examType, double grade, LocalDate examDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.examType = examType;
        this.grade = grade;
        this.examDate = examDate;
    }

    public ExamResults(int id, int studentId, int courseId, String examType, double grade, LocalDate examDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.examType = examType;
        this.grade = grade;
        this.examDate = examDate;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getExamType() {
        return examType;
    }

    public double getGrade() {
        return grade;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    @Override
    public String toString() {
        return "ExamResults{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", examType='" + examType + '\'' +
                ", grade=" + grade +
                ", examDate=" + examDate +
                '}';
    }
}