//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class StudentService {
    public double calculateAverageGrade(List<Student> students) {
        return students.stream()
                .mapToDouble(student -> student.getGrade())
                .average()
                .orElse(0);
    }

    public Optional<Student> findBestStudent(List<Student> students) {
        return students.stream()
                .max((s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()));
    }

    public Optional<Student> findWorstStudent(List<Student> students) {
        return students.stream()
                .min((s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()));
    }

    public List<Student> getPassedStudents(List<Student> students) {
        return students.stream()
                .filter(student -> student.getGrade() >= 5)
                .toList();
    }

    public List<Student> getFailedStudents(List<Student> students) {
        return students.stream()
                .filter(student -> student.getGrade() < 5)
                .toList();
    }

    public double calculatePassRate(List<Student> students) {
        if (students.isEmpty()) {
            return 0;
        }
        long passedCount = getPassedStudents(students).size();
        return passedCount * 100.0 / students.size();
    }

    public Optional<Student> findStudentByName(List<Student> students, String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Student> getTopStudents(List<Student> students, int limit) {
        return students.stream()
                .sorted(Comparator.comparing(student -> ((Student) student).getGrade()).reversed())
                .limit(limit)
                .toList();
    }

    public Map<Double, Long> getGradeDistribution(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.getGrade(),
                        Collectors.counting()));
    }
}