package com.jr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Galatyuk Ilya
 */

@SpringBootApplication
@EnableTransactionManagement
public class Launch {

    public static void main(String[] args) {
        SpringApplication.run(Launch.class, args);
    }
}
