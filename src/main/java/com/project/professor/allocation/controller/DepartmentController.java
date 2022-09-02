package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	 
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Department>> findAll(){
		List<Department> departments = departmentService.findAll();
		return new ResponseEntity<List<Department>>(departments,HttpStatus.OK);
	}
	
	
	
	
}