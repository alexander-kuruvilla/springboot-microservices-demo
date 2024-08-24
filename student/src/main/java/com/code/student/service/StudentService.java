package com.code.student.service;

import com.code.student.model.Student;
import com.code.student.model.StudentDetail;

public interface StudentService {
	StudentDetail getById(int id);
	Student getByName(String name);
	boolean save(Student student);
	boolean delete(int id);
	boolean update(Student student);
	StudentDetail getByIdUsingWebClient(int id);
	
}
