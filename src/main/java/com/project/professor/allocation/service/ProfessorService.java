package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}

	public Professor findById(Long id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}

	public Professor create(Professor professor) {
		professor.setId(null);
		return professorRepository.save(professor);
	}

	public Professor updateProfessor(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return professorRepository.save(professor);
		}
		return null;
	}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll(Long id) {
		professorRepository.deleteAllInBatch();
	}

}
