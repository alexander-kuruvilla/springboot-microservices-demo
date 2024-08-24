package com.code.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.course.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {

}
