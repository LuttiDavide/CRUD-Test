package com.example.demoCRUDTest.repository;

import com.example.demoCRUDTest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
