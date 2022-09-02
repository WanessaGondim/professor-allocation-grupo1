package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	

	@Autowired
	DepartmentService departmentService;

	@Test
	public void findById() {
		System.out.println(departmentService.findById(1l));
	}
	
	@Test
	public void findAll() {
		List<Department> departments = departmentService.findAll();
		System.out.println(departments);
	}
	
	
	
}
