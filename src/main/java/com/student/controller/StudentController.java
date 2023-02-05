package com.student.controller;

import com.student.payload.ProfileDto;
import com.student.payload.StudentDto;
import com.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){

        return new ResponseEntity<>(
                studentService.createStudent(studentDto), HttpStatus.CREATED);

    }



    @GetMapping("/api/students")
    public List<StudentDto> getAllStudents(){

        return  studentService.getAllStudents();
    }

    @GetMapping("api/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") long id){


        StudentDto dto = studentService.getStudentById(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping ("api/students/{id}")
    public ResponseEntity<StudentDto> updatePost(

            @RequestBody @Valid StudentDto studentDto,
            @PathVariable("id") Long id
    ){

        return  new ResponseEntity<StudentDto>(studentService.updateStudent(studentDto, id), HttpStatus.CREATED);
    }

    @DeleteMapping("api/students/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){

        studentService.deletePostById(id);

        return new ResponseEntity<>("Student entity deleted successfully", HttpStatus.OK);


    }



}

