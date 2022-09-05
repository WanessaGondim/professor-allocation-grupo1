package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<List<Allocation>>(allocations, HttpStatus.OK);
	}

	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping(path = "/{professor/professor_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
		List<Allocation> allocation = allocationService.findByProfessorId(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(path = "/{course/course_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name="course_id")Long id){
		List<Allocation>allocation = allocationService.findByCourseId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{allocation id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="allocation_id")Long id){
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
	