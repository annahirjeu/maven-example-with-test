//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 7/1/2026
 */
public class ReportService {
    private final ReportRepository reportRepository;
    private final StudentRepository studentRepository;
    private final ExamResultRepository examResultRepository;

    public ReportService(Connection connection) {
        this.reportRepository = new ReportRepository(connection);
        this.studentRepository = new StudentRepository(connection);
        this.examResultRepository = new ExamResultRepository(connection);
    }

    public ReportData buildReportData(int topStudentLimit) {
        long totalStudents = studentRepository.getTotalStudents();
        List<StudentPerformance> worstStudents = reportRepository.getWorstNStudent(totalStudents);
        List<StudentPerformance> bestStudents = reportRepository.getBestNStudent(totalStudents);
        return new ReportData(totalStudents,
                examResultRepository.getAverageGrade(5, "FINAL"),
                examResultRepository.getPassRate(5, "FINAL"),
                bestStudents != null ? bestStudents.get(0) : null,
                worstStudents != null ? worstStudents.get(0) : null,
                bestStudents.subList(0, Math.min(topStudentLimit, bestStudents.size())),
                examResultRepository.getGradeDistribution(),
                worstStudents,
                bestStudents
        );
    }

}