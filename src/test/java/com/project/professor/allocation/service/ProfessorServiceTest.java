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
		prof.setName("Arnaldo Campos");
		prof.setDepartmentId(1l);
		professorService.create(prof);
		System.out.println(prof);	
	}
	
	@Test
	public void update() {
		Professor prof = new Professor();
		prof.setId(6l);
		prof.setCpf("25412565411");
		prof.setName("Arnaldo Gomes");
		prof.setDepartmentId(1l);
		professorService.update(prof);
		System.out.println(prof);
	}
	
	@Test
	public void deleteById() {
		professorService.deleteById(5l);
	}
	
	@Test
	public void deleteAll() {
		professorService.deleteAll();
	}
	
	//TESTES CONSULTAS CUSTOMIZADAS
	
	@Test
	public void findByDepartmentId() {
		professorService.findByDepartmentId(2l);
	}
	
	@Test
	public void findByCpf() {
		professorService.findByCpf("25412565411");
	}
	
	@Test
	public void findByNameContaining() {
		professorService.findByNameContaining("mila");
	}
	
	
	
	
}
