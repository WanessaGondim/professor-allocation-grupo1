package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorRepositoryTest {
	
	@Autowired
	ProfessorRepository professorRepository;
	
//	@Test
//	public void findAll() {
//		
//		List<Professor> professorList = professorRepository.findAll();
//		System.out.println(professorList);
//	}
//	
//	@Test
//	public void findById() {
//		Optional<Professor> professorToFind = professorRepository.findById(2l);
//		System.out.println(professorToFind.orElse(null));
//	}
//	
//	@Test
//    public void create() {
//    	Professor prof1 = new Professor();
//    	prof1.setName("Camila Prado");
//    	System.out.println(prof1);
//    	
//    	Professor prof = professorRepository.save(prof1);
//    	System.out.println(prof);
//    }
//	
//	@Test
//	public void update() {
//		Professor prof1 = new Professor();
//		prof1.setName("Lorena Amaral");
//		prof1.setId(2l);
//		System.out.println(prof1);
//		
//		Professor prof2 = professorRepository.save(prof1);
//		System.out.println(prof2);
//	}
//	
//	@Test
//	public void delete() {
//		professorRepository.deleteById(2l);
//	}
//	
//	@Test void deleteAll() {
//		professorRepository.deleteAllInBatch();
//	}
//	
	
	
	
	
	
	
	
	
	
	
}
