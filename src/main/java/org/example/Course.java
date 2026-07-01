//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

/**
 *
 * @author aminoiu
 * @since 6/30/2026
 */
public class Course {
    private int id;
    private final String code;
    private final String name;
    private final int teacherId;

    public Course(String code, String name, int teacherId) {
        this.code = code;
        this.name = name;
        this.teacherId = teacherId;
    }

    public Course(int id, String code, String name, int teacherId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}