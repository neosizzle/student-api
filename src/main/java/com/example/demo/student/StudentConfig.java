//db seeding 
package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student test = new Student(
                "nmame",
                "email",
                LocalDate.of(2002, 12, 12)
            );

            Student test2 = new Student(
                "nmame2",
                "email2",
                LocalDate.of(2002, 12, 12)
            );


            repository.saveAll(List.of(test,test2));
        };
    }
}