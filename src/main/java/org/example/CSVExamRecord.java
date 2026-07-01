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
public record CSVExamRecord(int studentId, String studentName, String email, String groupName, String courseCode,
                            String courseName, String teacherName, String examType, double grade, LocalDate examDate) {
}