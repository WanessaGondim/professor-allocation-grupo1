package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {

	//SELECT * FROM professor WHERE department_id = 1?
			List<Professor> findByDepartmentId (Long department_id);

	//SELECT * FROM professor WHERE cpf = 1?	
			Professor findByCpf (String cpf);
			
	//SELECT * FROM professor WHERE name LIKE '%1?%'
			List<Professor> findByNameContaining (String name);
	
}
