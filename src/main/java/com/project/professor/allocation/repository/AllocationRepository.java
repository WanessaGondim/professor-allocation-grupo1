package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation,Long>{

	//SELECT * FROM allocation WHERE course_id = 1?
		List<Allocation> findByCourseId (Long course_id);
		
		//SELECT * FROM allocation WHERE professor_id = 1?[
		List<Allocation> findByProfessorId (Long professor_id);
		
	
}
