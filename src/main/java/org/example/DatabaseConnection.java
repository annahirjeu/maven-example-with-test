//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author aminoiu
 * @since 6/29/2026
 */
public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/student_management";
    private static final String USERNAME = "student_app_user";
    private static final String PASSWORD = "student123";

    public static Connection getDatabaseConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}