//student model class
package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity//lets the JPA aware of this class is an entity added to db. must have primary key
@Table//maps this class to create a table in DB
public class Student {
    @Id//defines the primary key for this entity
    @SequenceGenerator(//creates a generator which generates primary ids in a sequence
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )

    @GeneratedValue(//primary id generation strategy
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )

    //propterties
    private long id;
    private String name;
    private String email;
    private LocalDate dob;

    @Transient//declares that this field does not need to be a column in db
    private Integer age;




    //empty constructor
    public Student() {
    }
    

    //constructor with everything
    public Student(long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //constructor without id
    public Student(String name, String email,  LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //getters and setters

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();//calculate the age using dob
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }



    //tostring method that returns a string is json format containing obj data
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", age='" + getAge() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }

}