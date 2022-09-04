package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.exceptions.HasCollisionException;
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

	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}

	public Allocation create(Allocation allocation) throws HasCollisionException {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) throws HasCollisionException {
		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}

	// CONSULTAS CUSTOMIZADAS

	public List<Allocation> findByCourseId(Long courseId) {
		return allocationRepository.findByCourseId(courseId);
	}

	public List<Allocation> findByProfessorId(Long professorId) {
		return allocationRepository.findByProfessorId(professorId);
	}

	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	private Allocation saveInternal(Allocation allocation) throws HasCollisionException {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new HasCollisionException();
		} else {
			Long professorId = allocation.getProfessorId();
			Professor professor = professorService.findById(professorId);

			Long courseId = allocation.getCourseId();
			Course course = courseService.findById(courseId);

			Allocation allocationSaved = allocationRepository.save(allocation);

			allocationSaved.setProfessor(professor);
			allocationSaved.setCourse(course);

			return allocationSaved;
		}
	}
	
	//REGRA DE NEGÓCIO

	private boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

	
	
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
				&& allocation.getEnd().compareTo(allocation.getStart()) > 0;
	}

}
