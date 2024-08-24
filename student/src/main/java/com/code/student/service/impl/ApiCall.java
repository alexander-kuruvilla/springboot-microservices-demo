package com.code.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.code.student.model.Course;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class ApiCall {
	
	@Autowired RestTemplate restTemplate;
	
	
	private static final String courseMicroServiceBaseUrl = "http://course-service";
	
	@CircuitBreaker(name = "example",fallbackMethod = "fallback")
	public Course getCourseDetails(int courseId) {
		Course course = restTemplate.getForObject(courseMicroServiceBaseUrl+"/course/id?id={courseId}", Course.class, courseId);
		return course;
	}
	
	public Course fallback(Exception ex) {
		System.out.println("calling fallback and returning default course data");
		Course course = new Course();
		course.setId(1);
		course.setName("Medicine");
		course.setDuration(7);
		return course;
	}
	

}
