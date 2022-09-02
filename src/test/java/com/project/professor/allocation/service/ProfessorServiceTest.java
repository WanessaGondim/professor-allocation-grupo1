package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;
			
	@Test
	public void findById() {
	System.out.println(professorService.findById(1l));
	}
	
	@Test
	public void findAll() {
		List<Professor> professors = professorService.findAll();
		System.out.println(professors);
	}
	
	@Test
	public void create() {
		Professor prof = new Professor();
		
		prof.setId(null);
		prof.setCpf("25412565415");
		prof.setName(null);
		prof.setDepartmentId(1l);
		
		System.out.println(professorService.create(prof));
		
		
	}
	
	
}
