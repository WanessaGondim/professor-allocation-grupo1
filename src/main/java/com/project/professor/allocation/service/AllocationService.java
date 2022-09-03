package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}
	
	public List<Allocation> findAll(){
		return allocationRepository.findAll();
		}
	
	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}
	
	private Allocation saveInternal(Allocation allocation) {
		allocation = allocationRepository.save(allocation);

		Professor professor = professorService.findById(allocation.getProfessorId());
		allocation.setProfessor(professor);

		Course course = courseService.findById(allocation.getCourseId());
		allocation.setCourse(course);

		return allocation;
	}
	
	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}
	
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
	
	//CONSULTAS CUSTOMIZADAS
	
	public List<Allocation> findByCourse(Long courseId){
		return allocationRepository.findByCourseId(courseId);
	}
	
	public List<Allocation> findByProfessorId(Long professorId){
		return allocationRepository.findByProfessorId(professorId);
	}
}
