package com.student.service;

import com.student.exception.RequestNotFoundException;
import com.student.model.Student;
import com.student.payload.StudentDto;
import com.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student= mapToEntity(studentDto);
        Student newStudent= studentRepo.save(student);

        StudentDto studentResponse= mapToDto(newStudent);

        return studentResponse;
    }

    @Override
    public List<StudentDto> getAllStudents() {

        List<Student> students = studentRepo.findAll();
        List<StudentDto> collect = students.stream().map(s -> mapToDto(s)).collect((Collectors.toList()));


        return collect;
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student= studentRepo.findById(id).orElseThrow(()->new RuntimeException("id is not found :" + id));
        StudentDto studentDto = mapToDto(student);
        return studentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long id) {

        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("id is not found :" + id));
        student.setName(studentDto.getName());
        student.setDepartment(studentDto.getDepartment());
        student.setContact(studentDto.getContact());
        student.setEmail(studentDto.getEmail());
        Student newStudent = studentRepo.save(student);
        return mapToDto(newStudent);


    }

    @Override
    public void deletePostById(long id) {

        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("id is not found :" + id));
        studentRepo.deleteById(id);
    }


    private StudentDto mapToDto(Student student) {


        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setDepartment(student.getDepartment());
        studentDto.setContact(student.getContact());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }


    private Student mapToEntity(StudentDto studentDto) {

         Student student= new Student();
         student.setName(studentDto.getName());
         student.setDepartment(studentDto.getDepartment());
         student.setContact(studentDto.getContact());
         student.setEmail(studentDto.getEmail());


        return student;
    }


}
