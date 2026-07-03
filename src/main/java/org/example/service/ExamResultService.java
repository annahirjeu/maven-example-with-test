//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example.service;

import org.example.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 7/3/2026
 */
@Service
public class ExamResultService {
    private final ReportService reportService;

    public ExamResultService(ReportService reportService) {
        this.reportService = reportService;
    }

    public ReportData getExamResults(int topStudentLimit, int courseId, String examType) {
        return reportService.buildReportData(topStudentLimit, courseId, examType);
    }
}
