package org.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aminoiu
 * @since 6/19/2026
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);

    }
}
