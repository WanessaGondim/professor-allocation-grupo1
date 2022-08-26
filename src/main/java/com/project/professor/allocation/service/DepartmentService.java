package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public Department findById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return department;
	}
				
	public Department Creat(Department department) {
		department = departmentRepository.save(department);
		return department;
	}
	
	public void deleteById (Long id) {
		departmentRepository.deleteById(id);
	}
	
	public void deleteAll (Long id) {
		departmentRepository.deleteAllInBatch();
	}
	
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
	
	public Department updateDepartment(Department department) {
		Long id = department.getId();
		if(id != null) {
			return departmentRepository.save(department);
		}
		return null;
	}
}
