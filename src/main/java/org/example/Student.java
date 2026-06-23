//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
public class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}