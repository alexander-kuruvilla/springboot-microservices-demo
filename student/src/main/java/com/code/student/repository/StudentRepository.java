package com.code.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findByName(String name);
}
