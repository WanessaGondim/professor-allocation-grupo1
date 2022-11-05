package com.project.professor.allocation.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.exceptions.AllocationExistsException;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ProfessorRepository professorRepository;

	public DepartmentService(DepartmentRepository departmentRepository, ProfessorRepository professorRepository) {
		super();
		this.departmentRepository = departmentRepository;
		this.professorRepository = professorRepository;
	}
	
	

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public List<Department> findAll() {
			return departmentRepository.findAll();	
	}

	public Department create(Department department) {
		department.setId(null);
		return departmentRepository.save(department);
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id != null && departmentRepository.existsById(id)) {
			return departmentRepository.save(department);
		}
		return null;
	}

	
	 public void deleteById(Long id)throws AllocationExistsException, EntityNotFoundException {
		if (id!= null && departmentRepository.existsById(id)) {
			if (professorRepository.findByDepartmentId(id).size()> 0) {
				throw new AllocationExistsException ("Esse Departamento tem um professor");
			}else {
			departmentRepository.deleteById(id);}
		}else {
			throw new EntityNotFoundException ("O id do departamento não existe");
		}
	}


	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

	// CONSULTAS CUSTOMIZADAS

	public List<Department> findByNameContaining(String name) {
		return departmentRepository.findByNameContaining(name);
	}

	public List<Department> findByNameEndingWith(String name) {
		return departmentRepository.findByNameEndingWith(name);
	}


}
