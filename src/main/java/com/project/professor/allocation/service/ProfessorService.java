package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, 
			               DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public Professor findById(Long id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}
	
	public List<Professor> findAll(){
		return professorRepository.findAll(); 
	}

	public Professor create(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor updateProfessor(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		}else {
			return null;
		}
		}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll(Long id) {
		professorRepository.deleteAllInBatch();
	}
	
	//CONSULTAS CUSTOMIZADAS
	
	public List<Professor> findByDepartmentId (Long department_id){
		return professorRepository.findByDepartmentId(department_id);
	}
	
	public Professor findByCpf (String cpf) {
		return professorRepository.findByCpf(cpf);
	}
	
	public List<Professor> findByNameContaining (String name){
		return professorRepository.findByNameContaining(name);
	}
	

	public Professor saveInternal(Professor professor) {
		Long departmentId = professor.getDepartmentId();
		Department departmet = departmentService.findById(departmentId);
		
		Professor prof2 =  professorRepository.save(professor);
		prof2.setDepartment(departmet);
		
		return prof2;
	}
}
