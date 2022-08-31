package com.project.professor.allocation.service;

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
	
	
	
}
