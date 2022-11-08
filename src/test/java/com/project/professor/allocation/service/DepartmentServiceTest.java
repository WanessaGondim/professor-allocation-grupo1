package com.project.professor.allocation.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.exceptions.AllocationExistsException;

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
	
	@Test
	public void create() {
		Department depart = new Department();
		depart.setName("Departamento Historia");
		departmentService.create(depart);
		System.out.println(depart);
	}
	
	@Test
	public void update() {
		Department depart = new Department();
		depart.setId(4l);
		depart.setName("Departamento Geografia");
		departmentService.update(depart);
	}
	
	@Test
	public void deleteById() throws EntityNotFoundException, AllocationExistsException {
		departmentService.deleteById(4l);	
	}
	
	@Test
	public void deleteAll() {
		departmentService.deleteAll();
	}
	
	//TESTE CONSULTAS CUSTOMIZADAS
	
	@Test
	public void findByNameContaining() {
		List<Department> depart = departmentService.findByNameContaining("tica");
		System.out.println(depart);
	}
	
	@Test
	public void findByNameEndingWith() {
		List<Department> depart = departmentService.findByNameEndingWith("ia");
		System.out.println(depart);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}