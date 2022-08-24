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

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void findAll() {
		List<Department> listadpt = departmentRepository.findAll();
		System.out.println(listadpt);
	}

	@Test
	public void mamute() {
		Optional<Department> achebyid = departmentRepository.findById(1l);
		System.out.println(achebyid.orElse(null));
	}

	@Test
	public void creat() {
		Department dpt = new Department();
		dpt.setName("Matemática");
		System.out.println(dpt);

		Department dpt2 = departmentRepository.save(dpt);
		System.out.println(dpt2);
	}

	@Test
	public void update() {
		Department dpt = new Department();
		dpt.setName("Português");
		dpt.setId(3l);
		System.out.println(dpt);

		Department dpt2 = departmentRepository.save(dpt);
		System.out.println(dpt2);
	}
	
	@Test
	public void delete() {
		departmentRepository.deleteById(3l);
		
	}
	@Test 
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
