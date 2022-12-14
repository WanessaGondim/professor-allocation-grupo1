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

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.exceptions.NotDeleteProfessorWithAllocationException;
import com.project.professor.allocation.service.AllocationService;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
	
	private final ProfessorService professorService;
	private final AllocationService allocationService;

	public ProfessorController(ProfessorService professorService, AllocationService allocationService) {
		super();
		this.professorService = professorService;
		this.allocationService  = allocationService;
	}
	
	@ApiOperation(value = "Find all professors")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Professor>> findAll(@RequestParam(name = "name", required = false)String name){
		List<Professor> professors;
		if(name == null) {
			professors = professorService.findAll();
		}else {
			professors = professorService.findByNameContaining(name);
		}
		return new ResponseEntity<List<Professor>>(professors,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a professor")
	@ApiResponses({
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Professor> create(@RequestBody Professor professor){
		try {
			Professor prof = professorService.create(professor);
			return new ResponseEntity<Professor>(prof, HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<Professor>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a professor by Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BAD REQUEST"),
		@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@PutMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> update(@RequestBody Professor professor, 
											 @PathVariable(name = "professor_id", required=true) Long professorId){
		try {
			professor.setId(professorId);
			Professor prof = professorService.update(professor);
			if(prof == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else { 
				return new ResponseEntity<Professor>(prof, HttpStatus.OK);
			}	
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@ApiOperation(value = "Delete a professor by Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT")
	})
	@DeleteMapping(path = "/{professor_id}")
	public ResponseEntity<String> deleteProf(@PathVariable(name = "professor_id") Long id) {
		try {
			List<Allocation> allcotion = allocationService.findByProfessorId(id);
			if(!allcotion.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("N??o pode deletar um professor com aloca????es. Remova as aloca????es primeiro e em seguida remova o professor.");
			};
			
			professorService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NotDeleteProfessorWithAllocationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value = "Delete all professors")
	@ApiResponses({
		@ApiResponse(code = 204, message = "NO CONTENT")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll(){
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	

}
