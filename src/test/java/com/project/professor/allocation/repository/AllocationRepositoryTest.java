package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");
	@Autowired
	AllocationRepository allocationRepository;

	@Test
	public void findAll() {

		List<Allocation> allocationList = allocationRepository.findAll();
		System.out.println(allocationList);
	}

	@Test
	public void findById() {
		Optional<Allocation> allocationToFind = allocationRepository.findById(2l);
		System.out.println(allocationToFind.orElse(null));
	}

	@Test
	public void create() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setEnd(sdf.parse("17:00-0300"));
		allocation.setProfessorId(1l);
		allocation.setStart(sdf.parse("20:00-0300"));
		System.out.println(allocation);

		Allocation alloc = allocationRepository.save(allocation);
		System.out.println(alloc);

	}

	@Test
	public void update() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setId(1l);
		allocation.setCourseId(1l);
		allocation.setDay(DayOfWeek.FRIDAY);
		allocation.setEnd(sdf.parse("18:00-0300"));
		allocation.setProfessorId(1l);
		allocation.setStart(sdf.parse("21:00-0300"));
		System.out.println(allocation);

		Allocation alloc = allocationRepository.save(allocation);
		System.out.println(alloc);
	}

	@Test
	public void delete() {
		Long id = 6L;
		allocationRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

}
