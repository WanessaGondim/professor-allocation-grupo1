package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@ApiOperation(value = "Find all allocations")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<List<Allocation>>(allocations, HttpStatus.OK);
	}

	@ApiOperation(value = "Find a allocation by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Find a allocation by ProfessorId")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@GetMapping(path = "/professor/{professor_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
		List<Allocation> allocation = allocationService.findByProfessorId(id);
		return new ResponseEntity<>(allocation, HttpStatus.OK);

	}

	@ApiOperation(value = "Find a allocation by CourseId")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@GetMapping(path = "/course/{course_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name="course_id")Long id){
		List<Allocation>allocation = allocationService.findByCourseId(id);
		return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a allocation")
	@ApiResponses({
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation){
		
		try {
			Allocation alloc = allocationService.create(allocation);
			return new ResponseEntity<Allocation>(alloc, HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<Allocation>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@ApiOperation(value = "Update a allocation by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@PutMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@RequestBody Allocation allocation, 
											 @PathVariable(name = "allocation_id", required=true) Long allocationId){
		try {
			allocation.setId(allocationId);
			Allocation alloc = allocationService.update(allocation);
			if(alloc == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else { 
				return new ResponseEntity<Allocation>(alloc, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a allocation by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT")
	})
	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name="allocation_id")Long id){
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all allocations")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT")
	})
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
