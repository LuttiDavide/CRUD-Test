package com.example.demoCRUDTest.service;

import com.example.demoCRUDTest.entity.Student;
import com.example.demoCRUDTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent (Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents () {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById (Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent (Long id, Student studentDetails) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDetails.getName());
            student.setSurname(studentDetails.getSurname());
            return studentRepository.save(student);
        }
        return null;
    }

    public Student updateStudentWorkingStatus (Long id, boolean isWorking) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setWorking(isWorking);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent (Long id) {
        studentRepository.deleteById(id);
    }

}
