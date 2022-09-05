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
	
	@Test
	public void create() {
		Course course = new Course();
		course.setName("Jornalismo");
		courseService.create(course);
		System.out.println(course);
	}
	
	@Test
	public void update() {
		Course course = new Course();
		course.setId(4l);
		course.setName("Jornalismo Updated");
		courseService.update(course);
		System.out.println(course);
	}
	
	@Test
	public void deleteById() {
		courseService.deleteById(3l);
	}
	
	@Test
	public void deleteAll() {
		courseService.deleteAll();
	}
	
	// TESTES CONSULTAS CUSTOMIZADAS
	
	@Test
	public void findByNameContaining() {
		courseService.findByNameContaining("magem");
	}
	
	
	
	
	
}
