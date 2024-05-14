package com.example.demoCRUDTest.service;

import com.example.demoCRUDTest.entity.Student;
import com.example.demoCRUDTest.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testCreateStudent () {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);
        assertThat(createdStudent.getName()).isEqualTo("John");
        assertThat(createdStudent.getSurname()).isEqualTo("Doe");
        assertThat(createdStudent.isWorking()).isTrue();
    }

    @Test
    public void testGetAllStudents () {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentRepository.findAll()).thenReturn(Collections.singletonList(student));

        List<Student> students = studentService.getAllStudents();
        assertThat(students).hasSize(1);
        assertThat(students.getFirst().getName()).isEqualTo("John");
    }

    @Test
    public void testGetStudentById () {
        Student student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> foundStudent = studentService.getStudentById(1L);
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getName()).isEqualTo("John");
    }

    @Test
    public void testUpdateStudent () {
        Student student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setSurname("Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student updatedStudent = studentService.updateStudent(1L, student);
        assertThat(updatedStudent.getName()).isEqualTo("John");
        assertThat(updatedStudent.getSurname()).isEqualTo("Doe");
    }

    @Test
    public void testUpdateStudentWorkingStatus () {
        Student student = new Student();
        student.setId(1L);
        student.setWorking(false);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student updatedStudent = studentService.updateStudentWorkingStatus(1L, false);
        assertThat(updatedStudent.isWorking()).isFalse();
    }

    @Test
    public void testDeleteStudent () {
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

}
