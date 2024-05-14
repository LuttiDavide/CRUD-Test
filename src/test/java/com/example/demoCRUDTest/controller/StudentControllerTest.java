package com.example.demoCRUDTest.controller;

import com.example.demoCRUDTest.entity.Student;
import com.example.demoCRUDTest.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateStudent () throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.working").value(true));
    }

    @Test
    public void testGetAllStudents () throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentService.getAllStudents()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].surname").value("Doe"))
                .andExpect(jsonPath("$[0].working").value(true));
    }

    @Test
    public void testGetStudentById () throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setSurname("Doe");
        student.setWorking(true);

        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.working").value(true));
    }

    @Test
    public void testUpdateStudent () throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");

        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    public void testUpdateStudentWorkingStatus () throws Exception {
        Student student = new Student();
        student.setWorking(false);

        when(studentService.updateStudentWorkingStatus(1L, false)).thenReturn(student);

        mockMvc.perform(patch("/students/1/working?working=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.working").value(false));
    }

    @Test
    public void testDeleteStudent () throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(1L);
    }

}
