//routes file
//api layer, will be responsible for mapping endpoints
package com.example.demo.student;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/students")//specifies the coute for this controller to be accessed
public class StudentController {

	private final StudentService studentService;


	@Autowired//autowired annotaion to tell spring to automatically inject dependency into this controller class
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}


    //create get endpoint
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	//create post endpoint
	@PostMapping
	public void addStudent(@RequestBody Student student){
		studentService.addStudent(student);
	}

	//create delete endpoint
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id){//path var anotation gets var from url param

		studentService.deleteStudent(id);


	}

	//create put endpoint
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long id , @RequestBody Student fields){
		studentService.updateStudent(id , fields);
	}

}