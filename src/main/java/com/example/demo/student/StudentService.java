//service layer, responsible for talking to the data access layer to manage data
//sort of like a controller

package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//making this service available for dependency injection
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired//autowired annotaion to tell spring to automatically inject studentrepo dependency into thisr class
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    

    //get all students endpoint
    public List<Student> getStudents() {
        return studentRepository.findAll();//we did not impolement this method, the JPA does it for us
	}

    //add student endpoint
    public void addStudent(Student student){

        //optional is a container class that can store null values
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }

        //save student if validations passed
        studentRepository.save(student);
    }

    //delete student endpoint
    public void deleteStudent(Long id){

        boolean exists = studentRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Student not found");
        }

        studentRepository.deleteById(id);
    }

    //update student endpoint
    @Transactional// no need to go db, just use getter and setter to update
    public void updateStudent(Long id , Student fields){
        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent()){
            throw new IllegalStateException("Student not found");
        }

        //validate fields
        if(fields == null || fields.getDob() != null || fields.getId() != 0){
                throw new IllegalStateException("bad request");
            }

        if(fields.getEmail() != null){
            student.get().setEmail(fields.getEmail());
        }

        if(fields.getName() != null){
            student.get().setName(fields.getName());
        }

        

        System.out.println(student.get());

    }

}