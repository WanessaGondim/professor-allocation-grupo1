package com.project.professor.allocation.service;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.project.professor.allocation.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	@Autowired
	AllocationService allocationService;
	
	@Test
	public void findById() {
		System.out.println(allocationService.findById(1l));
	}
	
	@Test
	public void findAll() {
		List<Allocation> allocations = allocationService.findAll();
		System.out.println(allocations);
	}
	
	@Test
	public void create() {
		Allocation allocation = new Allocation();
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setProfessorId(1l);
		allocation.setStart(null);
		allocation.setEnd(null);
		allocationService.create(allocation);
		System.out.println(allocation);
	}
	
	@Test
	public void update() {
		Allocation allocation = new Allocation();
		allocation.setId(1l);
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.TUESDAY);
		allocation.setProfessorId(1l);
		allocation.setStart(null);
		allocation.setEnd(null);
		allocationService.update(allocation);
		System.out.println(allocation);
	}
	
	@Test
	public void deleteById() {
		allocationService.deleteById(1l);
	}
	
	@Test
	public void deleteAll() {
		allocationService.deleteAll();
	}
	
	//TESTES CONSULTAS CUSTOMIZADAS
	
	@Test
	public void findByCourse() {
		allocationService.findByCourse(null);
	}
	
	@Test
	public void findByProfessorId() {
		allocationService.findByProfessorId(1l);
	}
	
	
}
