package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public Course findById(Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		return course;
	}
				
	public Course Creat(Course department) {
		department = courseRepository.save(department);
		return department;
	}
	
	public void deleteById (Long id) {
		courseRepository.deleteById(id);
	}
	
	public void deleteAll (Long id) {
		courseRepository.deleteAllInBatch();
	}
	
}
