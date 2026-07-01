//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.util.List;
import java.util.Map;

/**
 *
 * @author aminoiu
 * @since 7/1/2026
 */
public record ReportData(long totalStudents,
                         double averageGrade,
                         double passRate,
                         StudentPerformance bestStudent,
                         StudentPerformance worstStudent,
                         List<StudentPerformance> topStudents,
                         Map<Double, Long> gradeDistribution,
                         List<StudentPerformance> failedStudents,
                         List<StudentPerformance> passedStudents) {
}