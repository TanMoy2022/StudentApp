package com.student.service;

import com.student.payload.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(long id);

    StudentDto updateStudent(StudentDto studentDto, Long id);

    void deletePostById(long id);
}
