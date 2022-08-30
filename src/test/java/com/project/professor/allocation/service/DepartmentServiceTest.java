package com.project.professor.allocation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	

	@Autowired
	DepartmentService departmentService;

	@Test
	public void findById() {
		System.out.println(departmentService.findById(1l));
	}
}
