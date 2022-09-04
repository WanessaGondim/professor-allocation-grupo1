package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.exceptions.HasCollisionException;
import com.project.professor.allocation.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");
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
	public void create() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setProfessorId(1l);
		allocation.setStart(sdf.parse("17:00-0300"));
		allocation.setEnd(sdf.parse("20:00-0300"));
		try {
			allocationService.create(allocation);
		} catch (HasCollisionException hcex) {
			System.out.println("Houve uma colisao de horarios");
		}
		System.out.println(allocation);
	}
	
	@Test
	public void update() {
		Allocation allocation = new Allocation();
		allocation.setId(1l);
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setProfessorId(1l);
		allocation.setStart(null);
		allocation.setEnd(null);
		try {
			allocationService.update(allocation);
		} catch (HasCollisionException hcex) {
			System.out.println("Houve uma colisão de horários");
		}
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
	public void findByCourseId() {
		allocationService.findByCourseId(1l);
	}
	
	@Test
	public void findByProfessorId() {
		allocationService.findByProfessorId(1l);
	}
	
	
}
