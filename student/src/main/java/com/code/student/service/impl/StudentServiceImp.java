package com.code.student.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.student.model.Course;
import com.code.student.model.Student;
import com.code.student.model.StudentDetail;
import com.code.student.repository.StudentRepository;
import com.code.student.service.StudentService;

import reactor.core.publisher.Mono;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ApiCall apiCall;
	
	@Autowired
	ApiCallUsingWebClient apiCallUsingWebClient;
	
	
	@Override
	public StudentDetail getById(int id) {
		// getting student from table by passing id
		Student s = studentRepository.findById(id).orElse(null);
		
		StudentDetail studentDetail = new StudentDetail();
		BeanUtils.copyProperties(s, studentDetail);
		
		Course course = apiCall.getCourseDetails(s.getCourseId());
		studentDetail.setCourse(course);
		return studentDetail;
	}
	
	@Override
	public StudentDetail getByIdUsingWebClient(int id) {
		// getting student from table by passing id
		Student s = studentRepository.findById(id).orElse(null);
		
		//getting course from course microservice by call API
		Mono<Course> courseMono = apiCallUsingWebClient.getCourseDetails(s.getCourseId());
		Course course = courseMono.block();
		
		StudentDetail sd = new StudentDetail();
		// copying properties from student to studentdetail object
		BeanUtils.copyProperties(s, sd);
		sd.setCourse(course);
		return sd;
	}
	
	
//	@Override
//	public Student getById(int id) {
//		// getting student from table by passing id
//		Student s = studentRepository.findById(id).orElse(null);
//		return s;
//	}

	@Override
	public Student getByName(String name) {
		Student s = studentRepository.findByName(name);
		return s;
	}

	@Override
	public boolean save(Student student) {
	//	System.out.println("id-" + student.getId() + ", name-" + student.getName() + ", mobileNo-" + student.getMobileNo());
		studentRepository.save(student);
		return true;
	}

	@Override
	public boolean delete(int id) {
		studentRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean update(Student student) {
		studentRepository.save(student);
		return true;
	}

}
