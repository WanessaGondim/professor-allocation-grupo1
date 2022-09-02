package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	CourseService courseService;
	
	@Test
	public void findById() {
	System.out.println(courseService.findById(1l));
	}
	
	@Test
	public void findAll() {
		List<Course> courses = courseService.findAll();
		System.out.println(courses);
	}
	
	
	
	
}
