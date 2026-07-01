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
    private String email;
    private int groupId;

    public Student(int id, String name, String email, int groupId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", groupId=" + groupId +
                '}';
    }
}