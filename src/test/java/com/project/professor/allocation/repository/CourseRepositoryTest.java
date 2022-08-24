package com.project.professor.allocation.repository;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void findAll() {

		List<Course> courselist = courseRepository.findAll();
		System.out.println(courselist);
	}

	@Test
	public void findById() {
		Optional<Course> courseToFind = courseRepository.findById(2l);
		System.out.println(courseToFind.orElse(null));
	}

	@Test
	public void create() {
		Course course1 = new Course();
		course1.setName("Enfermagem");
		System.out.println(course1);

		Course course = courseRepository.save(course1);
		System.out.println(course);
	}

	@Test
	public void update() {
		Course course1 = new Course();
		course1.setName("Fisioterapia");
		course1.setId(2l);
		System.out.println(course1);

		Course course2 = courseRepository.save(course1);
		System.out.println(course2);
	}

	@Test
	public void delete() {
		courseRepository.deleteById(2l);
	}

	@Test
	void deleteAll() {
		courseRepository.deleteAllInBatch();
	}

}
