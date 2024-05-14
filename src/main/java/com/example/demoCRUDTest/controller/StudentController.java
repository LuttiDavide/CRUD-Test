package com.example.demoCRUDTest.controller;

import com.example.demoCRUDTest.entity.Student;
import com.example.demoCRUDTest.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents () {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById (@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent (@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    @PatchMapping("/{id}/working")
    public Student updateStudentWorkingStatus (@PathVariable Long id, @RequestParam boolean working) {
        return studentService.updateStudentWorkingStatus(id, working);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent (@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
