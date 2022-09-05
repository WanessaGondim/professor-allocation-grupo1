package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Professor> professors;
		if (name == null) {
			professors = professorService.findAll();
		} else {
			professors = professorService.findByNameContaining(name);
		}
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> create(@RequestBody Professor professor) {
		try {
			Professor prof = professorService.create(professor);
			return new ResponseEntity<Professor>(prof, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<Professor>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> update(@RequestBody Professor professor,
			@PathVariable(name = "professor_id", required = true) Long professorId) {
		try {
			professor.setId(professorId);
			Professor prof = professorService.update(professor);
			if (prof == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Professor>(prof, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{professor_id}")
	public ResponseEntity<Void> deleteProf(@PathVariable(name = "professor_id") Long id) {
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

@DeleteMapping
@ResponseStatus(HttpStatus.NO_CONTENT)
public ResponseEntity<Void> deleteAll(){
professorService.deleteAll();
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
